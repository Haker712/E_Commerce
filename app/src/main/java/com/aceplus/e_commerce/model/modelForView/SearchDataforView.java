package com.aceplus.e_commerce.model.modelForView;

/**
 * Created by phonelin on 7/26/17.
 */

public class SearchDataforView {

    int id;

    String product_name;

    double cost_price;

    String description;

    String image_encode;

    double discount_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_encode() {
        return image_encode;
    }

    public void setImage_encode(String image_encode) {
        this.image_encode = image_encode;
    }

    public double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(double discount_price) {
        this.discount_price = discount_price;
    }
}
