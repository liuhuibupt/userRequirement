package com.charmingglobe.gr.webservice;

import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.service.UserRequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by PANZHENG on 2018/1/15.
 */
@WebService
public class UserRequestWebService {

    @Autowired
    UserRequestService userRequestService;

    @WebMethod
    public String uploadUserRequest(String json) {
        System.out.println(json);

        Gson gson = new GsonBuilder().create();
        UserRequest userRequest = gson.fromJson(json, UserRequest.class);

        userRequestService.uploadUserRequest(userRequest);

        return "SUCCESS";
    }


}
