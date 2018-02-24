package com.charmingglobe.gr.service;

import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.entity.Cavalier;
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

    public List<Cavalier> getAllUsers() {
        return userDao.selectUser();
    }

    public Cavalier getUser(int userId) {
        return userDao.getUser(userId);
    }

    public void registerUser(Cavalier user) throws Exception {
        String userName = user.getUserName();
        List<Cavalier> existList = userDao.getUser(userName);
        if (existList.size() > 0) {
            throw new Exception("用户名[" + userName + "]已存在");
        } else {
            userDao.saveUser(user);
        }
    }

    public void saveUser(Cavalier user) {
        userDao.saveUser(user);
    }

    public void setEnable(int userId, boolean enable) {
        Cavalier user = userDao.getUser(userId);
        if (user != null) {
            user.setEnable(enable);
            userDao.saveUser(user);
        }
    }
}
