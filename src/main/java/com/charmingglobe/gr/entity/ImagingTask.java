package com.charmingglobe.gr.entity;

import com.charmingglobe.gr.hibernate.JSONBUserType;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by PANZHENG on 2018/1/22.
 */
@Entity
@Table(name = "grc_imaging_task")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class)
public class ImagingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "ot_task_id")
    private String otTaskId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "user_request_id")
    private int userRequestId;

    @Column(name = "plan_id")
    private String planId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "satellite_id")
    private String satelliteId;

    @Column(name = "imaging_mode")
    private String imagingMode;

    @Column(name = "imaging_start")
    private Date imagingStart;

    @Column(name = "imaging_end")
    private Date imagingEnd;

    @Type(type="com.vividsolutions.jts.geom.Geometry")
    @Column(name = "imaging_geometry")
    private Geometry imagingGeometry;

    @Column(name = "imaging_wkt")
    private String imagingWkt;

    @Type(type = "jsonb")
    @Column(name = "meta_json")
    private Map<String, String> attributes;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "status")
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtTaskId() {
        return otTaskId;
    }

    public void setOtTaskId(String otTaskId) {
        this.otTaskId = otTaskId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getUserRequestId() {
        return userRequestId;
    }

    public void setUserRequestId(int userRequestId) {
        this.userRequestId = userRequestId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
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

    public String getImagingMode() {
        return imagingMode;
    }

    public void setImagingMode(String imagingMode) {
        this.imagingMode = imagingMode;
    }

    public Date getImagingStart() {
        return imagingStart;
    }

    public void setImagingStart(Date imagingStart) {
        this.imagingStart = imagingStart;
    }

    public Date getImagingEnd() {
        return imagingEnd;
    }

    public void setImagingEnd(Date imagingEnd) {
        this.imagingEnd = imagingEnd;
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

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
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
}
