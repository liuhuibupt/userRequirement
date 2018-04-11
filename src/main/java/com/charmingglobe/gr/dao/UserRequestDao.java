package com.charmingglobe.gr.dao;

import com.charmingglobe.gr.constants.RequestStatus;
import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.entity.Cavalier;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.entity.UserRequestSatellites;
import com.charmingglobe.gr.geo.GeometryTools;
import com.charmingglobe.gr.utils.ImagingParaConverter;
import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by PANZHENG on 2017/12/4.
 * Edited by Liuhui on 2018/3/17
 */
@Component
public class UserRequestDao {

    @Autowired
    private GeometryTools geometryTools;

    @Autowired
    @Qualifier("sessionFactoryForWriting")
    private SessionFactory sessionFactoryForWriting;

    @Autowired
    @Qualifier("sessionFactoryForReading")
    private SessionFactory sessionFactoryForReading;

    public void saveUserRequest(UserRequest userRequest) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(userRequest);
    }

    public void saveUserRequestSatellites(UserRequestSatellites userRequestSatellites) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        session.saveOrUpdate(userRequestSatellites);
    }

    public void saveUserRequest(int userRequestId, String status) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        UserRequest userRequest = session.get(UserRequest.class, userRequestId);
        if (null != userRequest) {
            userRequest.setStatus(status);
            session.update(userRequest);
        }

    }

    public void deleteUserRequest(int userRequestId){
        Session session = sessionFactoryForWriting.getCurrentSession();
        String hql = "Delete FROM UserRequest Where id="+userRequestId;
        Query q = session.createQuery(hql);
        q.executeUpdate() ;
    }

    public void deleteUserRequestSatellite(int userRequestSatelliteId){
        Session session = sessionFactoryForWriting.getCurrentSession();
        String hql = "Delete FROM UserRequestSatellites Where id="+userRequestSatelliteId;
        Query q = session.createQuery(hql);
        q.executeUpdate() ;
    }

    public void updateUserRequest(UserRequest userRequest) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        if (null != userRequest) {
            session.saveOrUpdate(userRequest);
        }

    }

    public int countUserRequestByDate(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:01");
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("select count(*) from UserRequest where submitTime >= '" + f.format(date) + "'");
        int count = ((Long) query.uniqueResult()).intValue();

        return count;
    }

    public int countUserRequest(UserRequestCri cri) {
        Session session = sessionFactoryForReading.getCurrentSession();
        String where = getSelectUserRequestSqlWhere(cri);
        Query query = session.createQuery("select count(*) from UserRequest " + where);
        int a = ((Long) query.uniqueResult()).intValue();
        return a;
    }

    public UserRequest getUserRequestForWriting(int id) {
        Session session = sessionFactoryForWriting.getCurrentSession();
        UserRequest entity = session.get(UserRequest.class, id);
        return entity;
    }

    public UserRequest getUserRequest(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        UserRequest entity = session.get(UserRequest.class, id);
        return entity;
    }

    public UserRequestSatellites  getUserRequestSatellites(int id) {
        Session session = sessionFactoryForReading.getCurrentSession();
        UserRequestSatellites entity = session.get(UserRequestSatellites.class, id);
        return entity;
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

    public List<UserRequest> selectUserRequest() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequest where 1=1 order by id asc");
        List<UserRequest> list = query.list();
        return list;
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

    public List<UserRequestSatellites> getAllUsersSatellites() {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequestSatellites where 1=1 order by id asc");
        List<UserRequestSatellites> list = query.list();
        return list;
    }

    public List<UserRequestSatellites> getUsersSatellitesByRequestNum(int requestNum) {
        Session session = sessionFactoryForReading.getCurrentSession();
        Query query = session.createQuery("from UserRequestSatellites where request_num='" + requestNum + "'");
        List<UserRequestSatellites> list = query.list();
        return list;
    }



}
