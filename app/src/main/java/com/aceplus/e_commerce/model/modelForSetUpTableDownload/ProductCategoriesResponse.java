package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/22/17.
 */

public class ProductCategoriesResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    private int aceplusStatusCode;
    @SerializedName("aceplusStatusMessage")
    @Expose
    private String aceplusStatusMessage;
    @SerializedName("data")
    @Expose
    private ProductCategoriesResponseObj productCategoriesResponseObj;

    public int getAceplusStatusCode() {
        return aceplusStatusCode;
    }

    public void setAceplusStatusCode(int aceplusStatusCode) {
        this.aceplusStatusCode = aceplusStatusCode;
    }

    public String getAceplusStatusMessage() {
        return aceplusStatusMessage;
    }

    public void setAceplusStatusMessage(String aceplusStatusMessage) {
        this.aceplusStatusMessage = aceplusStatusMessage;
    }

    public ProductCategoriesResponseObj getProductCategoriesResponseObj() {
        return productCategoriesResponseObj;
    }

    public void setProductCategoriesResponseObj(ProductCategoriesResponseObj productCategoriesResponseObj) {
        this.productCategoriesResponseObj = productCategoriesResponseObj;
    }
}
