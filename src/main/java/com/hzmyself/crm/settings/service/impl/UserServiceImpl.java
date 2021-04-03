package com.hzmyself.crm.settings.service.impl;

import com.hzmyself.crm.exception.LoginException;
import com.hzmyself.crm.exception.PermissionException;
import com.hzmyself.crm.settings.dao.UserDao;
import com.hzmyself.crm.settings.entity.User;
import com.hzmyself.crm.settings.service.UserService;
import com.hzmyself.crm.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {
        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        map.put("ip",ip);
        User user = userDao.login(map);
        if(user==null){
            throw new PermissionException("账号或密码错误！");
        }
        //如果程序能成功执行,密码正确
        //需要继续向下验证其他三项信息

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if(expireTime.compareTo(currentTime) < 0){
            throw new PermissionException("账号已失效！");
        }

        //判断账号是否被锁定
        if("0".equals(user.getLockState())){
            throw new PermissionException("账号已被锁定,请联系管理员！");
        }

        //判断ip地址
        String allows = user.getAllowIps();
        if(!allows.contains(ip)){
            throw new PermissionException("IP地址受限,请联系管理员！");
        }

        return user;
    }
}
