package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.cri.ReceivingPlanCri;
import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.entity.ReceivingPlan;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.service.ReceivingPlanService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/21.
 */
@Controller
public class ReceivingPlanController {

    @Autowired
    private ReceivingPlanService receivingPlanService;

    @RequestMapping("/receivingPlan-add")
    public String addReceivingPlan() {
        return "receiving_plan_add";
    }

    @RequestMapping("/submitReceivingPlan")
    public String submitReceivingPlan(String json,  Model model) {
        System.out.println(json);
        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
            ReceivingPlan receivingPlan = gson.fromJson(json, ReceivingPlan.class);
            int id = receivingPlanService.submitReceivingPlan(receivingPlan);
            return "redirect:receivingPlan?receivingPlanId=" + id;
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/receivingPlan")
    public String getReceivingPlan(int receivingPlanId, Model model) {
        ReceivingPlan receivingPlan = receivingPlanService.getReceivingPlan(receivingPlanId);
        model.addAttribute("receivingPlan", receivingPlan);
        return "receiving_plan";
    }

    @RequestMapping("/receivingPlan-list")
    public String getReceivingPlanList(ReceivingPlanCri cri, Model model) {
        List<ReceivingPlan> receivingPlanList = receivingPlanService.getReceivingPlanList(cri);
        model.addAttribute("resultSet", receivingPlanList);
        model.addAttribute("cri", cri);
        return "receiving_plan_list";
    }
}
