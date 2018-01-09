package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.entity.User0;
import com.charmingglobe.gr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by PANZHENG on 2017/11/18.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user-list")
    public String listUsers(Model model) {
        List<User0> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "user_list";
    }
}
