package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/20/17.
 */

public class ProductDiscountResponseObj {

    @SerializedName("product_discount")
    @Expose
    private List<ProductDiscountResponseData> productDiscountResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;

    public List<ProductDiscountResponseData> getProductDiscountResponseDatas() {
        return productDiscountResponseDatas;
    }

    public void setProductDiscountResponseDatas(List<ProductDiscountResponseData> productDiscountResponseDatas) {
        this.productDiscountResponseDatas = productDiscountResponseDatas;
    }


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
