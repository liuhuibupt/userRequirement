package com.charmingglobe.gr.entity;

import com.charmingglobe.gr.hibernate.JSONBUserType;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by PANZHENG on 2017/11/17.
 * Edited by Liuhui on 2018/3/17
 */
@Entity
@Table(name = "grc_user_request")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class)
public class UserRequest {

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

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "side_angel")
    private String sideAngel;

    @Column(name = "cloud")
    private String cloud;

    @Column(name = "geometry_request")
    private String geometryRequest;

    @Column(name = "radiation_request")
    private String radiationRequest;

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

    @Column(name = "coverage")
    private String coverage;

    @Transient
    private String imagingParaTxt;

    @Transient
    private String label;

    @Column(name = "request_satellites")
    private String requestSatellites;

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

    @Column(name = "product_delivery_model")
    private String  productDeliveryModel;

    @Column(name = "product_delivery_time")
    private String productDeliveryTime;

    @Column(name = "product_level")
    private String productLevel;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "product_delivery_url")
    private String productDeliveryURL;

    @Column(name = "spectrum")
    private String spectrum;

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

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
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

    public String getGeometryRequest() {
        return geometryRequest;
    }

    public void setGeometryRequest(String geometryRequest) {
        this.geometryRequest = geometryRequest;
    }

    public String getRadiationRequest() {
        return radiationRequest;
    }

    public void setRadiationRequest(String radiationRequest) {
        this.radiationRequest = radiationRequest;
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

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
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

    public String getProductDeliveryModel() {
        return productDeliveryModel;
    }

    public void setProductDeliveryModel(String productDeliveryModel) {
        this.productDeliveryModel = productDeliveryModel;
    }

    public String getProductDeliveryTime() {
        return productDeliveryTime;
    }

    public void setProductDeliveryTime(String productDeliveryTime) {
        this.productDeliveryTime = productDeliveryTime;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductDeliveryURL() {
        return productDeliveryURL;
    }

    public void setProductDeliveryURL(String productDeliveryURL) {
        this.productDeliveryURL = productDeliveryURL;
    }

    public String getSpectrum() {
        return spectrum;
    }

    public void setSpectrum(String spectrum) {
        this.spectrum = spectrum;
    }
}




