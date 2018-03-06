package com.charmingglobe.gr.builder;

import com.charmingglobe.gr.constants.ImagingPlanStatus;
import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.UserRequest;

/**
 * Created by PANZHENG on 2018/2/5.
 */
public class ImagingPlanBuilder {

    public static ImagingPlan create(UserRequest userRequest) {
        ImagingPlan imagingPlan = new ImagingPlan();

        imagingPlan.setUserRequestId(userRequest.getId());
        String requestId = userRequest.getRequestId();

        imagingPlan.setRequestId(requestId);
        imagingPlan.setRequestName(userRequest.getRequestName());
        imagingPlan.setPlanId(requestId.replace("REQ_IMG_", "PLAN_") + "_1");
        imagingPlan.setPlanStartTime(userRequest.getRequestStart());
        imagingPlan.setPlanEndTime(userRequest.getRequestEnd());
        //imagingPlan.setRequestSatellites(userRequest.getRequestSatellites());
        imagingPlan.setStatus(ImagingPlanStatus.PENDING);

        return imagingPlan;
    }
}
