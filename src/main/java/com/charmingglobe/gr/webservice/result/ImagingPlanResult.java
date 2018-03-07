package com.charmingglobe.gr.webservice.result;

import com.charmingglobe.gr.entity.ImagingPlan;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/3/5.
 */
public class ImagingPlanResult {


    private String requestId;

    private String requestName;

    private String planId;

    private String satelliteId;

    private String requestType;

    private Date planStartTime;

    private Date planEndTime;

    private String status;

    private String imagingWkt;

    public ImagingPlanResult(ImagingPlan imagingPlan) {
        this.requestId = imagingPlan.getRequestId();
        this.requestName = imagingPlan.getRequestName();
        this.planId = imagingPlan.getPlanId();
        this.satelliteId = imagingPlan.getSatelliteId();
        this.requestType = imagingPlan.getRequestType();
        this.planStartTime = imagingPlan.getPlanStartTime();
        this.planEndTime = imagingPlan.getPlanEndTime();
        this.status = imagingPlan.getStatus();
        this.imagingWkt = imagingPlan.getImagingWkt();
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

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagingWkt() {
        return imagingWkt;
    }

    public void setImagingWkt(String imagingWkt) {
        this.imagingWkt = imagingWkt;
    }
}
