package com.aceplus.e_commerce.recyclerviewAdaptersforHomeFragment;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aceplus.e_commerce.ProductActivity;
import com.aceplus.e_commerce.R;
import com.aceplus.e_commerce.model.modelForView.HomeProductData;
import com.aceplus.e_commerce.model.modelForView.ProductDataForView;
import com.aceplus.e_commerce.utils.MyDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phonelin on 6/20/17.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

    Context context;

    List<HomeProductData> homeProductDataList1 = new ArrayList<>();
    List<HomeProductData> homeProductDataList2 = new ArrayList<>();

    ChildRecyclerAdapter childRecyclerAdapter;

    SQLiteDatabase sqLiteDatabase;

    HomeProductData homeProductData1, homeProductData2;

    String product_name = "", image_encode, product_description;
    Double cost_price = 0.0, discount_price = 0.0;

    ProductDataForView productDataForView;

    ProgressBar pb;

    public MainRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listofhomefragment_mainrecyclerview, null);
        ViewHolder VH = new ViewHolder(v);

        pb = (ProgressBar) v.findViewById(R.id.progressbar);
        pb.setVisibility(View.VISIBLE);
        return VH;
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapter.ViewHolder holder, final int position) {

        sqLiteDatabase = new MyDatabase(context).getDataBase();


        if (position == 0) {

            homeProductDataList1.clear();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM product_discount", null);

            while (cursor.moveToNext()) {

                homeProductData1 = new HomeProductData();

                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int product_group_id = cursor.getInt(cursor.getColumnIndex("product_group_id"));
                double discount_amount = cursor.getDouble(cursor.getColumnIndex("discount_amount"));
                Log.i("DiscountAmount", discount_amount + "");
                String discount_type = cursor.getString(cursor.getColumnIndex("discount_type"));
                Log.i("DiscountType", discount_type);

                Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM product_group WHERE id='" + product_group_id + "'", null);

                while (cursor1.moveToNext()) {

                    product_name = cursor1.getString(cursor1.getColumnIndex("product_name"));
                    cost_price = cursor1.getDouble(cursor1.getColumnIndex("cost_price"));
                    product_description = cursor1.getString(cursor1.getColumnIndex("description"));


                }

                Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM product_gallery WHERE product_id='" + product_group_id + "'", null);

                while (cursor2.moveToNext()) {

                    image_encode = cursor2.getString(cursor2.getColumnIndex("image_encode"));

                }

                if (discount_type.equalsIgnoreCase("%")) {

                    discount_amount = (discount_amount / 100) * cost_price;

                }

                discount_price = cost_price - discount_amount;


                homeProductData1.setDiscount_price(discount_price);
                homeProductData1.setId(id);
                homeProductData1.setProduct_name(product_name);
                homeProductData1.setImage_encode(image_encode);
                homeProductData1.setCost_price(cost_price);
                homeProductData1.setProduct_description(product_description);
                homeProductData1.setProduct_group_id(product_group_id);

                homeProductDataList1.add(homeProductData1);

                childRecyclerAdapter = new ChildRecyclerAdapter(context, homeProductDataList1);

                pb.setVisibility(View.GONE);
            }

            holder.chileRecyclerView.setAdapter(childRecyclerAdapter);
            holder.chileRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.txtTitle.setText("Sales");

            holder.btnViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<ProductDataForView> productDataForViewList = new ArrayList<>();


                    for (int i = 0; i < homeProductDataList1.size(); i++) {

                        productDataForView = new ProductDataForView();

                        productDataForView.setId(homeProductDataList1.get(i).getProduct_group_id());
                        productDataForView.setProduct_name(homeProductDataList1.get(i).getProduct_name());
                        productDataForView.setImage_encode(homeProductDataList1.get(i).getImage_encode());
                        productDataForView.setCost_price(homeProductDataList1.get(i).getCost_price());
                        productDataForView.setDescription(homeProductDataList1.get(i).getProduct_description());
                        productDataForView.setDiscount_price(homeProductDataList1.get(i).getDiscount_price());

                        productDataForViewList.add(productDataForView);
                    }


                    Intent intent = new Intent(context, ProductActivity.class);
                    Bundle args = new Bundle();
                    args.putSerializable("ARRAYLIST", (Serializable) productDataForViewList);
                    intent.putExtra("BUNDLE", args);
                    context.startActivity(intent);


                }
            });


        }
        if (position == 1) {

            homeProductDataList1.clear();

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM product_discount", null);

            while (cursor.moveToNext()) {

                homeProductData1 = new HomeProductData();

                int id = cursor.getInt(cursor.getColumnIndex("id"));
                int product_group_id = cursor.getInt(cursor.getColumnIndex("product_group_id"));
                double discount_amount = cursor.getDouble(cursor.getColumnIndex("discount_amount"));
                String discount_type = cursor.getString(cursor.getColumnIndex("discount_type"));

                Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT * FROM product_group WHERE id='" + product_group_id + "'", null);

                while (cursor1.moveToNext()) {

                    product_name = cursor1.getString(cursor1.getColumnIndex("product_name"));
                    cost_price = cursor1.getDouble(cursor1.getColumnIndex("cost_price"));


                }

                Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT * FROM product_description WHERE product_group_id='" + product_group_id + "'", null);

                while (cursor2.moveToNext()) {

                    product_description = cursor2.getString(cursor2.getColumnIndex("description"));

                }

                Cursor cursor3 = sqLiteDatabase.rawQuery("SELECT * FROM product_gallery WHERE product_id='" + product_group_id + "'", null);

                while (cursor3.moveToNext()) {

                    image_encode = cursor3.getString(cursor3.getColumnIndex("image_encode"));

                }

                if (discount_type.equalsIgnoreCase("%")) {

                    discount_amount = (discount_amount / 100) * cost_price;

                }

                discount_price = cost_price - discount_amount;


                homeProductData1.setDiscount_price(discount_price);
                homeProductData1.setId(id);
                homeProductData1.setProduct_name(product_name);
                homeProductData1.setImage_encode(image_encode);
                homeProductData1.setCost_price(cost_price);
                homeProductData1.setProduct_description(product_description);
                homeProductData1.setProduct_group_id(product_group_id);

                homeProductDataList1.add(homeProductData1);

                childRecyclerAdapter = new ChildRecyclerAdapter(context, homeProductDataList1);

                pb.setVisibility(View.GONE);
            }

            holder.chileRecyclerView.setAdapter(childRecyclerAdapter);
            holder.chileRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            holder.txtTitle.setText("New Arrivals");

            holder.btnViewAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<ProductDataForView> productDataForViewList = new ArrayList<>();


                    for (int i = 0; i < homeProductDataList1.size(); i++) {

                        productDataForView = new ProductDataForView();

                        productDataForView.setId(homeProductDataList1.get(i).getProduct_group_id());
                        productDataForView.setProduct_name(homeProductDataList1.get(i).getProduct_name());
                        productDataForView.setImage_encode(homeProductDataList1.get(i).getImage_encode());
                        productDataForView.setCost_price(homeProductDataList1.get(i).getCost_price());
                        productDataForView.setDescription(homeProductDataList1.get(i).getProduct_description());
                        productDataForView.setDiscount_price(homeProductDataList1.get(i).getDiscount_price());

                        productDataForViewList.add(productDataForView);
                    }


//                    Intent intent = new Intent(context, AllProductActivity.class);
//                    Bundle args = new Bundle();
//                    args.putSerializable("ARRAYLIST", (Serializable) productDataForViewList);
//                    intent.putExtra("BUNDLE", args);
//                    context.startActivity(intent);


                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView chileRecyclerView;
        TextView txtTitle;
        Button btnViewAll;

        public ViewHolder(View itemView) {
            super(itemView);

            chileRecyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);
            txtTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            btnViewAll = (Button) itemView.findViewById(R.id.btnMore);
        }
    }


}

