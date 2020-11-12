package com.cnhoyun.jdbc.dao;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-06
 */
public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {
    @Override
    public void updateCustomerById(Connection con, int id, Customer customer) {
        String sql = "update customers set name = ? where id = ? ";
        super.update(con, sql, customer.getName(), id);
    }

    @Override
    public void delteCustomerById(Connection con, int id) {
        String sql = "delete from customers where id = ?";
        super.update(con, sql, id);
    }

    @Override
    public Customer getCustomerById(Connection con, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        return getObjectById(con, sql, id);
    }

    @Override
    public List<Customer> getAllCustomer(Connection con) {
        String sql = "select name, email from customers";
        return getObjectList(con, sql);
    }

    @Override
    public void  insertCustomer(Connection con, Customer customer) {
        String sql = "insert into customers(name, email, birth) value (?, ?, ?)";
        super.update(con, sql, customer.getName(), customer.getEmail(), customer.getDate());
    }
}
