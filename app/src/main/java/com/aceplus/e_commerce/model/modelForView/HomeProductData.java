package com.aceplus.e_commerce.model.modelForView;

import java.io.Serializable;

/**
 * Created by phonelin on 6/20/17.
 */

public class HomeProductData  implements Serializable {

    int id;

    double cost_price;

    double discount_price;

    String image_encode;

    String product_name;

    String product_description;

    int product_group_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }

    public String getImage_encode() {
        return image_encode;
    }

    public void setImage_encode(String image_encode) {
        this.image_encode = image_encode;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getProduct_group_id() {
        return product_group_id;
    }

    public void setProduct_group_id(int product_group_id) {
        this.product_group_id = product_group_id;
    }
}
