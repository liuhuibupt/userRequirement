package com.charmingglobe.gr.service;

import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.dao.UserDao;
import com.charmingglobe.gr.dao.UserRequestDao;
import com.charmingglobe.gr.entity.User0;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.geo.GeometryTools;
import com.charmingglobe.gr.utils.TimeUtils;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2017/12/4.
 */
@Service
public class UserRequestService {

    final int MAX_RESULT = 50;

    @Autowired
    private UserRequestDao userRequestDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private GeometryTools geometryTools;

    public void uploadUserRequest(UserRequest userRequest) {

        String imagingWkt = userRequest.getImagingWkt();
        Geometry imagingGeometry = geometryTools.getGeometryFromWKT(imagingWkt);
        userRequest.setImagingGeometry(imagingGeometry);

        User0 submitter = userDao.getUser(1);
        userRequest.setSubmitter(submitter);

        userRequest.setRequestFrom("内部需求");
        userRequest.setRequestType("POINT");
        userRequest.setImagingMode("常规推扫");
        userRequest.setStatus("历史需求");

        String requestSatellites = userRequest.getRequestSatellites();

        if (null == requestSatellites || "".equals(requestSatellites)) {
            userRequest.setRequestSatellites("ALL-SATELLITES");
        }
        Date timenow = new Date();
        userRequest.setCreateDate(timenow);
        userRequest.setUpdateDate(timenow);
        userRequestDao.saveUserRequest(userRequest);
    }

    public void cancelUserRequest(int userRequestId) {
        userRequestDao.saveUserRequest(userRequestId, "Cancelled");
    }

    public void submitUserRequest(UserRequest userRequest, int submitterId) {
        User0 submitter = userDao.getUser(submitterId);

        String requestId = getNextRequestId();
        userRequest.setRequestId(requestId);

        String imagingWkt = userRequest.getImagingWkt();
        String requestType = userRequest.getRequestType();
        if (!"IN-SPACE".equals(requestType)) {
            Geometry imagingGeometry = geometryTools.getGeometryFromWKT(imagingWkt);
            userRequest.setImagingGeometry(imagingGeometry);
        }

        userRequest.setSubmitter(submitter);
        userRequest.setSubmitTime(new Date());

        userRequest.setRequestFrom("内部需求");
        userRequest.setStatus("等待规划");

        String requestSatellites = userRequest.getRequestSatellites();

        if (null == requestSatellites || "".equals(requestSatellites)) {
            userRequest.setRequestSatellites("ALL-SATELLITES");
        }
        userRequestDao.saveUserRequest(userRequest);
    }

    private String getNextRequestId() {
        int count = userRequestDao.countUserRequestByDate(new Date());
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String timestamp = f.format(new Date());
        return "REQ_IMG_" + timestamp + "_" + (new String(10001 + count + "").substring(1, 5));
    }

    public UserRequest getUserRequest(int userRequestId) {
        return userRequestDao.getUserRequest(userRequestId);
    }

    public List<UserRequest> getUserRequestList(UserRequestCri cri) {

        cri.setMaxResult(MAX_RESULT);

        int pageNum = cri.getCurPageNum();
        if (pageNum < 0) {
            pageNum = 0;
            cri.setCurPageNum(pageNum);
        }

        int resultCount = new Long(userRequestDao.countUserRequest(cri)).intValue();
        int totalPageNum = resultCount % MAX_RESULT == 0 ? resultCount / MAX_RESULT : resultCount / MAX_RESULT + 1;
        if (pageNum > totalPageNum) {
            pageNum = totalPageNum;
            cri.setCurPageNum(totalPageNum);
        }

        cri.setTotalPageNum(totalPageNum);
        cri.setResultCount(resultCount);

        List<UserRequest> userRequestList =  userRequestDao.selectUserRequest(cri);

        return beautifyUserRequestList(userRequestList, pageNum);
    }

    private List<UserRequest> beautifyUserRequestList(List<UserRequest> userRequestList, int pageNum) {
        int num = 1;
        Date zeroOfToday = TimeUtils.getZeroOfToday();
        for (UserRequest userRequest : userRequestList) {
            userRequest.setNum(pageNum * MAX_RESULT + num++);
            Date submitTime = userRequest.getSubmitTime();
            if (submitTime != null && submitTime.getTime() > zeroOfToday.getTime()) {
                userRequest.setLabel("today");
            }
        }
        return userRequestList;
    }
}
