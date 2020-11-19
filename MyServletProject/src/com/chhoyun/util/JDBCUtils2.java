package com.chhoyun.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: huakaimay
 * @since: 2020-11-19
 */
public class JDBCUtils2 {

    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {

        try {
            InputStream resourceAsStream = JDBCUtils2.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    public void close(Connection con, PreparedStatement ps, ResultSet rs) {
        DbUtils.closeQuietly(con, ps, rs);
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        System.out.println(connection);
    }
}
