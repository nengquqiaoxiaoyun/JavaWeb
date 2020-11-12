package com.chhoyun.service;

import com.chhoyun.junit.User;

import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public interface UserService {

    User findUserByUsernameAndPassword(Connection connection, String username, String password);
}
