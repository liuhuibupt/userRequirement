package com.charmingglobe.gr.webservice;

import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.service.ImagingPlanService;
import com.charmingglobe.gr.service.ImagingTaskService;
import com.charmingglobe.gr.service.UserRequestService;
import com.charmingglobe.gr.webservice.result.ImagingPlanResult;
import com.charmingglobe.gr.webservice.result.ImagingTaskResult;
import com.charmingglobe.gr.webservice.result.UserRequestResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANZHENG on 2018/1/23.
 */
@WebService
public class MissionPlanWebService {

    private final String SUCCESS = "SUCCESS";
    @Autowired
    UserRequestService userRequestService;

    @Autowired
    ImagingTaskService imagingTaskService;

    @Autowired
    ImagingPlanService imagingPlanService;

    @WebMethod
    public List<UserRequestResult> getUserRequestList(int day) {
        List<UserRequest> userRequestList = userRequestService.getUserRequestByDate(day);
        List<UserRequestResult> resultList = new ArrayList<UserRequestResult>();
        for (UserRequest userRequest : userRequestList) {
            resultList.add(new UserRequestResult(userRequest));
        }
        return resultList;
    }

    @WebMethod
    public String inputImagingPlans(String json) {
        String result = SUCCESS;
        System.out.println(json);
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            List<ImagingPlan> imagingPlans = gson.fromJson(json, new TypeToken<List<ImagingPlan>>() {}.getType());
            int count = imagingPlanService.inputImagingPlans(imagingPlans);
            result += "[count=" + count + "]";
        } catch (Exception e) {
            e.printStackTrace();
            result = "ERROR[" + e.getMessage() + "]";
        }

        return result;
    }

    @WebMethod
    public List<ImagingPlanResult> getImagingPlanList(int day) {
        List<ImagingPlan> imagingPlanList = imagingPlanService.getImagingPlanByDate(day);
        List<ImagingPlanResult> resultList = new ArrayList<ImagingPlanResult>();
        for (ImagingPlan imagingPlan : imagingPlanList) {
            resultList.add(new ImagingPlanResult(imagingPlan));
        }
        return resultList;
    }

    @WebMethod
    public String inputImaingTasks(String json) {
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            List<ImagingTask> imagingTasks = gson.fromJson(json, new TypeToken<List<ImagingTask>>() {
            }.getType());
            //int id = imagingTaskService.submitImagingTask(imagingTask);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @WebMethod
    public List<ImagingTaskResult> getImagingTaskList(int day) {

        return null;
    }
}
