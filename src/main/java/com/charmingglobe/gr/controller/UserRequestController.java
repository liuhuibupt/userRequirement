package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.entity.User0;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.service.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by PANZHENG on 2017/11/18.
 */
@Controller
public class UserRequestController {

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private UserDao userDao;
    @RequestMapping("/userRequest-list")
    public String getUserRequestList() {
        return "user_request_list";
    }

    @RequestMapping("/userRequest-submit")
    public String viewUserRequestSubmit(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        if (userDetails instanceof User0) {
            User0 submitter = (User0) userDetails;
            model.addAttribute("submitter", submitter);
        }
        return "user_request";
    }

    @RequestMapping("/submitUserRequest")
    public String submitUserRequest(UserRequest userRequest, int submitterId) {

        User0 submitter = userDao.getUser(submitterId);

        userRequest.setSubmitter(submitter);
        userRequestService.submitService(userRequest);
        int userRequestId = userRequest.getId();
        return "redirect:userRequest?userRequestId=" + userRequestId;
    }

    @RequestMapping("/userRequest")
    public String viewUserRequest(int userRequestId, Model model) {
        UserRequest userRequest = userRequestService.getUserRequest(userRequestId);
        User0 submitter = userRequest.getSubmitter();

        model.addAttribute("submitter", submitter);
        model.addAttribute("userRequest", userRequest);
        return "user_request";
    }
}
