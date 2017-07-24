package com.rlms.model;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class ListOfCustomerDtl {

    private String firstName;
    private String lastName;
    private String address;
    private String vatNumber;
    private String tinNumber;
    private String panNumber;
    private String customerType;
    private String emailID;
    private String cntNumber;
    private Integer branchCompanyMapId;
    private String totalNumberOfLifts;
    private String branchName;
    private String companyName;
    private String customerName;
    private String city;
    private String area;
    private String pinCode;
    private String chairmanName;
    private String chairmanNumber;
    private String chairmanEmail;
    private String secretaryName;
    private String secretaryNumber;
    private String secretaryEmail;
    private String treasurerName;
    private String treasurerNumber;
    private String treasurerEmail;
    private String watchmenName;
    private String watchmenNumber;
    private String watchmenEmail;
    private String branchCustomerMapId;
    private String companyId;
    private Map<String, String> additionalProperties = new HashMap<String, String>();

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public void setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getCntNumber() {
        return cntNumber;
    }

    public void setCntNumber(String cntNumber) {
        this.cntNumber = cntNumber;
    }

    public Integer getBranchCompanyMapId() {
        return branchCompanyMapId;
    }

    public void setBranchCompanyMapId(Integer branchCompanyMapId) {
        this.branchCompanyMapId = branchCompanyMapId;
    }

    public String getTotalNumberOfLifts() {
        return totalNumberOfLifts;
    }

    public void setTotalNumberOfLifts(String totalNumberOfLifts) {
        this.totalNumberOfLifts = totalNumberOfLifts;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }

    public String getChairmanNumber() {
        return chairmanNumber;
    }

    public void setChairmanNumber(String chairmanNumber) {
        this.chairmanNumber = chairmanNumber;
    }

    public String getChairmanEmail() {
        return chairmanEmail;
    }

    public void setChairmanEmail(String chairmanEmail) {
        this.chairmanEmail = chairmanEmail;
    }

    public String getSecretaryName() {
        return secretaryName;
    }

    public void setSecretaryName(String secretaryName) {
        this.secretaryName = secretaryName;
    }

    public String getSecretaryNumber() {
        return secretaryNumber;
    }

    public void setSecretaryNumber(String secretaryNumber) {
        this.secretaryNumber = secretaryNumber;
    }

    public String getSecretaryEmail() {
        return secretaryEmail;
    }

    public void setSecretaryEmail(String secretaryEmail) {
        this.secretaryEmail = secretaryEmail;
    }

    public String getTreasurerName() {
        return treasurerName;
    }

    public void setTreasurerName(String treasurerName) {
        this.treasurerName = treasurerName;
    }

    public String getTreasurerNumber() {
        return treasurerNumber;
    }

    public void setTreasurerNumber(String treasurerNumber) {
        this.treasurerNumber = treasurerNumber;
    }

    public String getTreasurerEmail() {
        return treasurerEmail;
    }

    public void setTreasurerEmail(String treasurerEmail) {
        this.treasurerEmail = treasurerEmail;
    }

    public String getWatchmenName() {
        return watchmenName;
    }

    public void setWatchmenName(String watchmenName) {
        this.watchmenName = watchmenName;
    }

    public String getWatchmenNumber() {
        return watchmenNumber;
    }

    public void setWatchmenNumber(String watchmenNumber) {
        this.watchmenNumber = watchmenNumber;
    }

    public String getWatchmenEmail() {
        return watchmenEmail;
    }

    public void setWatchmenEmail(String watchmenEmail) {
        this.watchmenEmail = watchmenEmail;
    }

    public String getBranchCustomerMapId() {
        return branchCustomerMapId;
    }

    public void setBranchCustomerMapId(String branchCustomerMapId) {
        this.branchCustomerMapId = branchCustomerMapId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

}