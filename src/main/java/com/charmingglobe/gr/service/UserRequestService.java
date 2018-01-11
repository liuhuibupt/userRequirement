package com.charmingglobe.gr.service;

import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.dao.UserRequestDao;
import com.charmingglobe.gr.entity.User0;
import com.charmingglobe.gr.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2017/12/4.
 */
@Service
public class UserRequestService {

    @Autowired
    private UserRequestDao userRequestDao;

    @Autowired
    private UserDao userDao;

    public void submitService(UserRequest userRequest, int submitterId) {
        User0 submitter = userDao.getUser(submitterId);

        String requestCode = getNextRequestCode();
        userRequest.setRequestCode(requestCode);

        userRequest.setSubmitter(submitter);
        userRequest.setSubmitTime(new Date());
        userRequestDao.saveUserRequest(userRequest);
    }

    private String getNextRequestCode() {
        int count = userRequestDao.countUserRequestByDate(new Date());
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String timestamp = f.format(new Date());

        return "IMG_I_" + timestamp + "_" + (count + 1);
    }

    public UserRequest getUserRequest(int userRequestId) {
        return userRequestDao.getUserRequest(userRequestId);
    }

    public List<UserRequest> getUserRequestList(UserRequestCri cri) {
        return userRequestDao.selectUserRequest();
    }
}
