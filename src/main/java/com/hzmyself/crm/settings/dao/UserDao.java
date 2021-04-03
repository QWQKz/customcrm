package com.hzmyself.crm.settings.dao;

import com.hzmyself.crm.settings.entity.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String, String> map);
}
