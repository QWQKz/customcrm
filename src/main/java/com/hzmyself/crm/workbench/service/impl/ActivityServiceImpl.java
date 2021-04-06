package com.hzmyself.crm.workbench.service.impl;

import com.hzmyself.crm.vo.PaginationVO;
import com.hzmyself.crm.workbench.dao.ActivityDao;
import com.hzmyself.crm.workbench.entity.Activity;
import com.hzmyself.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;


    @Override
    public boolean saveActivity(Activity ac) {
        boolean flag = activityDao.saveActivity(ac) == 1 ? true:false;
        return flag;
    }

    @Override
    public PaginationVO<Activity> getPageList(Map<String, Object> map) {
        List<Activity> aclist = activityDao.getActivityListByCondition(map);
        PaginationVO<Activity> acVO = new PaginationVO<>();
        //获取查询到的总条数
        acVO.setTotal(activityDao.getTotalByCondition());
        acVO.setDataList(aclist);
        return acVO;
    }
}
