package com.rlms.model;

/**
 * Created by Swapnil on 3/17/2017.
 */

public class MemberRole {

    String memberId = "";

    public MemberRole(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
