package com.charmingglobe.gr.cri;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/1/11.
 */
public class UserRequestCri {

    private int curPageNum;

    private int totalPageNum;

    private int maxResult;

    private int resultCount;

    private String requestSatellite;

    private String imagingMode;

    private Date dateStart;

    private Date dateEnd;

    private String requestName;

    private String keyword;

    private String orderby;

    public int getCurPageNum() {
        return curPageNum;
    }

    public void setCurPageNum(int curPageNum) {
        this.curPageNum = curPageNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public String getRequestSatellite() {
        return requestSatellite;
    }

    public void setRequestSatellite(String requestSatellite) {
        this.requestSatellite = requestSatellite;
    }

    public String getImagingMode() {
        return imagingMode;
    }

    public void setImagingMode(String imagingMode) {
        this.imagingMode = imagingMode;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }
}
