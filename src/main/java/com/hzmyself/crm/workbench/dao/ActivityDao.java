package com.hzmyself.crm.workbench.dao;

import com.hzmyself.crm.workbench.entity.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int saveActivity(Activity activity);

    int getTotalByCondition();

    List<Activity> getActivityListByCondition(Map<String,Object> map);



}
