package com.charmingglobe.gr.controller;

import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.entity.User0;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.geo.GeometryTools;
import com.charmingglobe.gr.service.UserRequestService;
import com.vividsolutions.jts.geom.Geometry;
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
public class UserRequestController {

    @Autowired
    private UserRequestService userRequestService;

    @Autowired
    private GeometryTools geometryTools;

    @InitBinder
    public void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(
                dateFormat, true));
    }

    @RequestMapping("/userRequest-list")
    public String getUserRequestList(UserRequestCri cri, Model model) {
        List<UserRequest> userRequestList = userRequestService.getUserRequestList(cri);
        model.addAttribute("resultSet", userRequestList);

        model.addAttribute("cri", cri);
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
    public String submitUserRequest(UserRequest userRequest, int submitterId) throws InterruptedException {
        userRequestService.submitUserRequest(userRequest, submitterId);
        int userRequestId = userRequest.getId();
        int count = 0;
        UserRequest replica = null;
        do {
            Thread.sleep(200);
            count++;
            replica = userRequestService.getUserRequest(userRequestId);
            if (count > 10)
                return "error";
        } while (replica == null);
        return "redirect:userRequest?userRequestId=" + userRequestId;
    }

    @RequestMapping("/userRequest")
    public String viewUserRequest(int userRequestId, Model model) {
        UserRequest userRequest = userRequestService.getUserRequest(userRequestId);

        User0 submitter = userRequest.getSubmitter();
        model.addAttribute("submitter", submitter);

        Geometry imagingGeometry = userRequest.getImagingGeometry();
        String imagingGeojson = geometryTools.getGeoJsonFromGeometry(imagingGeometry);
        model.addAttribute("imagingGeojson", imagingGeojson);

        model.addAttribute("userRequest", userRequest);

        UserDetails author = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        model.addAttribute("author", author);
        return "user_request";
    }

    @RequestMapping("/cancelUserRequest")
    public String cancelUserRequest(int userRequestId) throws InterruptedException {
        userRequestService.cancelUserRequest(userRequestId);
        Thread.sleep(500);
        return "redirect:userRequest?userRequestId=" + userRequestId;
    }
}
