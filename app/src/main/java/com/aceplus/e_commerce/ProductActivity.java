package com.aceplus.e_commerce;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.e_commerce.model.modelForView.ProductDataForView;
import com.aceplus.e_commerce.model.modelForView.ProductDetailDataForView;
import com.aceplus.e_commerce.utils.MyDatabase;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    static ArrayList<ProductDataForView> productDataForViews = new ArrayList<>();

    int product_id;
    String product_name;
    Double product_price;
    Double product_discount_price;
    String product_description;
    String product_image_encode;

    ProductDetailDataForView productDetailDataForView;

    ProductAdapter productAdapter;
    RecyclerView ProductRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        sqLiteDatabase = new MyDatabase(this).getDataBase();

        getArrayIntent();

        setProductRecyclerviewAdapter();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Products");


    }

    private ArrayList<ProductDataForView> getArrayIntent() {

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        productDataForViews.clear();
        productDataForViews = (ArrayList<ProductDataForView>) args.getSerializable("ARRAYLIST");


        return productDataForViews;
    }

    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        private List<ProductDataForView> productDataForViewList = new ArrayList<>();
        LayoutInflater layoutInflater;
        Context context;

        public ProductAdapter(List<ProductDataForView> productDataForViewList, Context context) {
            this.productDataForViewList = productDataForViewList;
            layoutInflater = LayoutInflater.from(context);
            this.context = context;


        }

        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.product_cardview, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            RatioForLayouts(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ProductAdapter.ViewHolder holder, final int position) {


            product_name = productDataForViewList.get(position).getProduct_name();
            product_price = productDataForViewList.get(position).getCost_price();
            product_discount_price = productDataForViewList.get(position).getDiscount_price();


            byte[] decodeValue = Base64.decode(productDataForViewList.get(position).getImage_encode(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);


            if (product_discount_price.equals(0.0)) {

                holder.imgProduct.setImageBitmap(bitmap);
                holder.txtProduceName.setText(product_name);
                holder.txtProductPrice.setText(String.valueOf(product_price + "MMK"));
                holder.txtProductPrice.setPaintFlags(0);
                holder.txtProductDiscountPrice.setVisibility(View.GONE);

            } else {

                holder.imgProduct.setImageBitmap(bitmap);
                holder.txtProduceName.setText(product_name);
                holder.txtProductPrice.setText(String.valueOf(product_price + "MMK"));
                holder.txtProductPrice.setPaintFlags(holder.txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtProductDiscountPrice.setVisibility(View.VISIBLE);
                holder.txtProductDiscountPrice.setText(String.valueOf(product_discount_price) + "MMK");

            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    productDetailDataForView = new ProductDetailDataForView();

                    product_id = productDataForViewList.get(position).getId();
                    product_name = productDataForViewList.get(position).getProduct_name();
                    product_price = productDataForViewList.get(position).getCost_price();
                    product_image_encode = productDataForViewList.get(position).getImage_encode();
                    product_discount_price = productDataForViewList.get(position).getDiscount_price();


                    Cursor cursor = sqLiteDatabase.rawQuery("Select * From product_description where product_group_id='" + product_id + "'", null);

                    while (cursor.moveToNext()) {

                        product_description = cursor.getString(cursor.getColumnIndex("description"));


                    }

                    productDetailDataForView.setId(product_id);
                    productDetailDataForView.setProduct_name(product_name);
                    productDetailDataForView.setProduct_price(product_price);
                    productDetailDataForView.setProduct_description(product_description);
                    productDetailDataForView.setImage_encode(product_image_encode);
                    productDetailDataForView.setDiscount_price(product_discount_price);


                    Intent intent = new Intent(ProductActivity.this, ProductDetailActivity.class);
                    Gson gson = new Gson();
                    intent.putExtra("ProductDetailData", gson.toJson(productDetailDataForView));
                    intent.putExtra("String", "ProductActivity");
                    startActivity(intent);
                    finish();


                }
            });


        }

        @Override
        public int getItemCount() {
            return productDataForViewList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ImageView imgProduct;
            TextView txtProduceName;
            TextView txtProductPrice;
            TextView txtProductDiscountPrice;

            public ViewHolder(View itemView) {
                super(itemView);


                imgProduct = (ImageView) itemView.findViewById(R.id.product_ImgView);
                txtProduceName = (TextView) itemView.findViewById(R.id.txtproductName);
                txtProductPrice = (TextView) itemView.findViewById(R.id.txtproductPrice);
                txtProductDiscountPrice = (TextView) itemView.findViewById(R.id.txtproductDiscountPrice);
            }
        }
    }


    private void setProductRecyclerviewAdapter() {

        ProductRecyclerView = (RecyclerView) findViewById(R.id.product_grid_recycler_view);
        productAdapter = new ProductAdapter(productDataForViews, getApplicationContext());
        ProductRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ProductRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ProductRecyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();

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
                intent1.putExtra("Class", "ProductActivity");
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

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Home!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void RatioForLayouts(View view) {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        int heightRatioForCard = height / 4;

        CardView productCardView = (CardView) view.findViewById(R.id.productCardView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, heightRatioForCard);

        productCardView.setLayoutParams(params);


    }
}
