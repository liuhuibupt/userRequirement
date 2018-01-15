package com.charmingglobe.gr.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by PANZHENG on 2018/1/15.
 */
@WebService
public class UserRequestWebService {

    @WebMethod
    public String uploadUserRequest(String json) {
        return "SUCCESS";
    }
}
