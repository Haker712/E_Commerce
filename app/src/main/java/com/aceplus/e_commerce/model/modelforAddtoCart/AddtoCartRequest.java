package com.aceplus.e_commerce.model.modelforAddtoCart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 5/29/17.
 */

public class AddtoCartRequest {

    @SerializedName("site_activation_key")
    @Expose
    private String site_activation_key;


    @SerializedName("data")
    @Expose
    private AddtoCartData addtoCartData;

    public String getSite_activation_key() {
        return site_activation_key;
    }

    public void setSite_activation_key(String site_activation_key) {
        this.site_activation_key = site_activation_key;
    }

    public AddtoCartData getAddtoCartData() {
        return addtoCartData;
    }

    public void setAddtoCartData(AddtoCartData wishlist_uploadRequestData) {
        this.addtoCartData = wishlist_uploadRequestData;
    }

}
