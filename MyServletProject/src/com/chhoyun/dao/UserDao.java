package com.chhoyun.dao;

import com.chhoyun.junit.User;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public interface UserDao {


    User getUserById(Connection con, int id);


    User findUserByUsernameAndPassword(Connection connection, String username, String password);


    List<User> getAllUser();
}
