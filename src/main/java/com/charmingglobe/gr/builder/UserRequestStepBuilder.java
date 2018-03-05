package com.charmingglobe.gr.builder;

import com.charmingglobe.gr.entity.RequestStep;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/23.
 */
public class UserRequestStepBuilder {

    public static RequestStep submit(int userRequestId) {

        RequestStep step = new RequestStep();

        step.setUserRequestId(userRequestId);
        step.setStepName(com.charmingglobe.gr.constants.RequestStep.SUBMITTED);
        step.setOccurrenceTime(new Date());
        step.setJumpLink("#");
        step.setIcon("add to cart");

        return step;
    }

    public static RequestStep planning(int userRequestId, int imagingPlanId) {

        RequestStep step = new RequestStep();

        step.setUserRequestId(userRequestId);
        step.setStepName(com.charmingglobe.gr.constants.RequestStep.PLANNING);
        step.setOccurrenceTime(new Date());
        step.setJumpLink("imagingPlan?imagingPlanId=" + imagingPlanId);
        step.setIcon("world");

        return step;
    }

    public static RequestStep imaging(int userRequestId, int imagingTaskId) {

        RequestStep step = new RequestStep();

        step.setUserRequestId(userRequestId);
        step.setStepName(com.charmingglobe.gr.constants.RequestStep.IMAGING);
        step.setOccurrenceTime(new Date());
        step.setJumpLink("imagingTask?imagingTaskId=" + imagingTaskId);
        step.setIcon("photo");

        return step;
    }
}
