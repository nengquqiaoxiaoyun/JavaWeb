package com.cnhoyun.jdbc;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: huakaimay
 * @since: 2020-11-04
 */
public class JDBCConnectionTest {


    @Test
    public void testConnection1() {
        try {
            // 1.提供java.sql.Driver接口实现类的对象
            Driver driver = null;
            driver = new com.mysql.cj.jdbc.Driver();

            // 提供连接的参数
            String url = "jdbc:mysql://localhost:3306/test";
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "Zyt98218.");

            // connect需要的参数： url 和 properties
            Connection connect = driver.connect(url, properties);


            System.out.println(connect);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection2() {

        try {
            String className = "com.mysql.cj.jdbc.Driver";
            Class cls = Class.forName(className);
            Driver driver = (Driver) cls.newInstance();

            String url = "jdbc:mysql://localhost:3306/test";

            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "Zyt98218.");

            Connection connect = driver.connect(url, properties);
            System.out.println(connect);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testConnection3() {

        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String driverClass = "com.mysql.cj.jdbc.Driver";
            String user = "root";
            String password = "Zyt98218.";

            // 加载mysql driver 此时 Driver静态代码块执行，加载驱动
            Class cls = Class.forName(driverClass);
            Driver driver = (Driver) cls.newInstance();

            // 把驱动给DriverManager
            DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
        } catch (Exception e) {

        }

    }


    @Test
    public void testConnection4() {

        try {
            String url = "jdbc:mysql://localhost:3306/test";
            String user = "root";
            String password = "Zyt98218.";

            // 不需要手动加载驱动了 DriverManager在静态代码块已经加载了
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
        } catch (Exception e) {

        }

    }

    @Test
    public void testConnection5() {

        try {
            // 获取配置文件
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);


            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password =  properties.getProperty("password");

            // 不需要手动加载驱动了 DriverManager在静态代码块已经加载了
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);

        } catch (Exception e) {

        }

    }
    


}


