package com.aceplus.e_commerce.model.modelForRegistrationSpinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/14/17.
 */

public class State {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("state_name")
    @Expose
    private String stateName;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

}
