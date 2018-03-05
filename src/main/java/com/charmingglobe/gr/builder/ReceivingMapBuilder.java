package com.charmingglobe.gr.builder;

import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.ReceivingMap;
import com.charmingglobe.gr.entity.ReceivingPlan;

/**
 * Created by PANZHENG on 2018/2/25.
 */
public class ReceivingMapBuilder {

    public static ReceivingMap create(ReceivingPlan receivingPlan, ImagingTask imagingTask) {
        ReceivingMap receivingMap = new ReceivingMap();

        receivingMap.setReceivingPlanId(receivingPlan.getId());
        receivingMap.setTrPlanId(receivingPlan.getTrPlanId());
        receivingMap.setImagingTaskId(imagingTask.getId());
        receivingMap.setOtTaskId(imagingTask.getOtTaskId());

        return receivingMap;
    }
}
