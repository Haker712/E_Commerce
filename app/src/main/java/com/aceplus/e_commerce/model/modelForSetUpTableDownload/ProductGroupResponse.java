package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/16/17.
 */

public class ProductGroupResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    private Integer aceplusStatusCode;
    @SerializedName("aceplusStatusMessage")
    @Expose
    private String aceplusStatusMessage;
    @SerializedName("data")
    @Expose
    private ProductGroupResponseObj productGroupResponseObj;

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

    public ProductGroupResponseObj getProductGroupResponseObj() {
        return productGroupResponseObj;
    }

    public void setProductGroupResponseObj(ProductGroupResponseObj productGroupResponseObj) {
        this.productGroupResponseObj = productGroupResponseObj;
    }
}
