package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.UserAction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/8.
 */
@Component
public class UserActionDao {

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public UserAction getUserAction(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        UserAction entity = session.get(UserAction.class, id);
        return entity;
    }

    public void saveUserAction(UserAction entity) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    public List<UserAction> selectUserAction() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserAction where 1=1 order by id asc");
        List<UserAction> resultList = query.list();

        return resultList;
    }
}
