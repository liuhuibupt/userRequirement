package com.charmingglobe.gr.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by PANZHENG on 2018/1/24.
 */
@Entity
@Table(name = "gr_receiving_plan")
public class ReceivingPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "tr_plan_id")
    private String trPlanId;

    //卫星标示，标示本次数传卫星代号
    @Column(name="satellite_id")
    private String satelliteId;

    @Column(name = "satellite_name")
    private String satelliteName;

    @Column(name = "station_id")
    private String stationId;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "orbit_id")
    private int orbitId;

    @Column(name = "downlink_channel")
    private int downlinkChannel;

    @Column(name = "receive_start_time")
    private Date receiveStartTime;

    //数传结束时间
    @Column(name = "receive_stop_time")
    private Date receiveStopTime;

    //进站时间
    @Column(name = "satellite_capture_start_time")
    private Date satelliteCaptureStartTime;

    //出站时间
    @Column(name = "satellite_capture_stop_time")
    private Date satelliteCaptureStopTime;

    @Column(name = "status")
    private String status;

    @Column(name = "task_names")
    private String taskNames;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrPlanId() {
        return trPlanId;
    }

    public void setTrPlanId(String trPlanId) {
        this.trPlanId = trPlanId;
    }

    public String getSatelliteId() {
        return satelliteId;
    }

    public void setSatelliteId(String satelliteId) {
        this.satelliteId = satelliteId;
    }

    public String getSatelliteName() {
        return satelliteName;
    }

    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getOrbitId() {
        return orbitId;
    }

    public void setOrbitId(int orbitId) {
        this.orbitId = orbitId;
    }

    public int getDownlinkChannel() {
        return downlinkChannel;
    }

    public void setDownlinkChannel(int downlinkChannel) {
        this.downlinkChannel = downlinkChannel;
    }

    public Date getReceiveStartTime() {
        return receiveStartTime;
    }

    public void setReceiveStartTime(Date receiveStartTime) {
        this.receiveStartTime = receiveStartTime;
    }

    public Date getReceiveStopTime() {
        return receiveStopTime;
    }

    public void setReceiveStopTime(Date receiveStopTime) {
        this.receiveStopTime = receiveStopTime;
    }

    public Date getSatelliteCaptureStartTime() {
        return satelliteCaptureStartTime;
    }

    public void setSatelliteCaptureStartTime(Date satelliteCaptureStartTime) {
        this.satelliteCaptureStartTime = satelliteCaptureStartTime;
    }

    public Date getSatelliteCaptureStopTime() {
        return satelliteCaptureStopTime;
    }

    public void setSatelliteCaptureStopTime(Date satelliteCaptureStopTime) {
        this.satelliteCaptureStopTime = satelliteCaptureStopTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaskNames() {
        return taskNames;
    }

    public void setTaskNames(String taskNames) {
        this.taskNames = taskNames;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
