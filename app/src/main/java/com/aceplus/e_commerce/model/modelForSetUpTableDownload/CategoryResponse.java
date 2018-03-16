package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/15/17.
 */

public class CategoryResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    private int aceplusStatusCode;
    @SerializedName("aceplusStatusMessage")
    @Expose
    private String aceplusStatusMessage;
    @SerializedName("data")
    @Expose
    private CategoryResponseObj categoryResponseObj;

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

    public CategoryResponseObj getCategoryResponseObj() {
        return categoryResponseObj;
    }

    public void setCategoryResponseObj(CategoryResponseObj categoryResponseObj) {
        this.categoryResponseObj = categoryResponseObj;
    }
}
