package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.ImagingPlan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/5.
 */
@Component
public class ImagingPlanDao {
    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public ImagingPlan getImagingPlan(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        ImagingPlan entity = session.get(ImagingPlan.class, id);
        return entity;
    }

    public void saveImagingPlan(ImagingPlan entity) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    public List<ImagingPlan> selectImagingPlan() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingPlan where 1=1 order by id asc");
        List<ImagingPlan> resultList = query.list();

        return resultList;
    }

    public List<ImagingPlan> selectImagingPlan(int userRequestId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingPlan where userRequestId=" + userRequestId + " order by id asc");
        List<ImagingPlan> resultList = query.list();

        return resultList;
    }
}
