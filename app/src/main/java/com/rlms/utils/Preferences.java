package com.rlms.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.rlms.constants.AppConstants;
import com.rlms.constants.Params;
import com.rlms.model.User;

import static android.content.Context.MODE_PRIVATE;

public class Preferences {

    private SharedPreferences preferences;
    Context mContext;

    public Preferences(Context context) {

        preferences = context.getSharedPreferences(AppConstants.PREF_FILE_NAME,MODE_PRIVATE);
        mContext = context;
    }

    public void storeUserDetails(User user){

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(Params.FIRST_NAME, user.getFirstName()).apply();
        editor.putString(Params.LAST_NAME, user.getLastName()).apply();
        editor.putString(Params.CONTACT_NUMBER, user.getContactNumber()).apply();
        editor.putInt(Params.MEMBER_ID, user.getMemberId()).apply();
//        editor.putInt(Params.CUSTOMER_MAP_ID, user.getBranchCustoMapId()).apply();

        editor.commit();

    }

    public User getStoredUserDetails() {

        User user = new User();

        user.setFirstName(preferences.getString(Params.FIRST_NAME, ""));
        user.setLastName(preferences.getString(Params.LAST_NAME, ""));
        user.setContactNumber(preferences.getString(Params.CONTACT_NUMBER, ""));
        user.setMemberId(preferences.getInt(Params.MEMBER_ID, 0));

        return user;
    }
}
