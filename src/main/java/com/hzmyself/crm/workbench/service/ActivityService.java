package com.hzmyself.crm.workbench.service;

import com.hzmyself.crm.vo.PaginationVO;
import com.hzmyself.crm.workbench.entity.Activity;

import java.util.Map;

public interface ActivityService {
    boolean saveActivity(Activity ac);

    PaginationVO<Activity> getPageList(Map<String, Object> map);

    boolean delete(String[] ids);


    Map<String,Object> getUserListAndActivity(String id);

    boolean updateActivity(Activity activity);
}
