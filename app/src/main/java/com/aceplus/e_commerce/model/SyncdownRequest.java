package com.aceplus.e_commerce.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ppa on 12/15/16.
 */

public class SyncdownRequest {


    public String getSite_activation_key() {
        return site_activation_key;
    }

    public void setSite_activation_key(String site_activation_key) {
        this.site_activation_key = site_activation_key;
    }

    public Request_Data getData() {
        return requestData;
    }

    public void setData(Request_Data requestData) {
        this.requestData = requestData;
    }

    //    {"site_activation_key":"123456","data":{"country":"1","state":"1","town":"1","township":"1"}}
    @SerializedName("site_activation_key")
    @Expose
    private String site_activation_key;


    @SerializedName("data")
    @Expose
    private Request_Data requestData;

}
