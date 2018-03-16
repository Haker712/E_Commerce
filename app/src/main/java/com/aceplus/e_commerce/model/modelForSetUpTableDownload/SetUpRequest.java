package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/15/17.
 */

public class SetUpRequest {

    @SerializedName("site_activation_key")
    @Expose
    private String site_activation_key;


    @SerializedName("data")
    @Expose

    private List<SetUpRequest_Data> setUpRequestDataList = null;

    public String getSite_activation_key() {
        return site_activation_key;
    }

    public void setSite_activation_key(String site_activation_key) {
        this.site_activation_key = site_activation_key;
    }

    public List<SetUpRequest_Data> getSetUpRequestDataList() {
        return setUpRequestDataList;
    }

    public void setSetUpRequest_data(List<SetUpRequest_Data> setUpRequest_dataList) {
        this.setUpRequestDataList = setUpRequest_dataList;
    }
}
