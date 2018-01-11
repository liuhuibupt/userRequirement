package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.UserRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2017/12/4.
 */
@Component
public class UserRequestDao {

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public List<UserRequest> selectUserRequest() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequest where 1=1 order by id asc");
        List<UserRequest> list = query.list();
        return list;
    }

    public UserRequest getUserRequest(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        UserRequest entity = session.get(UserRequest.class, id);
        return entity;
    }

    public void saveUserRequest(UserRequest userRequest) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(userRequest);
    }

    public int countUserRequestByDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("select count(*) from UserRequest where submitTime >= '" + f.format(date) + "'");
        int count = ((Long) query.uniqueResult()).intValue();

        return count;
    }
}
