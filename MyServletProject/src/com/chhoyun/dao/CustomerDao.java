package com.chhoyun.dao;

import com.chhoyun.entity.Customer;

import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-11
 */
public interface CustomerDao {

    void register(Connection con, Customer customer);


    Customer findUserByUsernameAndPassword(Connection con, String username, String password);

    Customer findUserByUsername(Connection con, String username);
}
