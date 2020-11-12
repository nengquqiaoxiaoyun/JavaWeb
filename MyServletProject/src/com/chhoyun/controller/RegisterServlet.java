package com.chhoyun.controller;

import com.chhoyun.entity.Customer;
import com.chhoyun.service.CustomerService;
import com.chhoyun.service.impl.CustomerServiceImpl;
import com.chhoyun.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author: huakaimay
 * @since: 2020-11-11
 */
public class RegisterServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            // 开启事务
            connection.setAutoCommit(false);

            String id = UUID.randomUUID().toString();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String sex = request.getParameter("sex");
            String birth = request.getParameter("birth");

            Customer customer = new Customer();
            customer.setid(id);
            customer.setUsername(username);
            customer.setPassword(password);
            customer.setName(name);
            customer.setEmail(email);
            customer.setSex(sex);
            customer.setBirthday(birth);

            customerService.register(connection, customer);

            response.sendRedirect(request.getContextPath() + "/login.jsp");


            // 没有异常提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 发生异常回滚
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                // 恢复自动提交
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtils.close(connection, null, null);
        }

//        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
