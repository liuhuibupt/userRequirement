package com.charmingglobe.gr.service;

import com.charmingglobe.gr.cri.ReceivingPlanCri;
import com.charmingglobe.gr.dao.ReceivingPlanDao;
import com.charmingglobe.gr.entity.ReceivingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2018/1/24.
 */
@Service
public class ReceivingPlanService {

    final int MAX_RESULT = 50;

    @Autowired
    private ReceivingPlanDao receivingPlanDao;

    public int submitReceivingPlan(ReceivingPlan receivingPlan) {
        Date nowtime = new Date();
        receivingPlan.setCreateTime(nowtime);
        receivingPlan.setUpdateTime(nowtime);

        receivingPlan.setStatus("测试录入");
        receivingPlanDao.saveReceivingPlan(receivingPlan);
        int id = receivingPlan.getId();
        return id;
    }

    public void saveReceivingPlan(ReceivingPlan receivingPlan) {
        receivingPlanDao.saveReceivingPlan(receivingPlan);
    }

    public ReceivingPlan getReceivingPlan(int receivingPlanId) {
        return receivingPlanDao.getReceivingPlan(receivingPlanId);
    }

    public List<ReceivingPlan> getReceivingPlanList(ReceivingPlanCri cri) {
        cri.setMaxResult(MAX_RESULT);

        int pageNum = cri.getCurPageNum();
        if (pageNum < 0) {
            pageNum = 0;
            cri.setCurPageNum(pageNum);
        }

        int resultCount = new Long(receivingPlanDao.countReceivingPlan(cri)).intValue();

        cri.setResultCount(resultCount);
        return receivingPlanDao.selectReceivingPlan(cri);
    }
}
