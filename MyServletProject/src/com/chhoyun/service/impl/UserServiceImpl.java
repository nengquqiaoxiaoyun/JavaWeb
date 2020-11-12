package com.chhoyun.service.impl;

import com.chhoyun.dao.UserDao;
import com.chhoyun.dao.impl.UserDaoImpl;
import com.chhoyun.junit.User;
import com.chhoyun.service.UserService;

import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User findUserByUsernameAndPassword(Connection connection, String username, String password) {
        return userDao.findUserByUsernameAndPassword(connection, username, password);
    }
}
