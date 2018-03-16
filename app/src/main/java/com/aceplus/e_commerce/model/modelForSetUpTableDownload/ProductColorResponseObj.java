package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class ProductColorResponseObj {

    @SerializedName("product_color")
    @Expose
    private List<ProductColorResponseData> productColorResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductColorResponseData> getProductColorResponseDatas() {
        return productColorResponseDatas;
    }

    public void setProductColorResponseDatas(List<ProductColorResponseData> productColorResponseDatas) {
        this.productColorResponseDatas = productColorResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
