package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.entity.Cavalier;

import com.charmingglobe.gr.entity.UserAction;
import com.charmingglobe.gr.service.UserActionService;
import com.charmingglobe.gr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2017/11/18.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserActionService userActionService;

    @InitBinder
    public void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping("/userAction-list")
    public String getUserActionList(Model model) {
        List<UserAction> userActionList = userActionService.getUserActionList();
        model.addAttribute("resultSet", userActionList);
        return "user_action_list";
    }

    @RequestMapping("/user-list")
    public String listUsers(Model model) {
        List<Cavalier> userList = userService.getAllUsers();
        model.addAttribute("userList", userList);
        return "user_list";
    }

    @RequestMapping("/user")
    public String viewUser(int userId, Model model) {
        Cavalier user = userService.getUser(userId);
        model.addAttribute("user", user);

        UserDetails author = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        model.addAttribute("author", author);
        return "user_form";
    }

    @RequestMapping("/goRegister")
    public String goRegister() {
        return "user_register";
    }

    @RequestMapping("/registerUser")
    public String registerUser(Cavalier user, Model model) {
        model.addAttribute("user", user);
        try {
            userService.registerUser(user);
            model.addAttribute("user", user);
            model.addAttribute("success", "true");
        } catch (Exception e) {
            model.addAttribute("success", "false");
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "user_register";
    }

    @RequestMapping("/newUser")
    public String newUser() {
        return "user_form";
    }

    @RequestMapping("/saveUser")
    public String saveUser(Cavalier user) {
        userService.saveUser(user);
        int userId = user.getId();
        return "redirect:user?userId=" + userId;
    }

    @RequestMapping("/enableUser")
    public String enableUser(int userId) throws InterruptedException {
        userService.setEnable(userId, true);
        Thread.sleep(500);
        return "redirect:user?userId=" + userId;
    }

    @RequestMapping("/disableUser")
    public String disableUser(int userId) throws InterruptedException {
        userService.setEnable(userId, false);
        Thread.sleep(500);
        return "redirect:user?userId=" + userId;
    }
}
