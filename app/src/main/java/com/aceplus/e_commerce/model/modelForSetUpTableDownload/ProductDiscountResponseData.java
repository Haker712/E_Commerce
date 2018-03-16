package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/20/17.
 */

public class ProductDiscountResponseData {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("discount_type")
    @Expose
    String discount_type;

    @SerializedName("discount_amount")
    @Expose
    String discount_amount;

    @SerializedName("with_expiry_date")
    @Expose
    int with_expiry_date;

    @SerializedName("discount_from")
    @Expose
    String discount_from;

    @SerializedName("discount_to")
    @Expose
    String discount_to;

    @SerializedName("status")
    @Expose
    int status;

    @SerializedName("product_group_id")
    @Expose
    int product_group_id;


    @SerializedName("discount_name")
    @Expose
    String discount_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public int getWith_expiry_date() {
        return with_expiry_date;
    }

    public void setWith_expiry_date(int with_expiry_date) {
        this.with_expiry_date = with_expiry_date;
    }

    public String getDiscount_from() {
        return discount_from;
    }

    public void setDiscount_from(String discount_from) {
        this.discount_from = discount_from;
    }

    public String getDiscount_to() {
        return discount_to;
    }

    public void setDiscount_to(String discount_to) {
        this.discount_to = discount_to;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProduct_group_id() {
        return product_group_id;
    }

    public void setProduct_group_id(int product_group_id) {
        this.product_group_id = product_group_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }
}
