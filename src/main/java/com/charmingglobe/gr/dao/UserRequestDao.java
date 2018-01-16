package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.cri.UserRequestCri;
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

    public List<UserRequest> selectUserRequest() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequest where 1=1 order by id asc");
        List<UserRequest> list = query.list();
        return list;
    }

    public int countUserRequest(UserRequestCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();
        String where = getSelectUserRequestSqlWhere(cri);
        Query query = session.createQuery("select count(*) from UserRequest " + where);
        int a = ((Long) query.uniqueResult()).intValue();
        return a;
    }

    public List<UserRequest> selectUserRequest(UserRequestCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();
        String where = getSelectUserRequestSqlWhere(cri);
        String orderby = " order by submitTime asc";
        Query query = session.createQuery("from UserRequest " + where + orderby);

        int pageNum = cri.getCurPageNum();
        int maxResult = cri.getMaxResult();
        int beginIndex = pageNum * maxResult;
        query.setFirstResult(beginIndex);
        query.setMaxResults(maxResult);
        List<UserRequest> list = query.list();

        return list;
    }

    private String getSelectUserRequestSqlWhere(UserRequestCri cri) {
        String where = "where 1=1 ";

        return where;
    }
}
