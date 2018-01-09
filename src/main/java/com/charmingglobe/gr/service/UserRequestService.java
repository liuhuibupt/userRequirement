package com.charmingglobe.gr.service;

import com.charmingglobe.gr.dao.UserRequestDao;
import com.charmingglobe.gr.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by PANZHENG on 2017/12/4.
 */
@Service
public class UserRequestService {

    @Autowired
    private UserRequestDao userRequestDao;

    public void submitService(UserRequest userRequest) {
        userRequest.setSubmitTime(new Date());
        userRequestDao.saveUserRequest(userRequest);
    }

    public UserRequest getUserRequest(int userRequestId) {
        return userRequestDao.getUserRequest(userRequestId);
    }
}
