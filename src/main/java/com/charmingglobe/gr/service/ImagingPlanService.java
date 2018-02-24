package com.charmingglobe.gr.service;

import com.charmingglobe.gr.dao.ImagingPlanDao;
import com.charmingglobe.gr.entity.ImagingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PANZHENG on 2018/2/5.
 */
@Service
public class ImagingPlanService {

    @Autowired
    private ImagingPlanDao imagingPlanDao;

    public ImagingPlan getImagingPlan(int imagingPlanId) {
        return imagingPlanDao.getImagingPlan(imagingPlanId);
    }

    public void saveImagingPlan(ImagingPlan imagingPlan) {
        imagingPlanDao.saveImagingPlan(imagingPlan);
    }

    public List<ImagingPlan> getImagingPlanList() {
        return imagingPlanDao.selectImagingPlan();
    }

    public List<ImagingPlan> getImagingPlanListByRequest(int userRequestId) {
        return imagingPlanDao.selectImagingPlan(userRequestId);
    }
}
