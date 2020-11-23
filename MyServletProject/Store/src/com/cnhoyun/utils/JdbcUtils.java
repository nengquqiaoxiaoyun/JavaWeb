package com.cnhoyun.utils;

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
 * @since: 2020-11-23
 */
public class JdbcUtils {
    private static DataSource dataSource;

    static {
        try {
            InputStream resourceAsStream = JdbcUtils.class.getResourceAsStream("/druid.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        DbUtils.closeQuietly(con, ps, rs);
    }

}
