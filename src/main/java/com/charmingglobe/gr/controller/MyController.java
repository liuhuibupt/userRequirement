package com.charmingglobe.gr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
//    待修改，添加按照今日搜索我提的所有用户需求
    @RequestMapping("/today")
    public String today(Model model) {
        return "user_request_list";
    }

    @RequestMapping("/sticky")
    public String sticky(Model model) {

        return "sticky";
    }

}
