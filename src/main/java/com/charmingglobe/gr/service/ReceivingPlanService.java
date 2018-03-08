package com.charmingglobe.gr.service;

import com.charmingglobe.gr.builder.ReceivingMapBuilder;
import com.charmingglobe.gr.constants.ReceivingPlanStatus;
import com.charmingglobe.gr.cri.ReceivingPlanCri;
import com.charmingglobe.gr.dao.ReceivingMapDao;
import com.charmingglobe.gr.dao.ReceivingPlanDao;
import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.ReceivingMap;
import com.charmingglobe.gr.entity.ReceivingPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2018/1/24.
 */
@Service
public class ReceivingPlanService {

    final int MAX_RESULT = 50;

    @Autowired
    ReceivingPlanDao receivingPlanDao;

    @Autowired
    ReceivingMapDao receivingMapDao;

    @Autowired
    ImagingTaskService imagingTaskService;

    public int submitReceivingPlan(ReceivingPlan receivingPlan) throws Exception{
        Date rightnow = new Date();

        String trPlanId = getTestTrPlanId();

        if (trPlanId == null || "".equals(trPlanId)) {
            throw new Exception("error[trPlanId=" + trPlanId + "]");
        }

        receivingPlan.setTrPlanId(trPlanId);
        receivingPlan.setCreateTime(rightnow);
        receivingPlan.setUpdateTime(rightnow);

        receivingPlan.setStatus(ReceivingPlanStatus.WAITING);
        receivingPlanDao.saveReceivingPlan(receivingPlan);

        mapImagingTask(receivingPlan);
        int id = receivingPlan.getId();
        return id;
    }

    private void mapImagingTask(ReceivingPlan receivingPlan) {
        List<String> otTaskIds = receivingPlan.getOtTaskIds();
        if (null != otTaskIds && !"".equals(otTaskIds)) {
            for (String otTaskId : otTaskIds) {
               ImagingTask imagingTask =  imagingTaskService.getImagingTaskByOtTaskId(otTaskId);
               if (null != imagingTask) {
                   ReceivingMap receivingMap = ReceivingMapBuilder.create(receivingPlan, imagingTask);
                   receivingMapDao.save(receivingMap);
               }
            }
        }
    }

    int count = 0;
    private String getTestTrPlanId() {
        count++;
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String timestamp = f.format(new Date());
        return "TEST_TrPlanId_" + timestamp + "_" + (new String(10001 + count + "").substring(1, 5));
    }

    public void saveReceivingPlan(ReceivingPlan receivingPlan) {
        receivingPlanDao.saveReceivingPlan(receivingPlan);
    }

    public ReceivingPlan getReceivingPlan(int receivingPlanId) {
        return receivingPlanDao.getReceivingPlan(receivingPlanId);
    }

    public List<ReceivingPlan> getReceivingPlanListByImagingTask(int imagingTaskId) {
        List<ReceivingMap> receivingMaps = receivingMapDao.selectReceivingMapByImagingTaskId(imagingTaskId);
        List<ReceivingPlan> resultSet = new ArrayList<ReceivingPlan>();
        for (ReceivingMap receivingMap : receivingMaps) {
            int receivingPlanId = receivingMap.getReceivingPlanId();
            ReceivingPlan receivingPlan = receivingPlanDao.getReceivingPlan(receivingPlanId);
            resultSet.add(receivingPlan);
        }
        return resultSet;
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
