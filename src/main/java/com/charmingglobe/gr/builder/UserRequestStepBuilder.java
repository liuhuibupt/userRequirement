package com.charmingglobe.gr.builder;

import com.charmingglobe.gr.constants.RequestStep;
import com.charmingglobe.gr.entity.UserRequestStep;

import java.util.Date;

/**
 * Created by PANZHENG on 2018/2/23.
 */
public class UserRequestStepBuilder {

    public static UserRequestStep submit(int userRequestId) {

        UserRequestStep step = new UserRequestStep();

        step.setUserRequestId(userRequestId);
        step.setStepName(RequestStep.SUBMITTED);
        step.setOccurrenceTime(new Date());
        step.setJumpLink("#");
        step.setIcon("add to cart");

        return step;
    }

    public static UserRequestStep planning(int userRequestId, int imagingPlanId) {

        UserRequestStep step = new UserRequestStep();

        step.setUserRequestId(userRequestId);
        step.setStepName(RequestStep.PLANNING);
        step.setOccurrenceTime(new Date());
        step.setJumpLink("imagingPlan?imagingPlanId=" + imagingPlanId);
        step.setIcon("world");

        return step;
    }
}
