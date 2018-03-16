package com.aceplus.e_commerce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.e_commerce.model.modelForView.ProductDetailDataForView;
import com.aceplus.e_commerce.model.modelForView.WishlistdataforView;
import com.aceplus.e_commerce.utils.MyDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.aceplus.e_commerce.R.id.productCardView;

public class WishListActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;

    int id, product_id;
    double product_price, product_discount_amount, product_sell_price;
    String product_name, image_encode, product_discount_type;

    WishlistdataforView wishlistdataforView;
    List<WishlistdataforView> wishlistdataforViewList = new ArrayList<>();

    WishlistAdapter wishlistAdapter;
    RecyclerView wishlistRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        sqLiteDatabase = new MyDatabase(this).getDataBase();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Wish List");

        wishlistRecyclerView = (RecyclerView) findViewById(R.id.wishlist_recycler_view);

        SetCartAdapter();

    }

    public void RetrieveWishListData() {

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM wishlist ", null);

        while (cursor.moveToNext()) {

            wishlistdataforView = new WishlistdataforView();

            id = cursor.getInt(cursor.getColumnIndex("id"));
            Log.i("Id", id + "");
            product_id = cursor.getInt(cursor.getColumnIndex("product_id"));

            Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM product_group  WHERE id='" + product_id + "'", null);

            while (cursor1.moveToNext()) {

                product_name = cursor1.getString(cursor1.getColumnIndex("product_name"));
                Log.i("pname", product_name);
                product_price = cursor1.getDouble(cursor1.getColumnIndex("cost_price"));
                Log.i("pprice", product_price + "");


            }

            Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM product_gallery WHERE product_id='" + product_id + "'", null);

            while (cursor2.moveToNext()) {

                image_encode = cursor2.getString(cursor2.getColumnIndex("image_encode"));

            }
            Cursor cursor3 = sqLiteDatabase.rawQuery("SELECT * FROM product_discount WHERE product_group_id = " + product_id + "", null);

            if (cursor3.getCount() != 0) {

                while (cursor3.moveToNext()) {

                    product_discount_amount = cursor3.getDouble(cursor3.getColumnIndex("discount_amount"));
                    product_discount_type = cursor3.getString(cursor3.getColumnIndex("discount_type"));

                    if (product_discount_type.equalsIgnoreCase("%")) {

                        product_discount_amount = (product_discount_amount / 100) * product_price;
                        product_sell_price = product_price - product_discount_amount;
                        Log.i("psellprice", product_sell_price + "");

                    } else {

                        product_sell_price = product_price - product_discount_amount;
                        Log.i("psellprice", product_sell_price + "");

                    }


                }
            } else {

                product_sell_price = product_price;
            }


            wishlistdataforView.setId(id);
            wishlistdataforView.setProduct_id(product_id);
            wishlistdataforView.setProduct_name(product_name);
            wishlistdataforView.setProduct_price(product_sell_price);
            wishlistdataforView.setImage_encode(image_encode);

            wishlistdataforViewList.add(wishlistdataforView);
            Log.i("size", wishlistdataforViewList.size() + "");

        }

    }

    public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

        Context context;
        List<WishlistdataforView> wishlistdataforViewList;
        LayoutInflater layoutInflater;

        public WishlistAdapter(Context context, List<WishlistdataforView> wishlistdataforViewList) {

            this.context = context;
            this.wishlistdataforViewList = wishlistdataforViewList;
            layoutInflater = LayoutInflater.from(context);

        }

        @Override
        public WishlistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.wishlist_cardview, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(WishlistAdapter.ViewHolder holder, final int position) {

            holder.txtname.setText(wishlistdataforViewList.get(position).getProduct_name());
            holder.txtprice.setText(String.valueOf(wishlistdataforViewList.get(position).getProduct_price()));

            byte[] decodeValue = Base64.decode(wishlistdataforViewList.get(position).getImage_encode(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);

            holder.imageView.setImageBitmap(bitmap);

            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int remove_id = wishlistdataforViewList.get(position).getId();

                    sqLiteDatabase.beginTransaction();

                    sqLiteDatabase.execSQL("DELETE FROM wishlist WHERE id=" + remove_id + "");

                    sqLiteDatabase.setTransactionSuccessful();
                    sqLiteDatabase.endTransaction();

                    wishlistdataforViewList.remove(position);
                    wishlistAdapter.notifyDataSetChanged();

                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ProductDetailDataForView productDetailDataForView = new ProductDetailDataForView();


                }
            });

        }

        @Override
        public int getItemCount() {
            return wishlistdataforViewList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView txtname, txtprice;
            ImageView imageView;
            Button remove;

            public ViewHolder(View itemView) {
                super(itemView);

                txtname = (TextView) itemView.findViewById(R.id.txtWishProductName);
                txtprice = (TextView) itemView.findViewById(R.id.txtWishProductPrice);
                imageView = (ImageView) itemView.findViewById(R.id.imgWish);
                remove = (Button) itemView.findViewById(R.id.btnWishRemove);

            }
        }
    }

    public void SetCartAdapter() {

        RetrieveWishListData();


        wishlistAdapter = new WishlistAdapter(this, wishlistdataforViewList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        wishlistRecyclerView.setLayoutManager(layoutManager);
        wishlistRecyclerView.setItemAnimator(new DefaultItemAnimator());
        wishlistRecyclerView.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();


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
                intent1.putExtra("Class", "WishListActivity");
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
        Intent intent = new Intent(this, User.class);
        startActivity(intent);
        finish();
    }

}

