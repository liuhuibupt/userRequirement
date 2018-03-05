package com.charmingglobe.gr.service;

import com.charmingglobe.gr.cri.ImagingPlanCri;
import com.charmingglobe.gr.cri.UserRequestCri;
import com.charmingglobe.gr.dao.ImagingPlanDao;
import com.charmingglobe.gr.entity.Cavalier;
import com.charmingglobe.gr.entity.ImagingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    public ImagingPlan getImagingPlan(int imagingPlanId) {
        return imagingPlanDao.getImagingPlan(imagingPlanId);
    }

    public int inputImagingPlans(List<ImagingPlan> imagingPlans) {
        int count = 0;
        if (null!= imagingPlans) {
            for (ImagingPlan imagingPlan: imagingPlans) {
                imagingPlanDao.saveImagingPlan(imagingPlan);
                count++;
            }
        }
        return count;
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
