package com.charmingglobe.gr.service;

import com.charmingglobe.gr.constants.ImagingPlanStatus;
import com.charmingglobe.gr.cri.ImagingPlanCri;
import com.charmingglobe.gr.dao.ImagingPlanDao;
import com.charmingglobe.gr.dao.UserRequestDao;
import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.UserRequest;
import com.charmingglobe.gr.geo.GeometryTools;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2018/2/5.
 */
@Service
public class ImagingPlanService {

    final int MAX_RESULT = 50;

    @Autowired
    ImagingPlanDao imagingPlanDao;

    @Autowired
    UserRequestDao userRequestDao;

    @Autowired
    GeometryTools geometryTools;

    public ImagingPlan getImagingPlan(int imagingPlanId) {
        return imagingPlanDao.getImagingPlan(imagingPlanId);
    }

    public List<ImagingPlan> getImagingPlanByDate(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        Date date = c.getTime();
        return imagingPlanDao.selectImagingPlanByDate(date);
    }

    private void submitImagingPlan(ImagingPlan imagingPlan) throws Exception {
        String requestId = imagingPlan.getRequestId();
        List<UserRequest> userRequests = userRequestDao.selectUserRequestByRequestId(requestId);
        if (userRequests.size() == 0) {
            throw new Exception("cannot find requestId[" + requestId +"]");
        }
        imagingPlan.setUserRequestId(userRequests.get(0).getId());
        imagingPlan.setRequestId(userRequests.get(0).getRequestId());

        String imagingWkt = imagingPlan.getImagingWkt();
        if (imagingWkt == null || "".equals(imagingWkt)) {
            throw new Exception("imagingWkt=null");
        }
        Geometry imagingGeometry = geometryTools.getGeometryFromWKT(imagingWkt);
        if (null == imagingGeometry) {
            throw new Exception("error in parsing imagingGeometry");
        }
        imagingPlan.setImagingGeometry(imagingGeometry);
        String planId = getNextPlanId();
        imagingPlan.setPlanId(planId);
        imagingPlan.setStatus(ImagingPlanStatus.PENDING);
        imagingPlan.setCreateTime(new Date());
        imagingPlanDao.saveImagingPlan(imagingPlan);
    }

    public String inputImagingPlans(List<ImagingPlan> imagingPlans) {
        String result = "[\n";
        if (null != imagingPlans) {
            for (ImagingPlan imagingPlan : imagingPlans) {
                String requestId = imagingPlan.getRequestId();
                try {
                    submitImagingPlan(imagingPlan);
                    result += "\tinput imagingPlan[requestId=" + requestId + "]=success\n";
                } catch (Exception e) {
                    result += "\tinput imagingPlan[requestId=" + requestId + "]=error, error=[" + e.getMessage() + "]\n";
                }
            }
        }
        result += "]";
        return result;
    }

    private String getNextPlanId() {
        int count = imagingPlanDao.countImagingPlanByDate(new Date());
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String timestamp = f.format(new Date());
        return "IMG_PLAN_" + timestamp + "_" + (new String(10001 + count + "").substring(1, 5));
    }

    public void saveImagingPlan(ImagingPlan imagingPlan) {
        imagingPlanDao.saveImagingPlan(imagingPlan);
    }

    public List<ImagingPlan> getImagingPlanList(ImagingPlanCri cri) {

        cri.setMaxResult(MAX_RESULT);

        int pageNum = cri.getCurPageNum();
        if (pageNum < 0) {
            pageNum = 0;
            cri.setCurPageNum(pageNum);
        }

        int resultCount = new Long(imagingPlanDao.countImagingPlan(cri)).intValue();
        int totalPageNum = resultCount % MAX_RESULT == 0 ? resultCount / MAX_RESULT : resultCount / MAX_RESULT + 1;
        if (pageNum > totalPageNum) {
            pageNum = totalPageNum;
            cri.setCurPageNum(totalPageNum);
        }

        cri.setTotalPageNum(totalPageNum);
        cri.setResultCount(resultCount);

        return imagingPlanDao.selectImagingPlan(cri);
    }

    public List<ImagingPlan> getImagingPlanListByRequest(int userRequestId) {
        return imagingPlanDao.selectImagingPlan(userRequestId);
    }
}
