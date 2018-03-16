package com.aceplus.e_commerce.apiService;

import com.aceplus.e_commerce.model.modelForSetUpTableDownload.CategoryResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductCategoriesResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductColorResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDescriptionResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductDiscountResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGalleryResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductGroupResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductResponse;
import com.aceplus.e_commerce.model.modelForSetUpTableDownload.ProductSizeResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by phonelin on 6/15/17.
 */

public interface DownloadService {

    @FormUrlEncoded
    @POST("syncs/down")
    Call<CategoryResponse> downloadCategory(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductResponse> downloadProduct(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductColorResponse> downloadProductColor(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductSizeResponse> downloadProductSize(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductDescriptionResponse> downloadProductDescription(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductGalleryResponse> downloadProductGallery(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductGroupResponse> downloadProductGroup(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductDiscountResponse> downloadProductDiscount(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("syncs/down")
    Call<ProductCategoriesResponse> downloadProductCategories(@Field("param_data") String paramData);

}
