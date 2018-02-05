package com.charmingglobe.gr.service;

import com.charmingglobe.gr.cri.ImagingTaskCri;
import com.charmingglobe.gr.dao.ImagingTaskDao;
import com.charmingglobe.gr.entity.ImagingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by PANZHENG on 2018/1/22.
 */
@Service
public class ImagingTaskService {

    @Autowired
    private ImagingTaskDao imagingTaskDao;

    public void addImagingTask() {
        ImagingTask imagingTask = new ImagingTask();
        imagingTask.setTaskId("IMG_TASK_2018_01_12_0001");
        imagingTask.setTaskName("123");
        imagingTaskDao.saveImagingTask(imagingTask);
    }

    public ImagingTask getImagingTask(int imagingTaskId) {
        return imagingTaskDao.getImagingTask(imagingTaskId);
    }

    public List<ImagingTask> getImagingTaskList(ImagingTaskCri cri) {
        return imagingTaskDao.selectImagingTask();
    }
}
