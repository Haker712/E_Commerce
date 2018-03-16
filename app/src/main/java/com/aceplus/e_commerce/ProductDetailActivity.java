package com.aceplus.e_commerce;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.e_commerce.model.modelForView.ProductDetailDataForView;
import com.aceplus.e_commerce.model.modelforAddtoCart.AddtoCartData;
import com.aceplus.e_commerce.model.modelforWishList.WishListData;
import com.aceplus.e_commerce.utils.DatabaseContract;
import com.aceplus.e_commerce.utils.MyDatabase;
import com.google.gson.Gson;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {


    SQLiteDatabase sqLiteDatabase;

    String str_to_know_previous_class;

    ProductDetailDataForView productDetailDataForView;

    ImageView imgProductDetail;
    TextView txtProductDetailName, txtProductDetailPrice, txtProductDetailDiscountPrice, txtProductDetailDescription;

    BoomMenuButton boomMenuButton;

    ArrayList<Integer> namelist = new ArrayList<>();

    int[] imageList1 = new int[]{
            R.mipmap.cart1,
            R.mipmap.heart3,
            R.mipmap.share};

    int[] imageList2 = new int[]{
            R.mipmap.cart1,
            R.mipmap.heart2,
            R.mipmap.share};

    boolean clicked = false;

    AddtoCartData addtoCartData;
    WishListData wishListData;

    int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        sqLiteDatabase = new MyDatabase(this).getDataBase();


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product Details");


        findviewById();

        getObjectIntent();

        CheckWishList();

        setProductDetailData();

        BoomMenuButton();

        RatioForLayouts();
    }


    public ProductDetailDataForView getObjectIntent() {

        Intent intent = getIntent();
        String str = intent.getStringExtra("ProductDetailData");
        str_to_know_previous_class = intent.getStringExtra("String");
        Gson gson = new Gson();
        productDetailDataForView = gson.fromJson(str, ProductDetailDataForView.class);


        return productDetailDataForView;
    }

    public void findviewById() {

        imgProductDetail = (ImageView) findViewById(R.id.productDetail_ImgView);
        txtProductDetailName = (TextView) findViewById(R.id.txtproductDetailName);
        txtProductDetailPrice = (TextView) findViewById(R.id.txtproductDetailPrice);
        txtProductDetailDiscountPrice = (TextView) findViewById(R.id.txtproductDetailDiscountPrice);
        txtProductDetailDescription = (TextView) findViewById(R.id.txtproductDetailDescription);

    }

    public void setProductDetailData() {

        Log.i("DPrice", productDetailDataForView.getDiscount_price() + "");

        txtProductDetailName.setText(productDetailDataForView.getProduct_name());
        txtProductDetailPrice.setText(String.valueOf(productDetailDataForView.getProduct_price() + " MMK"));
        txtProductDetailDescription.setText(productDetailDataForView.getProduct_description());
        byte[] decodeValue = Base64.decode(productDetailDataForView.getImage_encode(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);

        imgProductDetail.setImageBitmap(bitmap);

        if (productDetailDataForView.getDiscount_price() == 0.0) {
            txtProductDetailDiscountPrice.setVisibility(View.GONE);
            txtProductDetailPrice.setPaintFlags(0);
        } else {
            txtProductDetailPrice.setPaintFlags(txtProductDetailDiscountPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            txtProductDetailDiscountPrice.setVisibility(View.VISIBLE);
            txtProductDetailDiscountPrice.setText(String.valueOf(productDetailDataForView.getDiscount_price()) + " MMK");
        }


    }

    public void BoomMenuButton() {

        boomMenuButton = (BoomMenuButton) findViewById(R.id.bmb);

        assert boomMenuButton != null;


        boomMenuButton.setButtonEnum(ButtonEnum.TextInsideCircle);
        boomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_3_1);//Main htl mhr pw mae style
        boomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_3_1);//Htwt lr mae style


        namelist.add(R.string.name1);
        namelist.add(R.string.name2);
        namelist.add(R.string.name3);
        Log.i("Click", clicked + "");
        if (clicked == true) {

            for (int i = 0; i < 3; i++) {
                boomMenuButton.addBuilder(getHamButtonBuilderWithDifferentPieceColor(imageList2[i], namelist.get(i)));
            }
        } else {
            for (int i = 0; i < 3; i++) {
                boomMenuButton.addBuilder(getHamButtonBuilderWithDifferentPieceColor(imageList1[i], namelist.get(i)));
            }
        }


    }

    TextInsideCircleButton.Builder getHamButtonBuilderWithDifferentPieceColor(int image, int name) {
        return new TextInsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {

                if (index == 0) {

                    insertIntoStockReserve();

                } else if (index == 1) {

                    if (clicked == true) {

                        clicked = false;

                        deleteFromWishlist();
                        boomMenuButton.clearBuilders();
                        Toast.makeText(ProductDetailActivity.this, "Successfully Removed from Your Wish list!", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < 3; i++) {
                            boomMenuButton.addBuilder(getHamButtonBuilderWithDifferentPieceColor(imageList1[i], namelist.get(i)));
                        }
                    } else {

                        clicked = true;

                        insertIntoWishList();
                        boomMenuButton.clearBuilders();
                        Toast.makeText(ProductDetailActivity.this, "Successfully Added to Your Wish list!", Toast.LENGTH_SHORT).show();
                        for (int i = 0; i < 3; i++) {
                            boomMenuButton.addBuilder(getHamButtonBuilderWithDifferentPieceColor(imageList2[i], namelist.get(i)));
                        }

                    }

                } else if (index == 2) {

                } else {

                }

            }
        })
                .normalImageRes(image)
                .normalTextRes(name)
                .normalColorRes(R.color.white);
    }

    public void insertIntoStockReserve() {


        addtoCartData = SetAddtoCartData();

        int product_id = addtoCartData.getProduct_id();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM stock_reserve WHERE product_id=" + product_id + "", null);

        if (cursor.getCount() == 0) {

            sqLiteDatabase.beginTransaction();


            ContentValues contentValues = new ContentValues();

            contentValues.put(DatabaseContract.Stock_reserve.customer_id, addtoCartData.getCustomer_id());
            contentValues.put(DatabaseContract.Stock_reserve.product_id, addtoCartData.getProduct_id());
            contentValues.put(DatabaseContract.Stock_reserve.qty, addtoCartData.getQuantity());

            sqLiteDatabase.insert(DatabaseContract.Stock_reserve.tb, null, contentValues);


            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
        }else {

            Toast.makeText(this, "This Product is already added to your cart", Toast.LENGTH_SHORT).show();

        }

    }

    public AddtoCartData SetAddtoCartData() {

        addtoCartData = new AddtoCartData();

        Id = productDetailDataForView.getId();

        addtoCartData.setCustomer_id(1);
        addtoCartData.setProduct_id(Id);
        addtoCartData.setQuantity(1);

        return addtoCartData;
    }

    public void insertIntoWishList() {


        wishListData = SetWishListData();

        sqLiteDatabase.beginTransaction();


        ContentValues contentValues = new ContentValues();

        Log.i("Cus_Id", wishListData.getCustomer_id() + "");

        contentValues.put(DatabaseContract.Wishlist.customer_id, wishListData.getCustomer_id());
        contentValues.put(DatabaseContract.Wishlist.product_id, wishListData.getProduct_id());

        sqLiteDatabase.insert(DatabaseContract.Wishlist.tb, null, contentValues);


        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }

    public WishListData SetWishListData() {

        wishListData = new WishListData();

        Id = productDetailDataForView.getId();
        Toast.makeText(ProductDetailActivity.this, Id + "", Toast.LENGTH_SHORT).show();


        wishListData.setCustomer_id(1);
        wishListData.setProduct_id(Id);
        wishListData.setQuantity(1);

        return wishListData;
    }

    public void deleteFromWishlist() {

        int remove_id = wishListData.getProduct_id();
        Log.i("RM_ID", remove_id + "");

        sqLiteDatabase.beginTransaction();

        sqLiteDatabase.execSQL("DELETE FROM wishlist WHERE product_id=" + remove_id + "");

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }

    public void CheckWishList() {

        wishListData = SetWishListData();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM wishlist WHERE product_id=" + wishListData.getProduct_id() + "", null);

        Log.i("idd", wishListData.getProduct_id() + "");
        Log.i("COUNT", cursor.getCount() + "");


        if (cursor.getCount() != 0) {

            clicked = true;


        }

        Log.i("clicked", clicked + "");

    }

    @Override
    public void onBackPressed() {
        Intent intent;

        Log.i("ComeFromWhich", str_to_know_previous_class);
        if (str_to_know_previous_class.equalsIgnoreCase("ProductActivity")) {

            intent = new Intent(ProductDetailActivity.this, ProductActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", ProductActivity.productDataForViews);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
            finish();


        } else {

            intent = new Intent(ProductDetailActivity.this, MainActivity.class);
            startActivity(intent);
            finish();


        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();
                return true;


            case R.id.cart:
                // TODO put your code here to respond to the button tap
                Toast.makeText(this, "Cart!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, CartListActivity.class);
                intent1.putExtra("Class", "ProductDetailActivity");
                startActivity(intent1);
                finish();
                return true;

            case R.id.search:
                // TODO put your code here to respond to the button tap
                Intent intent2 = new Intent(this, SearchActivity.class);
                startActivity(intent2);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void RatioForLayouts() {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        int heightRatioForCard = height / 3;

        ImageView imageView = (ImageView) findViewById(R.id.productDetail_ImgView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, heightRatioForCard);

        imageView.setLayoutParams(params);


    }


}
