package com.charmingglobe.gr.webservice.result;

import com.charmingglobe.gr.entity.UserRequest;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/1/23.
 */
public class UserRequestResult {

    private String requestId;

    private String requestName;

    private String requestSatellites;

    private Date submitTime;

    private boolean isSensitive;

    private String requestType;

    private Date requestStart;

    private Date requestEnd;

    private String imagingMode;

    private String imagingWkt;

    private String status;

    public UserRequestResult(UserRequest userRequest) {
        requestId = userRequest.getRequestId();
        requestName = userRequest.getRequestName();
        requestSatellites = userRequest.getRequestSatellites();
        submitTime = userRequest.getSubmitTime();
        isSensitive = userRequest.isSensitive();
        requestType = userRequest.getRequestType();
        requestStart = userRequest.getRequestStart();
        requestEnd = userRequest.getRequestEnd();
        imagingMode = userRequest.getImagingMode();
        imagingWkt = userRequest.getImagingWkt();
        status = userRequest.getStatus();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestSatellites() {
        return requestSatellites;
    }

    public void setRequestSatellites(String requestSatellites) {
        this.requestSatellites = requestSatellites;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Date getRequestStart() {
        return requestStart;
    }

    public void setRequestStart(Date requestStart) {
        this.requestStart = requestStart;
    }

    public Date getRequestEnd() {
        return requestEnd;
    }

    public void setRequestEnd(Date requestEnd) {
        this.requestEnd = requestEnd;
    }

    public String getImagingMode() {
        return imagingMode;
    }

    public void setImagingMode(String imagingMode) {
        this.imagingMode = imagingMode;
    }

    public String getImagingWkt() {
        return imagingWkt;
    }

    public void setImagingWkt(String imagingWkt) {
        this.imagingWkt = imagingWkt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
