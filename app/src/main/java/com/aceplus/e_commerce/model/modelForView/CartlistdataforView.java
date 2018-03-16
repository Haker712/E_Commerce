package com.aceplus.e_commerce.model.modelForView;

/**
 * Created by phonelin on 6/2/17.
 */

public class CartlistdataforView {

    int id;

    int product_id;

    String product_name;

    double sell_price;

    String image_encode;

    int qty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) {
        this.sell_price = sell_price;
    }

    public String getImage_encode() {
        return image_encode;
    }

    public void setImage_encode(String image_encode) {
        this.image_encode = image_encode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
