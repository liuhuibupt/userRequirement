package com.charmingglobe.gr.entity;

import com.charmingglobe.gr.hibernate.JSONBUserType;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by Liuhui on 2018/3/17
 */
@Entity
@Table(name = "grc_image_request")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class)
public class ImageRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Transient
    private int num;

    @Column(name = "status")
    private String status;

    @Column(name = "submit_time")
    private Date submitTime;

    @Column(name = "edit_time")
    private Date editTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "submitter_id")
    private Cavalier submitter;

    @Column(name = "request_from")
    private String requestFrom;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "is_sensitive")
    private boolean isSensitive;

    @Column(name = "priority")
    private String priority;

    @Column(name = "request_user")
    private String requestUser;

    @Column(name = "side_angel")
    private String sideAngel;

    @Column(name = "cloud")
    private String cloud;

    @Column(name = "geometry_request")
    private String geometry_request;

    @Column(name = "radiation_request")
    private String radiation_request;

    @Column(name = "request_type")
    private String requestType;

    @Type(type = "com.vividsolutions.jts.geom.Geometry")
    @Column(name = "imaging_geometry")
    private Geometry imagingGeometry;

    @Type(type = "jsonb")
    @Column(name = "imaging_para")
    private Map imagingPara;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "comments")
    private String comments;

    @Transient
    private String imagingParaTxt;

    @Transient
    private String label;

    @Column(name = "request_satellites")
    private String requestSatellites;

    @Column(name = "imaging_mode")
    private String imagingMode;

    @Column(name = "imaging_duration")
    private String imagingDuration;

    @Column(name = "is_multi_grid")
    private boolean isMultiGrid;

    @Column(name = "request_start")
    private Date requestStart;

    @Column(name = "request_end")
    private Date requestEnd;

    @Column(name = "shoot_num")
    private int shootNum;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Cavalier getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Cavalier submitter) {
        this.submitter = submitter;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
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

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getSideAngel() {
        return sideAngel;
    }

    public void setSideAngel(String sideAngel) {
        this.sideAngel = sideAngel;
    }

    public String getCloud() {
        return cloud;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }

    public String getGeometry_request() {
        return geometry_request;
    }

    public void setGeometry_request(String geometry_request) {
        this.geometry_request = geometry_request;
    }

    public String getRadiation_request() {
        return radiation_request;
    }

    public void setRadiation_request(String radiation_request) {
        this.radiation_request = radiation_request;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Geometry getImagingGeometry() {
        return imagingGeometry;
    }

    public void setImagingGeometry(Geometry imagingGeometry) {
        this.imagingGeometry = imagingGeometry;
    }

    public Map getImagingPara() {
        return imagingPara;
    }

    public void setImagingPara(Map imagingPara) {
        this.imagingPara = imagingPara;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImagingParaTxt() {
        return imagingParaTxt;
    }

    public void setImagingParaTxt(String imagingParaTxt) {
        this.imagingParaTxt = imagingParaTxt;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRequestSatellites() {
        return requestSatellites;
    }

    public void setRequestSatellites(String requestSatellites) {
        this.requestSatellites = requestSatellites;
    }

    public String getImagingMode() {
        return imagingMode;
    }

    public void setImagingMode(String imagingMode) {
        this.imagingMode = imagingMode;
    }

    public String getImagingDuration() {
        return imagingDuration;
    }

    public void setImagingDuration(String imagingDuration) {
        this.imagingDuration = imagingDuration;
    }

    public boolean isMultiGrid() {
        return isMultiGrid;
    }

    public void setMultiGrid(boolean multiGrid) {
        isMultiGrid = multiGrid;
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

    public int getShootNum() {
        return shootNum;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }
}




