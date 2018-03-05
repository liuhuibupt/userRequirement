package com.charmingglobe.gr.entity;

import javax.persistence.*;

/**
 * Created by PANZHENG on 2018/2/25.
 */
@Entity
@Table(name = "grc_receiving_map")
public class ReceivingMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "receiving_plan_id")
    private int receivingPlanId;

    @Column(name = "tr_plan_id")
    private String trPlanId;

    @Column(name = "ot_task_id")
    private String otTaskId;

    @Column(name = "imaging_task_id")
    private int imagingTaskId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceivingPlanId() {
        return receivingPlanId;
    }

    public void setReceivingPlanId(int receivingPlanId) {
        this.receivingPlanId = receivingPlanId;
    }

    public String getTrPlanId() {
        return trPlanId;
    }

    public void setTrPlanId(String trPlanId) {
        this.trPlanId = trPlanId;
    }

    public String getOtTaskId() {
        return otTaskId;
    }

    public void setOtTaskId(String otTaskId) {
        this.otTaskId = otTaskId;
    }

    public int getImagingTaskId() {
        return imagingTaskId;
    }

    public void setImagingTaskId(int imagingTaskId) {
        this.imagingTaskId = imagingTaskId;
    }
}
