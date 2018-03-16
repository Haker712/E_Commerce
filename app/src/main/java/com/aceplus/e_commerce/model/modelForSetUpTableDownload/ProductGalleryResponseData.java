package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/16/17.
 */

public class ProductGalleryResponseData {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("product_id")
    @Expose
    int product_id;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("image_encode")
    @Expose
    String image_encode;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_encode() {
        return image_encode;
    }

    public void setImage_encode(String image_encode) {
        this.image_encode = image_encode;
    }
}
