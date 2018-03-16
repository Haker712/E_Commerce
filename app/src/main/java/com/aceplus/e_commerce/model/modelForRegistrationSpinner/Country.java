package com.aceplus.e_commerce.model.modelForRegistrationSpinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/14/17.
 */

public class Country {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("country_name")
    @Expose
    private String countryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
