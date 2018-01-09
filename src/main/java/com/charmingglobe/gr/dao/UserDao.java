package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.User0;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by PANZHENG on 2017/11/17.
 */
@Component
public class UserDao {

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public List<User0> selectUser() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from User0 where 1=1 order by id asc");
        List<User0> list = query.list();
        return list;
    }

    public List<User0> getUser(String username) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from User0 where userName='" + username + "' order by id asc");
        List<User0> list = query.list();
        return list;
    }
    public User0 getUser(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        User0 entity = session.get(User0.class, id);
        return entity;
    }

    public void saveUser(User0 user) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(user);
    }
}
