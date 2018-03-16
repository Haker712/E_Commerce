package com.aceplus.e_commerce;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aceplus.e_commerce.model.modelForView.ProductDataForView;
import com.aceplus.e_commerce.model.modelForView.SearchDataforView;
import com.aceplus.e_commerce.utils.Constant;
import com.aceplus.e_commerce.utils.MyDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity implements android.widget.SearchView.OnQueryTextListener {

    SQLiteDatabase sqLiteDatabase;

    android.widget.SearchView searchView;

    RecyclerView searchRecyclerView;
    SearchAdapter searchAdapter;
    SearchDataforView searchDataforView;
    List<SearchDataforView> searchDataforViewList;

    String product_name = "", product_description = "", image_encode = "", product_discount_type;
    Double product_price = 0.0;
    Double product_discount_amount, product_discount_price = 0.0;
    int product_group_id = 0;

    TextView textViewMessage;


    /***
     * For Testing
     * @param savedInstanceState
     */

    TextView textViewIP;
    SharedPreferences sharedpreferences;
    public static final String MY_PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        sqLiteDatabase = new MyDatabase(this).getDataBase();

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search");

        searchView = (android.widget.SearchView) findViewById(R.id.SearchView);
        searchView.setOnQueryTextListener(this);

        textViewMessage = (TextView) findViewById(R.id.textViewMessage);





        findViewById(R.id.IpConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Constant.BASE_URL = textViewIP.getText().toString();
                // MY_PREFS_NAME - a static String variable like:
//public static final String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(MY_PREFS_NAME, textViewIP.getText().toString());
                editor.commit();

                Constant.BASE_URL=textViewIP.getText().toString();
            }
        });





    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;

        Log.i("Text", text);

        searchDataforViewList = new ArrayList<>();
        if (text.length() == 0) {

            textViewMessage.setVisibility(View.VISIBLE);
            textViewMessage.setText("No Search Data");
            searchDataforViewList.clear();
            searchRecyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);
            searchAdapter = new SearchAdapter(searchDataforViewList, getApplicationContext());
            searchRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
            searchRecyclerView.setAdapter(searchAdapter);
            searchRecyclerView.setVisibility(View.VISIBLE);
            searchAdapter.notifyDataSetChanged();

        } else {

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM product_group WHERE product_name LIKE '%" + text + "%'", null);


            while (cursor.moveToNext()) {

                searchDataforView = new SearchDataforView();

                product_group_id = cursor.getInt(cursor.getColumnIndex("id"));
                product_name = cursor.getString(cursor.getColumnIndex("product_name"));
                product_description = cursor.getString(cursor.getColumnIndex("description"));
                product_price = cursor.getDouble(cursor.getColumnIndex("cost_price"));

                Log.i("product_group_id", product_group_id + "");
                Log.i("product_name", product_name);


                Cursor cur3 = sqLiteDatabase.rawQuery("SELECT * FROM product_gallery where product_id=" + product_group_id + "", null);

                while (cur3.moveToNext()) {

                    image_encode = cur3.getString(cur3.getColumnIndex("image_encode"));

                }

                Cursor cur4 = sqLiteDatabase.rawQuery("SELECT * FROM product_discount where product_group_id=" + product_group_id + "", null);

                Log.i("Cur4Count", cur4.getCount() + "");

                if (cur4.getCount() != 0) {

                    while (cur4.moveToNext()) {

                        product_discount_amount = cur4.getDouble(cur4.getColumnIndex("discount_amount"));
                        product_discount_type = cur4.getString(cur4.getColumnIndex("discount_type"));

                        if (product_discount_type.equalsIgnoreCase("%")) {

                            product_discount_amount = (product_discount_amount / 100) * product_price;
                            product_discount_price = product_price - product_discount_amount;

                        } else {

                            product_discount_price = product_price - product_discount_amount;

                        }


                    }
                } else {

                    product_discount_price = 0.0;
                }

                searchDataforView.setId(product_group_id);
                searchDataforView.setProduct_name(product_name);
                searchDataforView.setCost_price(product_price);
                searchDataforView.setDescription(product_description);
                searchDataforView.setImage_encode(image_encode);
                searchDataforView.setDiscount_price(product_discount_price);
                Log.i("Discount Price", product_discount_price + "");

                searchDataforViewList.add(searchDataforView);

            }

            Log.i("searchDataforViewList", searchDataforViewList.size() + "");
            if (searchDataforViewList.size() > 0) {
                textViewMessage.setVisibility(View.GONE);

                searchRecyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);
                searchAdapter = new SearchAdapter(searchDataforViewList, getApplicationContext());
                searchRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
                searchRecyclerView.setAdapter(searchAdapter);
                searchRecyclerView.setVisibility(View.VISIBLE);
                searchAdapter.notifyDataSetChanged();
                Log.i("here", "setAdapter");
            } else if (searchDataforViewList.size() == 0 || newText.length() == 0) {

                textViewMessage.setVisibility(View.VISIBLE);
                textViewMessage.setText("No Result Found");
                searchDataforViewList.clear();
                searchRecyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);
                searchAdapter = new SearchAdapter(searchDataforViewList, getApplicationContext());
                searchRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
                searchRecyclerView.setAdapter(searchAdapter);
                searchRecyclerView.setVisibility(View.VISIBLE);
                searchAdapter.notifyDataSetChanged();

            }
        }
