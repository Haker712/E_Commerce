package com.aceplus.e_commerce.NaviExpListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.e_commerce.ProductActivity;
import com.aceplus.e_commerce.R;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.Sub1CategoryData;
import com.aceplus.e_commerce.model.CategoryModelsForNavi.Sub2CategoryData;
import com.aceplus.e_commerce.model.modelForView.ProductDataForView;
import com.aceplus.e_commerce.utils.MyDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by phonelin on 7/17/17.
 */
public class SecondLevelAdapter extends BaseExpandableListAdapter {

    SQLiteDatabase sqLiteDatabase;
    List<ProductDataForView> productDataForViewList;
    ProductDataForView productDataForView;
    String product_name, product_description, image_encode, product_discount_type;
    Double product_price;
    double product_discount_amount, product_discount_price;
    int product_group_id;

    private Context context;
    private Sub1CategoryData secondList;
    List<Sub2CategoryData> thirdList;

    public SecondLevelAdapter(Context context, Sub1CategoryData secondList, List<Sub2CategoryData> thirdList) {
        this.context = context;
        this.secondList = secondList;
        this.thirdList = thirdList;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            TextView text = (TextView) convertView.findViewById(R.id.lblListItem);
            text.setText(secondList.getSub1_Name());


            /***
             *SecondLevel OnClick
             */

            if (secondList.getSub2CategoryDataList() == null || secondList.getSub2CategoryDataList().size() == 0) {

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(context, secondList.getSub1_id() + "", Toast.LENGTH_SHORT).show();

                        RetrieveDataforProduct(secondList.getSub1_id());

                    }
                });

            }


        }
        return convertView;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.third_item, null);
            TextView text = (TextView) convertView.findViewById(R.id.thirdListItem);
            text.setText(thirdList.get(childPosition).getSub2_Name());

            /***
             * thirdLevel Onclick
             */

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, thirdList.get(childPosition).getSub2_id() + "", Toast.LENGTH_SHORT).show();


                    RetrieveDataforProduct(thirdList.get(childPosition).getSub2_id());


                }
            });
        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (thirdList != null) {
            return thirdList.size();
        } else {
            return 0;
        }

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void RetrieveDataforProduct(int CatId) {

        sqLiteDatabase = new MyDatabase(context).getDataBase();

        productDataForViewList = new ArrayList<>();


        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM product_categories where category_id=" + CatId + "", null);

        Log.i("CursorCount", cursor.getCount() + "");


        while (cursor.moveToNext()) {

            productDataForView = new ProductDataForView();


            product_group_id = cursor.getInt(cursor.getColumnIndex("product_group_id"));

            Cursor cur2 = sqLiteDatabase.rawQuery("SELECT * FROM product_group where id=" + product_group_id + "", null);

            while (cur2.moveToNext()) {

                product_name = cur2.getString(cur2.getColumnIndex("product_name"));
                product_description = cur2.getString(cur2.getColumnIndex("description"));
                product_price = cur2.getDouble(cur2.getColumnIndex("cost_price"));

            }

            Cursor cur3 = sqLiteDatabase.rawQuery("SELECT * FROM product_gallery where product_id=" + product_group_id + "", null);

            while (cur3.moveToNext()) {

                image_encode = cur3.getString(cur3.getColumnIndex("image_encode"));

            }

            Cursor cur4 = sqLiteDatabase.rawQuery("SELECT * FROM product_discount where product_group_id=" + product_group_id + "", null);

            Log.i("Cur4Count",cur4.getCount()+"");

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
            }else {

                product_discount_price=0.0;
            }

            productDataForView.setId(product_group_id);
            productDataForView.setProduct_name(product_name);
            productDataForView.setCost_price(product_price);
            productDataForView.setDescription(product_description);
            productDataForView.setImage_encode(image_encode);
            productDataForView.setDiscount_price(product_discount_price);
            Log.i("Discount Price",product_discount_price+"");

            productDataForViewList.add(productDataForView);

            Intent intent = new Intent(context, ProductActivity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", (Serializable) productDataForViewList);
            intent.putExtra("BUNDLE", args);
            context.startActivity(intent);
            ((Activity) context).finish();

        }




    }


}
