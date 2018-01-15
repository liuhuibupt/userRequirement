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

    public User0 getUser(int userId) {
        return userDao.getUser(userId);
    }

    public void registerUser(User0 user) throws Exception {
        String userName = user.getUserName();
        List<User0> existList = userDao.getUser(userName);
        if (existList.size() > 0) {
            throw new Exception("用户名[" + userName + "]已存在");
        } else {
            userDao.saveUser(user);
        }
    }

    public void saveUser(User0 user) {
        userDao.saveUser(user);
    }

    public void setEnable(int userId, boolean enable) {
        User0 user = userDao.getUser(userId);
        if (user != null) {
            user.setEnable(enable);
            userDao.saveUser(user);
        }
    }
}
