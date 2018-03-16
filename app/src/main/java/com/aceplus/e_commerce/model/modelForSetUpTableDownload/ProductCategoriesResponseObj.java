package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/22/17.
 */

public class ProductCategoriesResponseObj {

    @SerializedName("product_categories")
    @Expose
    private List<ProductCategoriesResponseData> productCategoriesResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductCategoriesResponseData> getProductCategoriesResponseDatas() {
        return productCategoriesResponseDatas;
    }

    public void setProductCategoriesResponseDatas(List<ProductCategoriesResponseData> productCategoriesResponseDatas) {
        this.productCategoriesResponseDatas = productCategoriesResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
