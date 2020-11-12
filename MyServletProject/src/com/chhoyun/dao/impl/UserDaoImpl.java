package com.chhoyun.dao.impl;

import com.chhoyun.dao.BaseDao;
import com.chhoyun.dao.UserDao;
import com.chhoyun.junit.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    public User getUserById(Connection connection, int id) {
        String sql = "select id, name, password, address, phone from user where id = ?";
        return super.getBean(connection, sql, id);

    }

    @Override
    public User findUserByUsernameAndPassword(Connection connection, String username, String password) {
        String sql = "select id, name, password, address, phone from user where name = ? and password = ?";
        return super.getBean(connection, sql, username, password);
    }

    @Override
    public List<User> getAllUser() {
        String sql = "select id, name, password, address, phone from user";
        return super.getBeanList(sql);
    }
}
