package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.cri.ImagingTaskCri;
import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.ReceivingPlan;
import com.charmingglobe.gr.service.ImagingTaskService;
import com.charmingglobe.gr.service.ReceivingPlanService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by PANZHENG on 2018/1/22.
 */
@Controller
public class ImagingTaskController {

    @Autowired
    ImagingTaskService imagingTaskService;

    @Autowired
    ReceivingPlanService receivingPlanService;

    @RequestMapping("/imagingTask-add")
    public String testAddImagingTask() {
        return "imaging_task_add";
    }

    @RequestMapping("/submitImagingTask")
    public String submitImagingTask(String json,  Model model) {
        System.out.println(json);
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            ImagingTask imagingTask = gson.fromJson(json, ImagingTask.class);
            int id = imagingTaskService.submitImagingTask(imagingTask);
            return "redirect:imagingTask?imagingTaskId=" + id;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/imagingTask-list")
    public String getImagingTaskList(ImagingTaskCri cri, Model model) {
        List<ImagingTask> imagingTaskList = imagingTaskService.getImagingTaskList(cri);
        model.addAttribute("resultSet", imagingTaskList);
        cri.setResultCount(imagingTaskList.size());
        model.addAttribute("cri", cri);
        return "imaging_task_list";
    }

    @RequestMapping("/imagingTask")
    public String getImagingTask(int imagingTaskId, Model model) {
        ImagingTask imagingTask = imagingTaskService.getImagingTask(imagingTaskId);
        model.addAttribute("imagingTask", imagingTask);

        List<ReceivingPlan> receivingPlanList = receivingPlanService.getReceivingPlanListByImagingTask(imagingTaskId);
        model.addAttribute("receivingPlanList", receivingPlanList);
        return "imaging_task";
    }
}
