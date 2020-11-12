package com.chhoyun.service;

import com.chhoyun.entity.Customer;

import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-11
 */
public interface CustomerService {


    void register(Connection con, Customer customer);

    Customer findUserByUsernameAndPassword(Connection connection, String username, String password);
}
