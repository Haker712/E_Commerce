package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class ProductGalleryResponseObj {

    @SerializedName("product_gallery")
    @Expose
    private List<ProductGalleryResponseData> productGalleryResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductGalleryResponseData> getProductGalleryResponseDatas() {
        return productGalleryResponseDatas;
    }

    public void setProductGalleryResponseDatas(List<ProductGalleryResponseData> productGalleryResponseDatas) {
        this.productGalleryResponseDatas = productGalleryResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
