package com.charmingglobe.gr.service;

import com.charmingglobe.gr.constants.ImagingPlanStatus;
import com.charmingglobe.gr.cri.ImagingPlanCri;
import com.charmingglobe.gr.dao.ImagingPlanDao;
import com.charmingglobe.gr.entity.ImagingPlan;
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
    private ImagingPlanDao imagingPlanDao;

    @Autowired
    private GeometryTools geometryTools;

    public ImagingPlan getImagingPlan(int imagingPlanId) {
        return imagingPlanDao.getImagingPlan(imagingPlanId);
    }

    public List<ImagingPlan> getImagingPlanByDate(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        Date date = c.getTime();
        return imagingPlanDao.selectImagingPlanByDate(date);
    }

    public String inputImagingPlans(List<ImagingPlan> imagingPlans) {
        String result = "[\n";
        int count = 0;
        if (null!= imagingPlans) {
            for (ImagingPlan imagingPlan: imagingPlans) {
                String requestId = imagingPlan.getRequestId();
                String imagingWkt = imagingPlan.getImagingWkt();
                if (imagingWkt == null || "".equals(imagingWkt)) {
                    result += "requestId=" + requestId + ", error=[imagingWkt=null]\n";
                    continue;
                }
                Geometry imagingGeometry = geometryTools.getGeometryFromWKT(imagingWkt);
                if (null == imagingGeometry) {
                    result += "requestId=" + requestId + ", error=[error in parsing imagingGeometry]\n";
                    continue;
                }
                imagingPlan.setImagingGeometry(imagingGeometry);
                String planId = getNextPlanId();
                imagingPlan.setPlanId(planId);
                imagingPlan.setStatus(ImagingPlanStatus.PENDING);
                imagingPlan.setCreateTime(new Date());
                imagingPlanDao.saveImagingPlan(imagingPlan);
                result += "requestId=" + requestId + ", success\n";
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
