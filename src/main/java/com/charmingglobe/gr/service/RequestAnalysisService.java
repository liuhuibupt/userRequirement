package com.charmingglobe.gr.service;

import com.charmingglobe.gr.constants.RequestType;
import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.builder.ImagingPlanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANZHENG on 2018/2/5.
 */
@Service
public class RequestAnalysisService{

    private boolean isIdle = true;

    private List<UserRequest> userRequestForAnalysisList = new ArrayList<UserRequest>();

    @Autowired
    private ImagingPlanService imagingPlanService;

    public void submitRequest(UserRequest userRequest) {
        userRequestForAnalysisList.add(userRequest);
    }

    @Scheduled(cron = "0/2 * *  * * ? ")
    private void executeWhenIdle() {
        if (isIdle && userRequestForAnalysisList.size() > 0) {
            isIdle = false;
            analyse();
            isIdle = true;
        }
    }

    private void analyse() {
        List<UserRequest> userRequestList = new ArrayList<UserRequest>(this.userRequestForAnalysisList);
        userRequestForAnalysisList = new ArrayList<UserRequest>();
        for (UserRequest userRequest: userRequestList) {
            analyseUserRequest(userRequest);
        }
    }

    private void analyseUserRequest(UserRequest userRequest) {
        String requestType = userRequest.getRequestType();
        if (RequestType.REQUEST_TYPE_POINT.equals(requestType)) {
            ImagingPlan imagingPlan = ImagingPlanBuilder.create(userRequest);
            imagingPlanService.saveImagingPlan(imagingPlan);
        }
    }
}
