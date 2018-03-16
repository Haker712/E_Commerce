package com.aceplus.e_commerce.apiService;

import com.aceplus.e_commerce.model.RegistrationResponse;
import com.aceplus.e_commerce.model.modelForLogin.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by phonelin on 6/14/17.
 */

public interface UploadService {

    @FormUrlEncoded
    @POST("customer/registration")
    Call<RegistrationResponse> uploadUserRegistration(@Field("param_data") String paramData);

    @FormUrlEncoded
    @POST("postLogin")
    Call<LoginResponse> uploadLogin(@Field("param_data") String paramData);

}
