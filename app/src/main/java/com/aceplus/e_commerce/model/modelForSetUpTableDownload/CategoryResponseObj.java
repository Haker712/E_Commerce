package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by phonelin on 6/19/17.
 */

public class CategoryResponseObj {

    @SerializedName("categories")
    @Expose
    private List<CategoryResponseData> categoryResponseDatas = null;

    @SerializedName("version")
    @Expose
    private Integer version;


    public List<CategoryResponseData> getCategoryResponseDatas() {
        return categoryResponseDatas;
    }

    public void setCategoryResponseDatas(List<CategoryResponseData> categoryResponseDatas) {
        this.categoryResponseDatas = categoryResponseDatas;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
