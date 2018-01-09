package com.charmingglobe.gr.entity;

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

    @Column(name = "request_name")
    private String requestName;

    @Column(name = "request_satellites")
    private String requestSatellites;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "submitter_id")
    private User0 submitter;

    @Column(name = "submit_time")
    private Date submitTime;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "imaging_type")
    private String imagingType;

    @Column(name = "imaging_code")
    private String imagingCode;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "comments")
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImagingType() {
        return imagingType;
    }

    public void setImagingType(String imagingType) {
        this.imagingType = imagingType;
    }

    public String getImagingCode() {
        return imagingCode;
    }

    public void setImagingCode(String imagingCode) {
        this.imagingCode = imagingCode;
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
}
