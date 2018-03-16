package com.aceplus.e_commerce.recyclerviewAdaptersforHomeFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aceplus.e_commerce.ProductDetailActivity;
import com.aceplus.e_commerce.R;
import com.aceplus.e_commerce.model.modelForView.HomeProductData;
import com.aceplus.e_commerce.model.modelForView.ProductDetailDataForView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by phonelin on 6/20/17.
 */

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.ViewHolder> {


    Double Discount_Price;

    Context context;
    List<HomeProductData> homeProductDatas;

    ProductDetailDataForView productDetailDataforview;

    public ChildRecyclerAdapter(Context context, List<HomeProductData> homeProductDatas) {
        this.context = context;
        this.homeProductDatas = homeProductDatas;
    }

    @Override
    public ChildRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitemofhomefragment_childrecyclerview, null);
        ViewHolder vh = new ViewHolder(v);

        RatioForLayouts(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ChildRecyclerAdapter.ViewHolder holder, final int position) {

        Discount_Price = homeProductDatas.get(position).getDiscount_price();


        if (Discount_Price != 0) {

            holder.txtDiscountProductName.setText(homeProductDatas.get(position).getProduct_name());

            Double Cost_Price = homeProductDatas.get(position).getCost_price();

            holder.txtCostPrice.setText(String.valueOf(Cost_Price) + " MMK");
            holder.txtCostPrice.setPaintFlags(holder.txtCostPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtDiscountPrice.setText(String.valueOf(Discount_Price) + " MMK");

            byte[] decodeValue = Base64.decode(homeProductDatas.get(position).getImage_encode(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);

            holder.imgDiscountProduct.setImageBitmap(bitmap);

        } else {

            holder.txtDiscountProductName.setText(homeProductDatas.get(position).getProduct_name());

            Double Cost_Price = homeProductDatas.get(position).getCost_price();

            holder.txtCostPrice.setText(String.valueOf(Cost_Price) + " MMK");
            holder.txtDiscountPrice.setVisibility(View.GONE);

            byte[] decodeValue = Base64.decode(homeProductDatas.get(position).getImage_encode(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodeValue, 0, decodeValue.length);

            holder.imgDiscountProduct.setImageBitmap(bitmap);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, homeProductDatas.get(position).getProduct_name() + "", Toast.LENGTH_SHORT).show();

                productDetailDataforview = new ProductDetailDataForView();


                productDetailDataforview.setId(homeProductDatas.get(position).getProduct_group_id());
                productDetailDataforview.setProduct_name(homeProductDatas.get(position).getProduct_name());
                productDetailDataforview.setImage_encode(homeProductDatas.get(position).getImage_encode());
                productDetailDataforview.setProduct_price(homeProductDatas.get(position).getCost_price());
                productDetailDataforview.setProduct_description(homeProductDatas.get(position).getProduct_description());
                productDetailDataforview.setDiscount_price(homeProductDatas.get(position).getDiscount_price());


                Intent intent = new Intent(context, ProductDetailActivity.class);
                Gson gson = new Gson();
                intent.putExtra("ProductDetailData", gson.toJson(productDetailDataforview));
                intent.putExtra("String", "Main");
                context.startActivity(intent);
                ((Activity) context).finish();


            }
        });

    }

    @Override
    public int getItemCount() {
        if (homeProductDatas.size() <= 7) {

            return homeProductDatas.size();

        } else if (homeProductDatas.size() > 7) {

            return 7;

        } else {

            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDiscountProduct;
        TextView txtDiscountProductName, txtCostPrice, txtDiscountPrice;

        public ViewHolder(View itemView) {
            super(itemView);

            imgDiscountProduct = (ImageView) itemView.findViewById(R.id.imgdiscountProduct);
            txtDiscountProductName = (TextView) itemView.findViewById(R.id.txtDiscountProductName);
            txtCostPrice = (TextView) itemView.findViewById(R.id.txtCostPrice);
            txtDiscountPrice = (TextView) itemView.findViewById(R.id.txtDiscountPrice);

        }
    }

    public void RatioForLayouts(View view) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        int heightRatioForCard = height / 5;
        int widthRatioForCard = width / 3;

        CardView productCardView = (CardView) view.findViewById(R.id.card_view);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthRatioForCard, heightRatioForCard);

        productCardView.setLayoutParams(params);

    }
}
