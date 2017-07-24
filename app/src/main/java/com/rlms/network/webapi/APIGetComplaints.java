package com.rlms.network.webapi;

import com.rlms.model.RLMSAPIResponse;
import com.rlms.model.MemberRole;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIGetComplaints {

    @Headers("Accept: application/json")
    @POST("/RLMS/API/complaints/getAllComplaintsByMember")
    Call<RLMSAPIResponse> getAllComplaintsFor(
            @Body MemberRole memberRole);

}
