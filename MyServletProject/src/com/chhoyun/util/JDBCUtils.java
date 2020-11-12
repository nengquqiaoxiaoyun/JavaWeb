package com.chhoyun.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author: huakaimay
 * @since: 2020-11-09
 */
public class JDBCUtils {


    private static DataSource druidDataSource;

    static {
        try {
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            druidDataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws Exception {

        Connection connection = druidDataSource.getConnection();
        return connection;
    }

    public static DataSource getDataSource() {
        return druidDataSource;
    }


    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        DbUtils.closeQuietly(con, ps, rs);
    }

}
