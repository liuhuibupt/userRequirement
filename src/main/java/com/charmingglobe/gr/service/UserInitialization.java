package com.charmingglobe.gr.service;

import com.charmingglobe.gr.constants.UserRole;
import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.entity.Cavalier;
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
        tryInitAdmin();
        tryInitAlex();
    }

    private void tryInitAlex() {
        String username = "alex";
        List<Cavalier> userList = userDao.getUser(username);
        if (userList == null || userList.size() == 0) {
            Cavalier user = new Cavalier();
            user.setUserName(username);
            user.setDisplayName("Alex Pan");
            user.setPassword("123456,");

            user.setDepartmentName("数据中心二室");
            user.setRole(UserRole.ROLE_ADMIN);

            user.setLastRequestTime(new Date());
            user.setEnable(true);
            userDao.saveUser(user);
        }
    }

    private void tryInitAdmin() {
        String username = "admin";
        List<Cavalier> userList = userDao.getUser(username);
        if (userList == null || userList.size() == 0) {
            Cavalier user = new Cavalier();
            user.setUserName(username);
            user.setDisplayName("ADMIN");
            user.setPassword("admin");

            user.setDepartmentName("数据中心二室");
            user.setRole(UserRole.ROLE_ADMIN);

            user.setLastRequestTime(new Date());
            user.setEnable(true);
            userDao.saveUser(user);
        }
    }
}
