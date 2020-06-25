package com.luobee.home.platform.service;

import com.luobee.home.platform.dao.UserDao;
import com.luobee.home.platform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 通过用户名判断用户是否存在
     *
     * @param username 用户名
     * @return
     */
    public boolean isExist(String username) {
        User user = userDao.getByUsername(username);
        return null != user;
    }

    /**
     * 通过用户名获取用户
     *
     * @param username 用户名
     * @return
     */
    public User getByUsername(String username) {
        User user = userDao.getByUsername(username);
        return user;
    }

    /**
     * 通过用户名密码获取用户，用于登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User getByUsernameAndPassword(String username, String password) {
        return userDao.getByUsernameAndPassword(username, password);
    }

    /**
     * 用户添加（注册）
     *
     * @param user 用户对象
     */
    public void add(User user) {
        userDao.save(user);
    }
}
