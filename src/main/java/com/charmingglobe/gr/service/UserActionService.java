package com.charmingglobe.gr.service;

import com.charmingglobe.gr.builder.UserActionBuilder;
import com.charmingglobe.gr.dao.UserActionDao;
import com.charmingglobe.gr.entity.Cavalier;
import com.charmingglobe.gr.entity.UserAction;
import com.charmingglobe.gr.entity.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/8.
 */
@Service
public class UserActionService {

    @Autowired
    private UserActionDao userActionDao;

    public void addUserAction(UserRequest userRequest) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        UserAction userAction = UserActionBuilder.createSubmitUserRequestAction(userRequest, (Cavalier)userDetails);
        userActionDao.saveUserAction(userAction);
    }

    public List<UserAction> getUserActionList() {
        return userActionDao.selectUserAction();
    }
}
