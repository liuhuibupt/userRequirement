package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.service.ImagingPlanService;
import com.charmingglobe.gr.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/5.
 */
@Controller
public class ImagingPlanController {

    @Autowired
    private ImagingPlanService imagingPlanService;

    @Autowired
    private UserRequestService userRequestService;

    @RequestMapping("/imagingPlan")
    public String getImagingPlan(int imagingPlanId, Model model) {
        ImagingPlan imagingPlan = imagingPlanService.getImagingPlan(imagingPlanId);

        int userRequestId = imagingPlan.getUserRequestId();
        UserRequest userRequest = userRequestService.getUserRequest(userRequestId);
        List<ImagingPlan> imagingPlanList = imagingPlanService.getImagingPlanListByRequest(userRequestId);

        model.addAttribute("userRequest", userRequest);
        model.addAttribute("imagingPlanList", imagingPlanList);
        return "imaging_plan";
    }

    @RequestMapping("/imagingPlan-list")
    public String getImagingPlanList(Model model) {
        List<ImagingPlan> imagingTaskList = imagingPlanService.getImagingPlanList();
        model.addAttribute("resultSet", imagingTaskList);
        return "imaging_plan_list";
    }
}
