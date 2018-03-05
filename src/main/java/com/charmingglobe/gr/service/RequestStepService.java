package com.charmingglobe.gr.service;

import com.charmingglobe.gr.builder.UserRequestStepBuilder;
import com.charmingglobe.gr.dao.ImagingPlanDao;
import com.charmingglobe.gr.dao.ImagingTaskDao;
import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.RequestStep;
import com.charmingglobe.gr.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANZHENG on 2018/2/27.
 */
@Service
public class RequestStepService {

    @Autowired
    ImagingPlanDao imagingPlanDao;

    @Autowired
    ImagingTaskDao imagingTaskDao;

    public List<RequestStep> getRequestStepList(UserRequest userRequest) {
        List<RequestStep> requestStepList = new ArrayList<RequestStep>();
        int userRequestId = userRequest.getId();
        requestStepList.add(UserRequestStepBuilder.submit(userRequestId));

        List<ImagingPlan> imagingPlanList = imagingPlanDao.selectImagingPlan(userRequestId);

        List<ImagingTask> imagingTaskList = imagingTaskDao.selectImagingTaskByUserRequestId(userRequestId);
        return requestStepList;
    }
}
