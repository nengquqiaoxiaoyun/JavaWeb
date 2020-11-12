package com.chhoyun.controller;

import com.chhoyun.junit.User;
import com.chhoyun.service.UserService;
import com.chhoyun.service.impl.UserServiceImpl;
import com.chhoyun.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");

//        username = new String(username.getBytes("gbk"), "UTF-8");
        String password = request.getParameter("password");

        System.out.println(username);

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            User user = userService.findUserByUsernameAndPassword(connection, username, password);
            if (user != null) {
                response.getWriter().write("success");
            } else {
                response.getWriter().write("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null, null);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
