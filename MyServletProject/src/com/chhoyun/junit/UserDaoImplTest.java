package com.chhoyun.junit;

import com.chhoyun.dao.impl.UserDaoImpl;
import com.chhoyun.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public class UserDaoImplTest {

    @Test
    public void getUserById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = new UserDaoImpl().getUserById(connection, 1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null, null);
        }
    }

    @Test
    public void findUserByUsernameAndPassword() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = new UserDaoImpl().findUserByUsernameAndPassword(connection, "张三", "qwerty");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null, null);
        }
    }

    @Test
    public void testGetAllUser() {
        try {
            List<User> allUser = new UserDaoImpl().getAllUser();
            allUser.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}