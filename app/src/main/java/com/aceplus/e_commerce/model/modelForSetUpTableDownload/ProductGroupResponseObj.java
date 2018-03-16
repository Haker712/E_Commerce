package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class ProductGroupResponseObj {

    @SerializedName("product_group")
    @Expose
    private List<ProductGroupResponseData> productGroupResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductGroupResponseData> getProductGroupResponseDatas() {
        return productGroupResponseDatas;
    }

    public void setProductGroupResponseDatas(List<ProductGroupResponseData> productGroupResponseDatas) {
        this.productGroupResponseDatas = productGroupResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
