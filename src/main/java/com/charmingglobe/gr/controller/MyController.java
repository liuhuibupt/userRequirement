package com.charmingglobe.gr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping("/today")
    public String today(Model model) {

        return "today";
    }

    @RequestMapping("/sticky")
    public String sticky(Model model) {

        return "sticky";
    }

}
