package com.charmingglobe.gr.webservice.result;

import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.ImagingTask;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.util.Date;
import java.util.Map;

/**
 * Created by PANZHENG on 2018/3/5.
 */
public class ImagingTaskResult {

    private String otTaskId;

    private String planId;

    private String requestName;

    private String satelliteId;

    private String imagingMode;

    private Date imagingStart;

    private Date imagingEnd;

    private String imagingWkt;

    private Map<String, String> attributes;

    private Date createTime;

    private String status;

    public ImagingTaskResult(ImagingTask imagingTask) {
        this.otTaskId = imagingTask.getOtTaskId();
        this.planId= imagingTask.getPlanId();
        this.requestName = imagingTask.getRequestName();
        this.satelliteId = imagingTask.getSatelliteId();
        this.imagingMode = imagingTask.getImagingMode();
        this.imagingStart = imagingTask.getImagingStart();
        this.imagingEnd = imagingTask.getImagingEnd();
        this.imagingWkt = imagingTask.getImagingWkt();
        this.attributes = imagingTask.getAttributes();
        this.createTime = imagingTask.getCreateTime();
        this.status = imagingTask.getStatus();
    }

    public String getOtTaskId() {
        return otTaskId;
    }

    public void setOtTaskId(String otTaskId) {
        this.otTaskId = otTaskId;
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
