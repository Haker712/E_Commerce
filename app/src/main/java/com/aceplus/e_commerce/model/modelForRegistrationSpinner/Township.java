package com.aceplus.e_commerce.model.modelForRegistrationSpinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/14/17.
 */

public class Township {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("township_name")
    @Expose
    private String townshipName;
    @SerializedName("town_id")
    @Expose
    private Integer townId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

}
