package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.UserRequestStep;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/23.
 */
@Component
public class UserRequestStepDao {

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public void save(UserRequestStep entity) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    public List<UserRequestStep> selectUserRequestStep(int userRequestId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequestStep where userRequestId=" + userRequestId + " order by id asc");
        List<UserRequestStep> resultList = query.list();
        return resultList;
    }
}
