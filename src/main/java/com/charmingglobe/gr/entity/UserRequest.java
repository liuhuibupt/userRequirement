package com.charmingglobe.gr.entity;

import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Entity
@Table(name = "gr_user_request")
public class UserRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Transient
    private int num;

    @Column(name = "request_code")
    private String requestCode;

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

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "request_start")
    private Date requestStart;

    @Column(name = "request_end")
    private Date requestEnd;

    @Column(name = "imaging_type")
    private String imagingType;

    @Column(name = "imaging_wkt")
    private String imagingWkt;

    @Type(type="com.vividsolutions.jts.geom.Geometry")
    @Column(name = "imaging_geometry")
    private Geometry imagingGeometry;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "comments")
    private String comments;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

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

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
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

    public String getImagingType() {
        return imagingType;
    }

    public void setImagingType(String imagingType) {
        this.imagingType = imagingType;
    }

    public String getImagingWkt() {
        return imagingWkt;
    }

    public void setImagingWkt(String imagingWkt) {
        this.imagingWkt = imagingWkt;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
