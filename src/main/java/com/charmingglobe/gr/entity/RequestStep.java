package com.charmingglobe.gr.entity;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/23.
 */
public class RequestStep {

    private int userRequestId;

    private String stepName;

    private Date occurrenceTime;

    private String jumpLink;

    private String icon;

    public int getUserRequestId() {
        return userRequestId;
    }

    public void setUserRequestId(int userRequestId) {
        this.userRequestId = userRequestId;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public String getJumpLink() {
        return jumpLink;
    }

    public void setJumpLink(String jumpLink) {
        this.jumpLink = jumpLink;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
