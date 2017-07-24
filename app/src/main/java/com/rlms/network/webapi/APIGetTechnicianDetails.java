package com.rlms.network.webapi;

import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.TechnicianRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by user on 17/5/17.
 */

public interface APIGetTechnicianDetails {
    @Headers("Accept: application/json")
    @POST("/RLMS/API/complaint/getTechnicianDtls")
    Call<RLMSAPIResponse> getTechnicianDetailsFor(
            @Body TechnicianRequest technicianRequest);

}
