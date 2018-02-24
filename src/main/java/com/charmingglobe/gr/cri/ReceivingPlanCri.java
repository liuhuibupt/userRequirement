package com.charmingglobe.gr.cri;

/**
 * Created by PANZHENG on 2018/2/21.
 */
public class ReceivingPlanCri {
    private int curPageNum;

    private int totalPageNum;

    private int maxResult;

    private int resultCount;

    private String trPlanId;

    private String satelliteId;

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

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }
}
