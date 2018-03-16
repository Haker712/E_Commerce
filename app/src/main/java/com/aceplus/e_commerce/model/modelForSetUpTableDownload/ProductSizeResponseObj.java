package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class ProductSizeResponseObj {

    @SerializedName("product_size")
    @Expose
    private List<ProductSizeResponseData> productSizeResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductSizeResponseData> getProductSizeResponseDatas() {
        return productSizeResponseDatas;
    }

    public void setProductSizeResponseDatas(List<ProductSizeResponseData> productSizeResponseDatas) {
        this.productSizeResponseDatas = productSizeResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
