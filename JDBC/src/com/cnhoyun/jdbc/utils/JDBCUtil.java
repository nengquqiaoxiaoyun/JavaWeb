package com.cnhoyun.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author: huakaimay
 * @since: 2020-11-04
 */
public class JDBCUtil {

    public static Connection getConnection() throws Exception {
        InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }

    public static void closed(Connection con, Statement statement) {

        DbUtils.closeQuietly(con);
        DbUtils.closeQuietly(statement);

    }

    public static void closed(Connection con, Statement statement, ResultSet set) {
        closed(con, statement);
        DbUtils.closeQuietly(set);
    }

    private static DataSource dataSource = null;

    static {
        try {
            InputStream inputStream = ClassLoader.getSystemResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getDruidConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public static void main(String[] args) throws Exception {
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("druid.properties");
        Properties pro = new Properties();
        pro.load(inputStream);
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
