package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.UserRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by PANZHENG on 2018/1/22.
 */
@Component
public class ImagingTaskDao {

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public ImagingTask getImagingTask(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        ImagingTask entity = session.get(ImagingTask.class, id);
        return entity;
    }

    public void saveImagingTask(ImagingTask imagingTask) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(imagingTask);
    }

    public List<ImagingTask> selectImagingTask() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingTask where 1=1 order by id asc");
        List<ImagingTask> resultList = query.list();

        return resultList;
    }
}
