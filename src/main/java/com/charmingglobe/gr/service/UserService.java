package com.charmingglobe.gr.service;

import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.entity.User0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PANZHENG on 2017/11/18.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User0> getAllUsers() {
        return userDao.selectUser();
    }
}
