package com.rlms.model;

public class RegisterComplaint {

//    {"liftCustomerMapId":"1","registrationType":"1",
//            "complaintsRemark":"Complaints",
//            "complaintsTitle":"Title","memberId":"2"}
//
    int liftCustomerMapId = 0;
    String complaintsTitle = "";
    int registrationType = 0;
    String complaintsRemark = "";
    int memberId = 0;

    public RegisterComplaint() {
    }

    public int getLiftCustomerMapId() {
        return liftCustomerMapId;
    }

    public void setLiftCustomerMapId(int liftCustomerMapId) {
        this.liftCustomerMapId = liftCustomerMapId;
    }

    public String getComplaintsTitle() {
        return complaintsTitle;
    }

    public void setComplaintsTitle(String complaintsTitle) {
        this.complaintsTitle = complaintsTitle;
    }

    public int getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(int registrationType) {
        this.registrationType = registrationType;
    }

    public String getComplaintsRemark() {
        return complaintsRemark;
    }

    public void setComplaintsRemark(String complaintsRemark) {
        this.complaintsRemark = complaintsRemark;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "RegisterComplaint{" +
                "liftCustomerMapId='" + liftCustomerMapId + '\'' +
                ", complaintsTitle='" + complaintsTitle + '\'' +
                ", registrationType='" + registrationType + '\'' +
                ", complaintsRemark='" + complaintsRemark + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
