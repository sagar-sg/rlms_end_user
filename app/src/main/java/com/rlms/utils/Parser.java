package com.rlms.utils;

import com.rlms.constants.Params;
import com.rlms.model.Complaint;
import com.rlms.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Parser {

    static Log Log = new Log();
    static String TAG = "Parser";

    public static ArrayList<Complaint> getParsedComplaintsList(String stringToParse){

        ArrayList<Complaint> complaintArrayList = new ArrayList<Complaint>();

        try {
            JSONArray jsonArray = new JSONArray(stringToParse);

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Complaint complaint = new Complaint();
//                complaint.setTitle(jsonObject.getString(Params.TITLE));
//                complaint.setUserRoleId(jsonObject.getInt(Params.USER_ROLE_ID));
                complaint.setRegistrationDate(jsonObject.optLong(Params.REGISTRATION_DATE));
                complaint.setStatus(jsonObject.optString(Params.STATUS));
                complaint.setCustomerName(jsonObject.optString(Params.CUSTOMER_NAME));
                complaint.setLiftNumber(jsonObject.optString(Params.LIFT_NUMBER));
                complaint.setRemark(jsonObject.optString(Params.REMARK));
                complaint.setComplaintNumber(jsonObject.optString(Params.COMPLAINT_NUMBER));
                complaint.setComplaintId(jsonObject.optInt(Params.COMPLAINT_ID));
                complaint.setComplaintTechMapId(jsonObject.optInt(Params.COMPLAINT_TECH_MAP_ID));
                complaint.setActualServiceEndDate(jsonObject.optLong(Params.ACTUAL_SERVICE_END_DATE));

                complaintArrayList.add(complaint);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(TAG,"complaints parsed size = "+complaintArrayList.size());

        return complaintArrayList;

    }

    public static User getParsedUser(String response){
        try {
            JSONObject userJson = new JSONObject(response);

            User user = new User();
            user.setFirstName(userJson.getString(Params.FIRST_NAME));
            user.setLastName(userJson.getString(Params.LAST_NAME));
            user.setContactNumber(userJson.getString(Params.CONTACT_NUMBER));
            user.setMemberId(userJson.getInt(Params.MEMBER_ID));
//            user.setBranchCustoMapId(userJson.getInt(Params.CUSTOMER_MAP_ID));

            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
