package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.ReceivingMap;
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
public class ReceivingMapDao {
    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public void save( ReceivingMap entity) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(entity);
    }

    public List<ReceivingMap> selectReceivingMapByImagingTaskId(int imagingTaskId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ReceivingMap where imagingTaskId=" + imagingTaskId + " order by id");
        List<ReceivingMap> list = query.list();
        return list;
    }

    public List<ReceivingMap> selectReceivingMapByReceivingPlanId(int receivingPlanId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ReceivingMap where receivingPlanId=" + receivingPlanId + " order by id");
        List<ReceivingMap> list = query.list();
        return list;
    }
}
