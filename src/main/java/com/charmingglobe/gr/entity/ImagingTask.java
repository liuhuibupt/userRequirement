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
@Table(name = "gr_imaging_task")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class)
public class ImagingTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "task_id")
    private String taskId;

    @Column(name = "task_name")
    private String taskName;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
}
