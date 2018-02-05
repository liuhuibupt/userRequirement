package com.charmingglobe.gr.entity;

import com.charmingglobe.gr.hibernate.JSONBUserType;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Entity
@Table(name = "gr_user_request")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class)
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Transient
    private int num;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "request_satellites")
    private String requestSatellites;

    @Column(name = "request_from")
    private String requestFrom;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "submitter_id")
    private User0 submitter;

    @Column(name = "submit_time")
    private Date submitTime;

    @Column(name = "is_sensitive")
    private boolean isSensitive;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "request_start")
    private Date requestStart;

    @Column(name = "request_end")
    private Date requestEnd;

    @Column(name = "imaging_mode")
    private String imagingMode;

    @Type(type = "jsonb")
    @Column(name = "imaging_para")
    private Map imagingPara;

    @Transient
    private String imagingParaTxt;

    @Type(type="com.vividsolutions.jts.geom.Geometry")
    @Column(name = "imaging_geometry")
    private Geometry imagingGeometry;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "comments")
    private String comments;

    @Column(name = "status")
    private String status;

    @Transient
    private String label;

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

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public User0 getSubmitter() {
        return submitter;
    }

    public void setSubmitter(User0 submitter) {
        this.submitter = submitter;
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

    public Map getImagingPara() {
        return imagingPara;
    }

    public void setImagingPara(Map imagingPara) {
        this.imagingPara = imagingPara;
    }

    public String getImagingParaTxt() {
        return imagingParaTxt;
    }

    public void setImagingParaTxt(String imagingParaTxt) {
        this.imagingParaTxt = imagingParaTxt;
    }

    public Geometry getImagingGeometry() {
        return imagingGeometry;
    }

    public void setImagingGeometry(Geometry imagingGeometry) {
        this.imagingGeometry = imagingGeometry;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
