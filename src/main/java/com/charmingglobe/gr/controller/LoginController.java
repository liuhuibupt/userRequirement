package com.charmingglobe.gr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PANZHENG on 2017/11/17.
 * Edited by Liuhui on 2018/3/22
 */
@Controller
public class LoginController {

    @RequestMapping("/grc_login")
    public String grc_login() {
        return "login";
    }

    @RequestMapping("/gr_login")
    public String gr_login() {
        return "user_request";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "user_request";
    }
}
