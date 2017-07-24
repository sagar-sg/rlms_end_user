package com.rlms.network.webapi;

import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.RegisterComplaint;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIRegisterComplaint {

    @Headers("Accept: application/json")
    @POST("/RLMS/API/complaint/validateAndRegisterNewComplaint")
    Call<RLMSAPIResponse> validateAndRegisterComplaint(
            @Body RegisterComplaint registerComplaint);

}
