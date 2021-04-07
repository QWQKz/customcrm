package com.hzmyself.crm.workbench.service.impl;

import com.hzmyself.crm.vo.PaginationVO;
import com.hzmyself.crm.workbench.dao.ActivityDao;
import com.hzmyself.crm.workbench.dao.ActivityRemarkDao;
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
    @Resource
    private ActivityRemarkDao activityRemarkDao;


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
        acVO.setTotal(activityDao.getTotalByCondition(map));
        acVO.setDataList(aclist);
        return acVO;
    }

    @Override
    public boolean delete(String[] ids) {
        boolean flag = true;

        //查询出需要删除的备注的数量
        int count1 = activityRemarkDao.getCountByAids(ids);
        //删除备注,返回受影响的条数(实际删除的数量)
        int count2 = activityRemarkDao.deleteByAids(ids);
        if(count1 != count2){
            flag = false;
        }
        //最后删除市场活动
        int count3 = activityDao.delete(ids);
        flag = count3 != ids.length ? false:true;
        return flag;
    }

}
