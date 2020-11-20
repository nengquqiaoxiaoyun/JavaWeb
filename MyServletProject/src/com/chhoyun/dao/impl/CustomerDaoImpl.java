package com.chhoyun.dao.impl;

import com.chhoyun.dao.BaseDao;
import com.chhoyun.dao.CustomerDao;
import com.chhoyun.entity.Customer;

import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-11
 */
public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {

    @Override
    public void register(Connection con, Customer customer) {
        String sql = "insert into user(uid, username, password, name, email, birthday, sex) values(?, ?, ?, ?, ?, ?, ?)";
        super.update(con, sql, customer.getid(), customer.getUsername(), customer.getPassword(),
                customer.getName(), customer.getEmail(), customer.getBirthday(), customer.getSex());
    }

    @Override
    public Customer findUserByUsernameAndPassword(Connection con, String username, String password) {
        String sql = "select uid, username, password, email, name, birthday, sex " +
                "from user where username = ? and password = ?";
        return super.getBean(con, sql, username, password);
    }

    @Override
    public Customer findUserByUsername(Connection con, String username) {
        String sql = "select uid, username, password, email, name, birthday, sex " +
                "from user where username = ?";
        return super.getBean(con, sql, username);
    }
}
