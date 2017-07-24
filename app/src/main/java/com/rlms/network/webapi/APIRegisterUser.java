package com.rlms.network.webapi;

import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.RegisterUser;
import com.rlms.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIRegisterUser {

    @POST("/RLMS/API/register/registerMemeberDeviceByMblNo")
    Call<RLMSAPIResponse> callLoginUser(
            @Body RegisterUser registerUser);

}
