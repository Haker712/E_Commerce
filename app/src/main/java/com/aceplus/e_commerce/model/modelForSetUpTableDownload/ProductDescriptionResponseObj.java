package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class ProductDescriptionResponseObj {

    @SerializedName("product_description")
    @Expose
    private List<ProductDescriptionResponseData> productDescriptionResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductDescriptionResponseData> getProductDescriptionResponseDatas() {
        return productDescriptionResponseDatas;
    }

    public void setProductDescriptionResponseDatas(List<ProductDescriptionResponseData> productDescriptionResponseDatas) {
        this.productDescriptionResponseDatas = productDescriptionResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
