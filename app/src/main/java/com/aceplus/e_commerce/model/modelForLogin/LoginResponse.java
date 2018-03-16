package com.aceplus.e_commerce.model.modelForLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/2/2017.
 */
public class LoginResponse {

    @SerializedName("aceplusStatusCode")
    @Expose
    private Integer aceplusStatusCode;
    @SerializedName("aceplusStatusMessage")
    @Expose
    private String aceplusStatusMessage;
    @SerializedName("userId")
    @Expose
    private int uerId;


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

    public Integer getUerId() {
        return uerId;
    }

    public void setUerId(Integer uerId) {
        this.uerId = uerId;
    }
}
