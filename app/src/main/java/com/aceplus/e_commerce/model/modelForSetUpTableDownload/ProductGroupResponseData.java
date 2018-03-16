package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/16/17.
 */

public class ProductGroupResponseData {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("model")
    @Expose
    String model;

    @SerializedName("group_code")
    @Expose
    String group_code;

    @SerializedName("brand_id")
    @Expose
    int brand_id;

    @SerializedName("has_variance")
    @Expose
    int has_variance;

    @SerializedName("product_name")
    @Expose
    String product_name;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("status")
    @Expose
    int status;

    @SerializedName("product_sku")
    @Expose
    String product_sku;

    @SerializedName("manufacturer_id")
    @Expose
    int manufacturer_id;

    @SerializedName("cost_price")
    @Expose
    double cost_price;

    @SerializedName("base_price")
    @Expose
    double base_price;

    @SerializedName("weight")
    @Expose
    String weight;

    @SerializedName("length")
    @Expose
    String length;

    @SerializedName("width")
    @Expose
    String width;

    @SerializedName("height")
    @Expose
    String height;


    @SerializedName("product_color_id")
    @Expose
    String product_color_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGroup_code() {
        return group_code;
    }

    public void setGroup_code(String group_code) {
        this.group_code = group_code;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getHas_variance() {
        return has_variance;
    }

    public void setHas_variance(int has_variance) {
        this.has_variance = has_variance;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProduct_sku() {
        return product_sku;
    }

    public void setProduct_sku(String product_sku) {
        this.product_sku = product_sku;
    }

    public int getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(int manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public double getCost_price() {
        return cost_price;
    }

    public void setCost_price(double cost_price) {
        this.cost_price = cost_price;
    }

    public double getBase_price() {
        return base_price;
    }

    public void setBase_price(double base_price) {
        this.base_price = base_price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getProduct_color_id() {
        return product_color_id;
    }

    public void setProduct_color_id(String product_color_id) {
        this.product_color_id = product_color_id;
    }
}
