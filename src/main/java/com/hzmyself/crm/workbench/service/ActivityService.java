package com.hzmyself.crm.workbench.service;

import com.hzmyself.crm.vo.PaginationVO;
import com.hzmyself.crm.workbench.entity.Activity;

import java.util.Map;

public interface ActivityService {
    boolean saveActivity(Activity ac);

    PaginationVO<Activity> getPageList(Map<String, Object> map);
}
