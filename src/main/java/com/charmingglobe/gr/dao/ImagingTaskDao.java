package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.entity.ImagingTask;
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

    public List<ImagingTask> selectImagingTaskByUserRequestId(int userRequestId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingTask where userRequestId=" + userRequestId + " order by id asc");
        List<ImagingTask> resultList = query.list();

        return resultList;
    }

    public ImagingTask selectImagingTaskByOtTaskId(String otTaskId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingTask where otTaskId='" + otTaskId + "' order by id asc");
        List<ImagingTask> resultSet = query.list();

        ImagingTask result = null;
        if (resultSet.size() > 0) {
            result= resultSet.get(0);
        }
        return result;
    }

    public ImagingTask getImagingTask(String taskId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from ImagingTask where taskId='" + taskId + "' order by id asc");
        List<ImagingTask> resultList = query.list();
        ImagingTask result = null;
        if (resultList.size() > 0) {
            result = resultList.get(0);
        }
        return result;
    }

    public int countImagingTaskByDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("select count(*) from ImagingTask where createTime >= '" + f.format(date) + "'");
        int count = ((Long) query.uniqueResult()).intValue();

        return count;
    }
}
