package com.cnhoyun.jdbc.dao;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-06
 */
public interface CustomerDao  {


    void updateCustomerById(Connection con, int id, Customer customer);

    void delteCustomerById(Connection con, int id);

    Customer getCustomerById(Connection con, int id);

    List<Customer> getAllCustomer(Connection con);

    void  insertCustomer(Connection con, Customer customer);


}
