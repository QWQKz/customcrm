package com.hzmyself.crm.settings.service;

import com.hzmyself.crm.exception.LoginException;
import com.hzmyself.crm.settings.entity.User;

import java.util.List;

public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
