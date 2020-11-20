package com.chhoyun.service.impl;

import com.chhoyun.dao.CustomerDao;
import com.chhoyun.dao.impl.CustomerDaoImpl;
import com.chhoyun.entity.Customer;
import com.chhoyun.service.CustomerService;

import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-11
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl();


    @Override
    public void register(Connection con, Customer customer) {
        customerDao.register(con, customer);
    }

    @Override
    public Customer findUserByUsernameAndPassword(Connection connection, String username, String password) {

        return customerDao.findUserByUsernameAndPassword(connection, username, password);
    }

    @Override
    public Customer finUserByUsername(Connection connection, String username) {
        return customerDao.findUserByUsername(connection, username);
    }

}
