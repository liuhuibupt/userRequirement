package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.cri.ReceivingPlanCri;
import com.charmingglobe.gr.entity.ReceivingPlan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Created by PANZHENG on 2018/1/24.
 */
@Controller
public class ReceivingPlanDao {
    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public void saveReceivingPlan( ReceivingPlan entity) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    public ReceivingPlan getReceivingPlan(int receivingPlanId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        ReceivingPlan entity = session.get(ReceivingPlan.class, receivingPlanId);
        return entity;
    }

    public int countReceivingPlan(ReceivingPlanCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();
        String where = getSelectUserRequestSqlWhere(cri);
        Query query = session.createQuery("select count(*) from ReceivingPlan " + where);
        int a = ((Long) query.uniqueResult()).intValue();
        return a;
    }

    public List<ReceivingPlan> selectReceivingPlan(ReceivingPlanCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();
        String where = getSelectUserRequestSqlWhere(cri);
        String orderby = getSelectUserRequestSqlOrderby(cri);
        Query query = session.createQuery("from ReceivingPlan " + where + orderby);

        int pageNum = cri.getCurPageNum();
        int maxResult = cri.getMaxResult();
        int beginIndex = pageNum * maxResult;
        query.setFirstResult(beginIndex);
        query.setMaxResults(maxResult);
        List<ReceivingPlan> list = query.list();

        return list;
    }


    private String getSelectUserRequestSqlWhere(ReceivingPlanCri cri) {
        String where = "where 1=1 ";

        String trPlanId = cri.getTrPlanId();
        if (null != trPlanId && !"".equals(trPlanId)) {
            where += " and trPlanId = '" + trPlanId + "' ";
        }

        String requestSatellite = cri.getSatelliteId();
        if (null != requestSatellite && !"".equals(requestSatellite)) {
            where += " and satelliteId = '" + requestSatellite + "' ";
        }

        return where;
    }

    private String getSelectUserRequestSqlOrderby(ReceivingPlanCri cri) {
        String orderby = " order by ";
        String mode = cri.getOrderby();

        if (null != mode && !"".equals(mode)) {
            if ("submitTimeAsc".equals(mode)) {
                orderby += "submitTime asc";
            }

            if ("submitTimeDesc".equals(mode)) {
                orderby += "submitTime desc";
            }
        } else {
            orderby += "submitTime desc";
        }


        return "";
    }
}
