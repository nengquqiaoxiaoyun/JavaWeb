package com.cnhoyun.jdbc;

import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author: huakaimay
 * @since: 2020-11-06
 */
public class TranstionTest2 {

    @Test
    public void test() {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();

            // 关闭自动提交
            conn.setAutoCommit(false);

            String sql = "update user_table set balance = balance - 100 where user = ?";
            update(conn, sql, "AA");

            int i = 1 / 0;

            sql = "update user_table set balance = balance + 100 where user = ?";
            update(conn, sql, "BB");

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JDBCUtil.closed(conn,null);
        }
    }


    public void update(Connection con, String sql, Object ...args) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closed(null, ps);
        }


    }



    @Test
    public void test2() throws Exception {
        Connection connection = JDBCUtil.getConnection();

        int transactionIsolation = connection.getTransactionIsolation();
        System.out.println(transactionIsolation);

        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        System.out.println(connection.getTransactionIsolation());


        String sql = "select id, name from customers where id = ?";
        User user = genericSelect(connection, User.class, sql, 2);
        System.out.println(user);


    }


    @Test
    public void test3() throws Exception {

        Connection connection = JDBCUtil.getConnection();
        connection.setAutoCommit(false);
        System.out.println(connection.getTransactionIsolation());
        String sql = "update customers set  name = ? where id = ?";
        update(connection, sql, "小四", 2);

        Thread.sleep(10000);
        System.out.println("执行成功");
    }




    public <T> T genericSelect(Connection connection, Class<T> cls, String sql, Object... args) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(1 + i, args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            T t;
            if (rs.next()) {
                t = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(1 + i);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = t.getClass().getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }

                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(null, ps, rs);
        }


        return null;
    }
}
