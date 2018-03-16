package com.aceplus.e_commerce.model.modelForLogin;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell on 1/2/2017.
 */

public class LoginRequest {

    @SerializedName("site_activation_key")
    @Expose
    private String siteActivationKey;
    @SerializedName("data")
    @Expose
    private LoginRequestData loginRequestData;

    public String getSiteActivationKey() {
        return siteActivationKey;
    }

    public void setSiteActivationKey(String siteActivationKey) {
        this.siteActivationKey = siteActivationKey;
    }

    public LoginRequestData getData() {
        return loginRequestData;
    }

    public void setData(LoginRequestData data) {
        this.loginRequestData = data;
    }


}
