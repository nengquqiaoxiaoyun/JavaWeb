package com.cnhoyun.jdbc;

import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author: huakaimay
 * @since: 2020-11-04
 */
public class CRUDTest {


    @Test
    public void testGeneric() {
        String sql = "select id, name, email from customers where id = ?";
        User user = genericSelect(User.class, sql, 2);
        System.out.println(user);

        sql = "select order_id orderId, order_name orderName from `order` where order_id = ?";
        Order order = genericSelect(Order.class, sql, 2);
        System.out.println(order);

        String sql1 = "select id, name, email from customers where id < ?";

        List<User> users = genericSelectList(User.class, sql1, 22);
        users.forEach(System.out::println);


    }

    public <T> List<T> genericSelectList(Class<T> cls, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(1 + i, args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<T> list = new ArrayList<>();
            T t;
            while (rs.next()) {
                t = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(1 + i);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = t.getClass().getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }
                list.add(t);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, rs);
        }
        return null;
    }


    public <T> T genericSelect(Class<T> cls, String sql, Object... args) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
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
            JDBCUtil.closed(connection, ps, rs);
        }


        return null;
    }


    @Test
    public void testOrder() {
        String sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id = ?";
        Order order = orderTest(sql, 1);
        System.out.println(order);
    }


    /**
     * 别名映射到实体类
     *
     * @param sql
     * @param args
     * @return
     */
    public Order orderTest(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(1 + i, args[i]);
            }

            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    Object columnVal = resultSet.getObject(1 + i);
                    String columnName = metaData.getColumnLabel(i + 1);

                    Field declaredField = Order.class.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(order, columnVal);
                }
                return order;
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, resultSet);
        }

        return null;
    }


    @Test
    public void testSelect() {
        String sql = "select name, email, birth from customers where id = ?";
        User user = getUser(sql, 2);
        System.out.println(user);
    }

    public User select(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }


            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            if (resultSet.next()) {
                user = new User();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnName = metaData.getColumnName(i + 1);

                    Field declaredField = user.getClass().getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(user, columnValue);
                }

                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, resultSet);
        }

        return user;

    }


    public User getUser(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getDruidConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            User user = null;
            if (resultSet.next()) {
                user = new User();
                // 把数据库查找到的数据给实体类
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(1 + i);
                    String columnName = metaData.getColumnName(i + 1);

                    // 给实体类字段设置值
                    Field declaredField = user.getClass().getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(user, columnValue);
                }
                return user;

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, resultSet);
        }


        return null;
    }


    @Test
    public void select() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "select id, name, email, birth from customers where id = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, 18);
            resultSet = ps.executeQuery();

            User user;
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                user = new User(id, name, email, date);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, resultSet);
        }
    }


    @Test
    public void delete() {
        String sql = "delete from customers where id = ?";
        Object[] args = {1};
        update(sql, args);
    }


    public void update(String sql, Object... args) {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps);
        }

    }

    @Test
    public void update() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update customers set name = ? where id = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, "王五");
            ps.setInt(2, 18);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps);
        }
    }

    @Test
    public void insert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // read properties and load
            InputStream is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(is);

            // connect with properties
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);


            String sql = "insert into customers(name, email, birth) values(?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "张三");
            preparedStatement.setString(2, "xx@123.com");
            preparedStatement.setObject(3, LocalDate.now());

            int i = preparedStatement.executeUpdate();

            System.out.println(i);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException throwables) {
            }
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException throwables) {
            }
        }


    }
}
