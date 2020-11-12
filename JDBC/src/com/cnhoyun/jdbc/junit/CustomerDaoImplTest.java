package com.cnhoyun.jdbc.junit;

import com.cnhoyun.jdbc.dao.Customer;
import com.cnhoyun.jdbc.dao.CustomerDaoImpl;
import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-06
 */
public class CustomerDaoImplTest {


    private CustomerDaoImpl cus = new CustomerDaoImpl();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testUpdateCustomerById() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Customer customer = new Customer();
            customer.setName("王五哈");
            customer.setEmail("wangwu@111.cn");

            cus.updateCustomerById(connection, 5, customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, null);
        }
    }

    @Test
    public void testDelteCustomerById() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();

            cus.delteCustomerById(connection, 7);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, null);
        }
    }

    @Test
    public void testGetCustomerById() {

        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();

            Customer customer = cus.getCustomerById(connection, 18);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, null, null);
        }
    }

    @Test
    public void testGetAllCustomer() {

        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();

            List<Customer> allCustomer = cus.getAllCustomer(connection);
            allCustomer.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, null, null);
        }

    }

    @Test
    public void testInsertCustomer() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            Customer customer = new Customer();
            customer.setName("花花");
            customer.setEmail("hua@165.cn");
            customer.setDate(new Date());

            cus.insertCustomer(connection, customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, null);
        }
    }
}