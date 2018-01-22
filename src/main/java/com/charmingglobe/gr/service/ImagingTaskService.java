package com.charmingglobe.gr.service;

import com.charmingglobe.gr.dao.ImagingTaskDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by PANZHENG on 2018/1/22.
 */
@Service
public class ImagingTaskService {

    @Autowired
    private ImagingTaskDao imagingTaskDao;
}
