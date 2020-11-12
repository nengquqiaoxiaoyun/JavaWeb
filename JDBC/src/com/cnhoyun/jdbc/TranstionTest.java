package com.cnhoyun.jdbc;

import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: huakaimay
 * @since: 2020-11-05
 */
public class TranstionTest {

    @Test
    public void transfer() {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            String sql = "update user_table set balance = balance - 100 where user = ?";
            update(connection, sql, "AA");

            int i = 1 / 0;

            sql = "update user_table set balance = balance + 100 where user = ?";
            update(connection, sql, "BB");
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtil.closed(connection,null);
        }



    }


    public void update(Connection con, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[0]);
            }
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBCUtil.closed(null, ps);
        }

    }


    public void update(String sql, Object... args) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[0]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(con, ps);
        }
    }

}
