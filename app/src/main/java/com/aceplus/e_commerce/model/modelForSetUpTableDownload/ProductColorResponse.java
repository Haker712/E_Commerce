package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/16/17.
 */

public class ProductColorResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    private Integer aceplusStatusCode;
    @SerializedName("aceplusStatusMessage")
    @Expose
    private String aceplusStatusMessage;
    @SerializedName("data")
    @Expose
    private ProductColorResponseObj productColorResponseObj;


    public Integer getAceplusStatusCode() {
        return aceplusStatusCode;
    }

    public void setAceplusStatusCode(Integer aceplusStatusCode) {
        this.aceplusStatusCode = aceplusStatusCode;
    }

    public String getAceplusStatusMessage() {
        return aceplusStatusMessage;
    }

    public void setAceplusStatusMessage(String aceplusStatusMessage) {
        this.aceplusStatusMessage = aceplusStatusMessage;
    }

    public ProductColorResponseObj getProductColorResponseObj() {
        return productColorResponseObj;
    }

    public void setProductColorResponseObj(ProductColorResponseObj productColorResponseObj) {
        this.productColorResponseObj = productColorResponseObj;
    }
}
