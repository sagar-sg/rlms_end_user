package com.rlms.model;

/**
 * Created by user on 17/5/17.
 */

public class TechnicianRequest {

    private String complaintId="0";

    public TechnicianRequest(String complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }
}
