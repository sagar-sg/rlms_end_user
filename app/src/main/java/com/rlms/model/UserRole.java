package com.rlms.model;

/**
 * Created by Swapnil on 3/17/2017.
 */

public class UserRole {

    String memberId = "";

    public UserRole(String userRoleId) {
        this.memberId = userRoleId;
    }

    public String getUserRoleId() {
        return memberId;
    }

    public void setUserRoleId(String userRoleId) {
        this.memberId = userRoleId;
    }
}
