package com.aceplus.e_commerce.model.modelforAddtoCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 5/29/17.
 */

public class AddtoCartData {

    @SerializedName("customer_id")
    @Expose
    private int customer_id;


    @SerializedName("product_id")
    @Expose
    private int product_id;


    @SerializedName("quantity")
    @Expose
    private int quantity;


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
