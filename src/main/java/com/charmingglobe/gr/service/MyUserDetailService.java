package com.charmingglobe.gr.service;

import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.entity.Cavalier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Service("myUserDetailService")
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cavalier userDetails;
        List<Cavalier> users = userDao.getUser(username);
        if (null != users && users.size() > 0) {

            userDetails = users.get(0);
            String userRole = userDetails.getRole();
            userDetails.addAuthority(userRole);

            userDetails.setLastRequestTime(new Date());
            userDao.saveUser(userDetails);
        } else {
            throw new UsernameNotFoundException("no user found");
        }
        return userDetails;
    }
}
