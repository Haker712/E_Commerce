package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class ProductResponseObj {

    @SerializedName("products")
    @Expose
    private List<ProductResponseData> productResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductResponseData> getProductResponseDatas() {
        return productResponseDatas;
    }

    public void setProductResponseDatas(List<ProductResponseData> productResponseDatas) {
        this.productResponseDatas = productResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