//        else if (searchView.toString().equalsIgnoreCase("")){
//
//
//            textViewMessage.setVisibility(View.VISIBLE);
//            searchDataforViewList.clear();
//            searchRecyclerView.setVisibility(View.GONE);
//            searchAdapter.notifyDataSetChanged();
//
//        }

        return true;
    }

    public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

        private List<SearchDataforView> searchDataforViewList = new ArrayList<>();
        LayoutInflater layoutInflater;
        Context context;

        public SearchAdapter(List<SearchDataforView> searchDataforViewList, Context context) {

            this.searchDataforViewList = searchDataforViewList;
            layoutInflater = LayoutInflater.from(context);
            this.context = context;

        }

        @Override
        public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.search_cardview, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);

            RatioForLayouts(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {

            product_name = searchDataforViewList.get(position).getProduct_name();
            product_price = searchDataforViewList.get(position).getCost_price();
            product_discount_price = searchDataforViewList.get(position).getDiscount_price();


            byte[] decodeValue = Base64.decode(searchDataforViewList.get(position).getImage_encode(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);


            holder.imgProduct.setImageBitmap(bitmap);
            holder.txtProduceName.setText(product_name);

            if (product_discount_price.equals(0.0)) {


                holder.txtProductPrice.setText(String.valueOf(product_price + "MMK"));
                holder.txtProductPrice.setPaintFlags(0);
                holder.txtProductDiscountPrice.setVisibility(View.GONE);

            } else {

                holder.txtProductPrice.setText(String.valueOf(product_price + "MMK"));
                holder.txtProductPrice.setPaintFlags(holder.txtProductPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                holder.txtProductDiscountPrice.setVisibility(View.VISIBLE);
                holder.txtProductDiscountPrice.setText(String.valueOf(product_discount_price) + "MMK");

            }


        }

        @Override
        public int getItemCount() {
            return searchDataforViewList.size();
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


    public void onBackPressed() {
        Intent intent;

        intent = new Intent(this, MainActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST", ProductActivity.productDataForViews);
        intent.putExtra("BUNDLE", args);
        startActivity(intent);
        finish();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:

                onBackPressed();

        }

        return super.onOptionsItemSelected(item);
    }

    public void RatioForLayouts(View view) {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        int heightRatioForCard = height / 4;

        CardView searchproductCardView = (CardView) view.findViewById(R.id.searchCardView);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, heightRatioForCard);

        searchproductCardView.setLayoutParams(params);


    }


}
