package com.charmingglobe.gr.entity;

import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/3.
 */
@Entity
@Table(name = "grc_imaging_plan")
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

    @Column(name = "plan_id")
    private String planId;

    @Column(name = "satellite_id")
    private String satelliteId;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "plan_start_time")
    private Date planStartTime;

    @Column(name = "plan_end_time")
    private Date planEndTime;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "status")
    private String status;

    @Type(type="com.vividsolutions.jts.geom.Geometry")
    @Column(name = "imaging_geometry")
    private Geometry imagingGeometry;

    @Transient
    private String imagingWkt;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Geometry getImagingGeometry() {
        return imagingGeometry;
    }

    public void setImagingGeometry(Geometry imagingGeometry) {
        this.imagingGeometry = imagingGeometry;
    }

    public String getImagingWkt() {
        return imagingWkt;
    }

    public void setImagingWkt(String imagingWkt) {
        this.imagingWkt = imagingWkt;
    }
}
