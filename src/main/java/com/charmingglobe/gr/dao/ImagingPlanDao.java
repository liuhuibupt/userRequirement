package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.cri.ImagingPlanCri;
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

    public List<ImagingPlan> selectImagingPlan(int userRequestId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingPlan where userRequestId=" + userRequestId + " order by id asc");
        List<ImagingPlan> resultList = query.list();

        return resultList;
    }

    public List<ImagingPlan> selectImagingPlanByPlanId(String planId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingPlan where planId='" + planId + "' order by id asc");
        List<ImagingPlan> resultList = query.list();

        return resultList;
    }

    public int countImagingPlan(ImagingPlanCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();
        String where = getSqlWhere(cri);
        Query query = session.createQuery("select count(*) from ImagingPlan " + where);
        int a = ((Long) query.uniqueResult()).intValue();
        return a;
    }

    public List<ImagingPlan> selectImagingPlan(ImagingPlanCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();

        String where = getSqlWhere(cri);
        String orderby = getSqlOrderby(cri);
        Query query = session.createQuery("from ImagingPlan " + where + orderby);

        int pageNum = cri.getCurPageNum();
        int maxResult = cri.getMaxResult();
        int beginIndex = pageNum * maxResult;
        query.setFirstResult(beginIndex);
        query.setMaxResults(maxResult);
        List<ImagingPlan> resultList = query.list();
        return resultList;
    }

    private String getSqlWhere(ImagingPlanCri cri) {
        String where = "where 1=1 ";

        String satelliteId = cri.getSatelliteId();
        if (null != satelliteId && !"".equals(satelliteId)) {
            where += " and requestSatellites like '%" + satelliteId + "%' ";
        }
        return where;
    }

    private String getSqlOrderby(ImagingPlanCri cri) {
        String orderby = " order by id desc";

        return orderby;
    }
}
