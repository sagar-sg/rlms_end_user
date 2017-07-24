package com.rlms.model;

/**
 * Created by user on 17/5/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Technician {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("userId")
    @Expose
    private int userId = 0;
    @SerializedName("branchCompanyMapId")
    @Expose
    private int branchCompanyMapId = 0;
    @SerializedName("companyId")
    @Expose
    private int companyId = 0;
    @SerializedName("userRoleId")
    @Expose
    private int userRoleId = 0;
    @SerializedName("appRegId")
    @Expose
    private String appRegId;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("pinCode")
    @Expose
    private String pinCode;
    @SerializedName("userRoleName")
    @Expose
    private String userRoleName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBranchCompanyMapId() {
        return branchCompanyMapId;
    }

    public void setBranchCompanyMapId(int branchCompanyMapId) {
        this.branchCompanyMapId = branchCompanyMapId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getAppRegId() {
        return appRegId;
    }

    public void setAppRegId(String appRegId) {
        this.appRegId = appRegId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    @Override
    public String toString() {
        return "Technician" +
                "  \nuserName='" + userName + '\'' +
                ", \nfirstName='" + firstName + '\'' +
                ", \nlastName='" + lastName + '\'' +
                ", \ncompanyName='" + companyName + '\'' +
                ", \ncontactNumber='" + contactNumber + '\'' +
                ", \naddress='" + address + '\'' +
                ", \nemailId='" + emailId + '\'' +
                ", \nbranchName='" + branchName + '\'' +
                ", \nuserId=" + userId +
                ", \nbranchCompanyMapId=" + branchCompanyMapId +
                ", \ncompanyId=" + companyId +
                ", \nuserRoleId=" + userRoleId +
                ", \nappRegId='" + appRegId + '\'' +
                ", \nlatitude=" + latitude +
                ", \nlongitude=" + longitude +
                ", \ncity='" + city + '\'' +
                ", \narea='" + area + '\'' +
                ", \npinCode='" + pinCode + '\'' +
                ", \nuserRoleName='" + userRoleName;
    }
}
