package com.chhoyun.controller;

import com.chhoyun.entity.Customer;
import com.chhoyun.service.CustomerService;
import com.chhoyun.service.impl.CustomerServiceImpl;
import com.chhoyun.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author: huakaimay
 * @since: 2020-11-11
 */
public class LoginServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checked = request.getParameter("checked");
        System.out.println("checked: " + checked);

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = customerService.findUserByUsernameAndPassword(connection, username, password);
            if (customer != null) {

                System.out.println("username: " + customer.getName());
                if (checked != null) {
                    Cookie cookie = new Cookie("username", customer.getUsername());
                    cookie.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(cookie);
                } else {
                    Cookie cookie = new Cookie("username", customer.getUsername());
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }

                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("msg", "用户名不存在或密码错误");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null, null);
        }

    }
}
