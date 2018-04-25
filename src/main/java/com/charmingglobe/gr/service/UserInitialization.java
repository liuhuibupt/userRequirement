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
 * EDITED by LIUHUI on 2018/4/16
 */
@Service
public class UserInitialization implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserDao userDao;

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        tryInitHui();
        tryInitPan();
    }

    private void tryInitHui() {
        String username = "hui";
        List<Cavalier> userList = userDao.getUser(username);
        if (userList == null || userList.size() == 0) {
            Cavalier user = new Cavalier();
            user.setUserName(username);
            user.setDisplayName("LiuHui");
            user.setPassword("123456");

            user.setDepartmentName("数据中心一室");
            user.setRole(UserRole.ROLE_ADMIN);

            user.setLastRequestTime(new Date());
            user.setEnable(true);
            userDao.saveUser(user);
        }
    }
    private void tryInitPan() {
        String username = "pan";
        List<Cavalier> userList = userDao.getUser(username);
        if (userList == null || userList.size() == 0) {
            Cavalier user = new Cavalier();
            user.setUserName(username);
            user.setDisplayName("PanShengNan");
            user.setPassword("123456");

            user.setDepartmentName("数据中心一室");
            user.setRole(UserRole.ROLE_ADMIN);

            user.setLastRequestTime(new Date());
            user.setEnable(true);
            userDao.saveUser(user);
        }
    }
}
