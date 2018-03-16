package com.aceplus.e_commerce.model.modelForSetUpTableDownload;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by phonelin on 6/15/17.
 */

public class CategoryResponseData {

    @SerializedName("id")
    @Expose
    int id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("parent_id")
    @Expose
    int parent_id;

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("image_encode")
    @Expose
    String image_encode;

    @SerializedName("status")
    @Expose
    int status;

    @SerializedName("created_by")
    @Expose
    int created_by;

    @SerializedName("updated_by")
    @Expose
    int updated_by;

    @SerializedName("deleted_by")
    @Expose
    int deleted_by;

    @SerializedName("created_at")
    @Expose
    String created_at;

    @SerializedName("updated_at")
    @Expose
    String updated_at;

    @SerializedName("deleted_at")
    @Expose
    String deleted_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_encode() {
        return image_encode;
    }

    public void setImage_encode(String image_encode) {
        this.image_encode = image_encode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public int getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(int deleted_by) {
        this.deleted_by = deleted_by;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
