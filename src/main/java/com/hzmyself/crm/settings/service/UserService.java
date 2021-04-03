package com.hzmyself.crm.settings.service;

import com.hzmyself.crm.exception.LoginException;
import com.hzmyself.crm.settings.entity.User;

public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
