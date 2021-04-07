package com.hzmyself.crm.workbench.web.controller;

import com.hzmyself.crm.settings.entity.User;
import com.hzmyself.crm.settings.service.UserService;
import com.hzmyself.crm.utils.DateTimeUtil;
import com.hzmyself.crm.utils.UUIDUtil;
import com.hzmyself.crm.vo.PaginationVO;
import com.hzmyself.crm.workbench.entity.Activity;
import com.hzmyself.crm.workbench.service.ActivityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private UserService userService;
    @Resource
    private ActivityService activityService;

    @RequestMapping("/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        List<User> userList = userService.getUserList();
        return userList;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public Map<String,Boolean> saveActivity(HttpServletRequest request,Activity activity){
        String id = UUIDUtil.getUUID();
        //创建时间,当前系统时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人:当前登录用户
        User user = (User) request.getSession().getAttribute("user");
        String createBy = user.getName();
        activity.setId(id);
        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);
        boolean result = activityService.saveActivity(activity);
        Map<String,Boolean> map = new HashMap<>();
        map.put("success",result);
        return map;
    }

    @RequestMapping(value = "pageList.do",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public PaginationVO<Activity> getPageList(Activity ac, @RequestParam("pageNo")Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;//
        Map<String,Object> map = new HashMap<>();
        map.put("owner",ac.getOwner());
        map.put("name",ac.getName());
        map.put("startDate",ac.getStartDate());
        map.put("endDate",ac.getEndDate());
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        PaginationVO<Activity> vo = activityService.getPageList(map);
        return vo;
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Map<String,Object> delete(String[] ids){
        boolean flag = activityService.delete(ids);
        Map<String,Object> map = new HashMap<>();
        map.put("success",flag);
        return map;

    }
}
