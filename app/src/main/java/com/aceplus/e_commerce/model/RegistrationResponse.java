package com.aceplus.e_commerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/14/17.
 */

public class RegistrationResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    private int aceplusStatusCode;
    @SerializedName("aceplusStatusMessage")
    @Expose
    private String aceplusStatusMessage;


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

}
