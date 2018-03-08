package com.charmingglobe.gr.service;

import com.charmingglobe.gr.constants.ImagingPlanStatus;
import com.charmingglobe.gr.constants.ImagingTaskStatus;
import com.charmingglobe.gr.cri.ImagingTaskCri;
import com.charmingglobe.gr.dao.ImagingPlanDao;
import com.charmingglobe.gr.dao.ImagingTaskDao;
import com.charmingglobe.gr.dao.ReceivingMapDao;
import com.charmingglobe.gr.entity.ImagingPlan;
import com.charmingglobe.gr.entity.ImagingTask;
import com.charmingglobe.gr.entity.ReceivingMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by PANZHENG on 2018/1/22.
 */
@Service
public class ImagingTaskService {

    @Autowired
    ImagingTaskDao imagingTaskDao;

    @Autowired
    ImagingPlanDao imagingPlanDao;

    @Autowired
    ReceivingMapDao receivingMapDao;

    public int submitImagingTask(ImagingTask imagingTask) throws Exception {

        String planId = imagingTask.getPlanId();
        List<ImagingPlan> imagingPlans = imagingPlanDao.selectImagingPlanByPlanId(planId);
        if (imagingPlans.size() == 0) {
            throw new Exception("Imaging Plan [planId=" + planId +"] does not exist");
        }
        ImagingPlan imagingPlan = imagingPlans.get(0);

        String taskId = getNextTaskId();
        imagingTask.setOtTaskId(taskId);

        int userRequestId = imagingPlan.getUserRequestId();
        imagingTask.setUserRequestId(userRequestId);

        String requestId = imagingPlan.getRequestId();
        imagingTask.setRequestId(requestId);

        imagingTask.setStatus(ImagingTaskStatus.PLANNED);
        imagingTask.setCreateTime(new Date());
        imagingTaskDao.saveImagingTask(imagingTask);

        int imagingTaskId = imagingTask.getId();

        imagingPlan.setStatus(ImagingPlanStatus.IMAGING);
        imagingPlanDao.saveImagingPlan(imagingPlan);

        return imagingTaskId;
    }

    public String inputImagingTasks(List<ImagingTask> imagingTasks) {
        String result = "[\n";
        for (ImagingTask imagingTask : imagingTasks) {
            String requestName = imagingTask.getRequestName();
            try {
                submitImagingTask(imagingTask);
                result += "\tinput imagingTask[RequestName=" + requestName + "]=success\n";
            } catch (Exception e) {
                result += "\tinput imagingTask[RequestName=" + requestName + "]=error, error=[" + e.getMessage() + "]\n";
            }
        }
        result += "]";
        return result;
    }

    private String getNextTaskId() {
        int count = imagingTaskDao.countImagingTaskByDate(new Date());
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String timestamp = f.format(new Date());
        return "IMG_TASK_" + timestamp + "_" + (new String(10001 + count + "").substring(1, 5));
    }

    public List<ImagingTask> getImagingTaskByDate(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, day);
        Date date = c.getTime();
        return imagingTaskDao.selectImagingTaskByDate(date);
    }

    public ImagingTask getImagingTask(int imagingTaskId) {
        return imagingTaskDao.getImagingTask(imagingTaskId);
    }

    public List<ImagingTask> getImagingTaskListByReceivingPlan(int receivingPlanId) {

        List<ReceivingMap> receivingMaps = receivingMapDao.selectReceivingMapByReceivingPlanId(receivingPlanId);
        List<ImagingTask> resultSet = new ArrayList<ImagingTask>();
        for (ReceivingMap receivingMap : receivingMaps) {
            int imagingPlanId = receivingMap.getImagingTaskId();
            ImagingTask imagingTask = imagingTaskDao.getImagingTask(imagingPlanId);
            resultSet.add(imagingTask);
        }
        return resultSet;
    }

    public ImagingTask getIamgingTaskByOtTaskId(String otTaskId) {
        ImagingTask imagingTask = imagingTaskDao.selectImagingTaskByOtTaskId(otTaskId);
        return imagingTask;
    }

    public List<ImagingTask> getImagingTaskList(ImagingTaskCri cri) {
        return imagingTaskDao.selectImagingTask();
    }

}
