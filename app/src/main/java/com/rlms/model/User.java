package com.rlms.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

//    {"userName":null,"password":null,"userRoleId":1,"userId":1,"companyId":1}

//    List OF Customer Details with following params:
//            1)	branchCompanyMapId
//            2)	branchName
//            3)	customerName
//            4)	companyName
//
//    {"userName":null,"firstName":"testFirst","lastName":"TestLast","companyName":"INDITECH",
//            "contactNumber":"9096136234","address":null,"emailId":null,"branchName":"Hinjewadi Branch","userId":4,
//            "branchCompanyMapId":8,"companyId":null,"userRoleId":17,
//            "appRegId":null,"latitude":null,"longitude":null,"city":null,"area":null,"pinCode":null,"userRoleName":null}


//{status=true, response='{"firstName":"Sharad","lastName":"Pawar","companyName":null,
//    "contactNumber":"8983564578","address":null,"emailId":null,"branchCustoMapId":null,
//        "appRegId":null,"latitude":null,"longitude":null,"branchName":null,"customerName":null,
//        "registrationDate":null,"city":null,"area":null,"pinCode":null,"memberId":null,
//        "listOfCustomerDtls":[{"firstName":null,"lastName":null,"address":null,"vatNumber":null,
//        "tinNumber":null,"panNumber":null,"customerType":null,"emailID":null,"cntNumber":null,
//        "branchCompanyMapId":1,"totalNumberOfLifts":null,"branchName":"PUNE","companyName":"INDITECH",
//        "customerName":"sanket","city":null,"area":null,"pinCode":null,"chairmanName":null,
//        "chairmanNumber":null,"chairmanEmail":null,"secretaryName":null,"secretaryNumber":null,
//        "secretaryEmail":null,"treasurerName":null,"treasurerNumber":null,"treasurerEmail":null,
//        "watchmenName":null,"watchmenNumber":null,"watchmenEmail":null,"branchCustomerMapId":null,
//        "companyId":null}]}', jsonElement='null', jsonArray='null'}

    private String firstName;
    private String lastName;
    private String companyName;
    private String contactNumber;
    private String address;
    private String emailId;
    private int branchCustoMapId;
    private String appRegId;
    private String latitude;
    private String longitude;
    private String branchName;
    private String customerName;
    private String registrationDate;
    private String city;
    private String area;
    private String pinCode;
    private int memberId;
    private List<ListOfCustomerDtl> listOfCustomerDtls = null;
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

    public int getBranchCustoMapId() {
        return branchCustoMapId;
    }

    public void setBranchCustoMapId(int branchCustoMapId) {
        this.branchCustoMapId = branchCustoMapId;
    }

    public String getAppRegId() {
        return appRegId;
    }

    public void setAppRegId(String appRegId) {
        this.appRegId = appRegId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
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

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public List<ListOfCustomerDtl> getListOfCustomerDtls() {
        return listOfCustomerDtls;
    }

    public void setListOfCustomerDtls(List<ListOfCustomerDtl> listOfCustomerDtls) {
        this.listOfCustomerDtls = listOfCustomerDtls;
    }

    public Map<String, String> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, String value) {
        this.additionalProperties.put(name, value);
    }

    public User(int memberId) {
        this.memberId = memberId;
    }

    public User() {
    }
}
