package com.rlms.network;

import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.MemberRole;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by user on 11/4/17.
 */

public interface APIGetAllLifts {

    @Headers("Accept: application/json")
    @POST("/RLMS/API/lift/getAllLiftsForMember")
    Call<RLMSAPIResponse> getAllLiftsFor(
            @Body MemberRole memberRole);

}
