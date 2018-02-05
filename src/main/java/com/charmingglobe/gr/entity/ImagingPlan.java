package com.charmingglobe.gr.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/3.
 */
@Entity
@Table(name = "gr_imaging_plan")
public class ImagingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Transient
    private int num;

    @Column(name = "user_request_id")
    private int userRequestId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "satellite_id")
    private String satelliteId;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "imaging_start_time")
    private Date imagingStartTime;

    @Column(name = "imaging_end_time")
    private Date imagingEndTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getUserRequestId() {
        return userRequestId;
    }

    public void setUserRequestId(int userRequestId) {
        this.userRequestId = userRequestId;
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

    public Date getImagingStartTime() {
        return imagingStartTime;
    }

    public void setImagingStartTime(Date imagingStartTime) {
        this.imagingStartTime = imagingStartTime;
    }

    public Date getImagingEndTime() {
        return imagingEndTime;
    }

    public void setImagingEndTime(Date imagingEndTime) {
        this.imagingEndTime = imagingEndTime;
    }
}
