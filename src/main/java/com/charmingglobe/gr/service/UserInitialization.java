package com.charmingglobe.gr.service;

import com.charmingglobe.gr.constants.UserRole;
import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.entity.User0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Service
public class UserInitialization implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserDao userDao;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        tryInitAlex();
        tryInitAdmin();
    }

    private void tryInitAlex() {
        String username = "Alex";
        List<User0> userList = userDao.getUser(username);
        if (userList == null || userList.size() == 0) {
            User0 user = new User0();
            user.setUserName(username);
            user.setDepartmentName("Alex Pan");
            user.setPassword("123456.");

            user.setDepartmentName("数据中心二室");
            user.setRole(UserRole.ROLE_ADMIN);

            user.setLastRequestTime(new Date());
            userDao.saveUser(user);
        }
    }

    private void tryInitAdmin() {
        String username = "Admin";
        List<User0> userList = userDao.getUser(username);
        if (userList == null || userList.size() == 0) {
            User0 user = new User0();
            user.setUserName(username);
            user.setDepartmentName("ADMIN");
            user.setPassword("admin");

            user.setDepartmentName("数据中心二室");
            user.setRole(UserRole.ROLE_ADMIN);

            user.setLastRequestTime(new Date());
            userDao.saveUser(user);
        }
    }
}
