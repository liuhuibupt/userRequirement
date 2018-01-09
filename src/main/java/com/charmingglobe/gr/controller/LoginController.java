package com.charmingglobe.gr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Controller
public class LoginController {

    @RequestMapping("/gr_login")
    public String gr_login() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "redirect:today";
    }
}
