package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.entity.Cavalier;
import com.charmingglobe.gr.entity.UserRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public void saveUserRequest(int uerRequestId, String status) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        UserRequest userRequest = session.get(UserRequest.class, uerRequestId);
        if (null != userRequest) {
            userRequest.setStatus(status);
            session.update(userRequest);
        }
    }

    public  List<UserRequest> selectUserRequestByDate(Date date) {
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
        SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequest where submitTime >= '" + f1.format(date) + "' and submitTime <= '" + f2.format(date) + "' order by id asc");
        List<UserRequest> resultList = query.list();

        return resultList;
    }

    public  List<UserRequest> selectUserRequestByRequestId(String requestId) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequest where requestId='" + requestId + "'");
        List<UserRequest> resultList = query.list();

        return resultList;
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
        String orderby = getSelectUserRequestSqlOrderby(cri);
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
        Date dateStart = cri.getDateStart();
        if (null != dateStart) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
            where += " and submitTime > '" + f.format(dateStart) + "' ";
        }
        Date dateEnd = cri.getDateEnd();
        if (null != dateEnd) {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
            where += " and submitTime < '" + f.format(dateEnd) + "' ";
        }

        String requestSatellite = cri.getRequestSatellite();
        if (null != requestSatellite && !"".equals(requestSatellite)) {
            where += " and requestSatellites = '" + requestSatellite + "' ";
        }

        String imagingMode = cri.getImagingMode();
        if (null != imagingMode && !"".equals(imagingMode)) {
            where += " and imagingMode = '" + imagingMode + "' ";
        }

        String requestName = cri.getRequestName();
        if (null != requestName && !"".equals(requestName)) {
            where += " and requestName like '%" + requestName + "%' ";
        }

        String keyword = cri.getKeyword();
        if (null != keyword && !"".equals(keyword)) {
            where += " and keyword like '%" + keyword + "%' ";
        }

        boolean onlyme = cri.isOnlyme();
        if (onlyme) {
            Cavalier me = (Cavalier) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
            where += " and submitter.id = " + me.getId();
        }

        return where;
    }

    private String getSelectUserRequestSqlOrderby(UserRequestCri cri) {
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

        return orderby;
    }
}
