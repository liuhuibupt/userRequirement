package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.cri.ImagingTaskCri;
import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.service.ImagingTaskService;
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
    private ImagingTaskService imagingTaskService;

    @RequestMapping("/add-imagingTask")
    public String testAddImagingTask() {
        imagingTaskService.addImagingTask();
        return "redirect:imagingTask-list";
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
        //ImagingTask imagingTask = imagingTaskService.getImagingTask(imagingTaskId);
        //model.addAttribute("imagingTask", imagingTask);
        return "imaging_task";
    }
}
