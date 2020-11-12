package com.cnhoyun.jdbc;

import com.cnhoyun.jdbc.dao.Customer;
import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author: huakaimay
 * @since: 2020-11-06
 */
public class QueryRunnerTest {

    @Test
    public void updateTest() {
        Connection druidConnection = null;
        try {
            druidConnection = JDBCUtil.getDruidConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "update customers set name = ? where id = ?";
            queryRunner.update(druidConnection, sql, "黑子", 10);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.closeQuietly(druidConnection);
        }

    }


    @Test
    public void testSelect() {
        Connection druidConnection = null;
        try {
            druidConnection = JDBCUtil.getDruidConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select id, name, email from customers where id = ?";
            BeanHandler<Customer> customerBeanHandler = new BeanHandler<Customer>(Customer.class);

            Customer customer = queryRunner.query(druidConnection, sql, customerBeanHandler, 10);
            System.out.println(customer);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.closeQuietly(druidConnection);
        }
    }

    @Test
    public void testSelectList() {
        Connection druidConnection = null;
        try {
            druidConnection = JDBCUtil.getDruidConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select id, name, email from customers where id < ?";
            BeanListHandler<Customer> customerBeanHandler = new BeanListHandler<Customer>(Customer.class);

            List<Customer> customer = queryRunner.query(druidConnection, sql, customerBeanHandler, 10);
            customer.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.closeQuietly(druidConnection);
        }
    }

    @Test
    public void testSelectMapList() {
        Connection druidConnection = null;
        try {
            druidConnection = JDBCUtil.getDruidConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select id, name, email from customers where id < ?";
            MapListHandler customerBeanHandler = new MapListHandler();

            List<Map<String, Object>> query = queryRunner.query(druidConnection, sql, customerBeanHandler, 10);
            query.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.closeQuietly(druidConnection);
        }
    }

    @Test
    public void testSelectGeneric() {
        Connection druidConnection = null;
        try {
            druidConnection = JDBCUtil.getDruidConnection();
            QueryRunner queryRunner = new QueryRunner();
            String sql = "select count(*) from customers";
            ScalarHandler scalarHandler = new ScalarHandler();

            long count = (long) queryRunner.query(druidConnection, sql, scalarHandler);
            System.out.println(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtils.closeQuietly(druidConnection);
        }
    }
}
