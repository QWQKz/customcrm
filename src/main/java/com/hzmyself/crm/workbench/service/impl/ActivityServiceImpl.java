package com.hzmyself.crm.workbench.service.impl;

import com.hzmyself.crm.settings.entity.User;
import com.hzmyself.crm.workbench.dao.ActivityDao;
import com.hzmyself.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;


}
