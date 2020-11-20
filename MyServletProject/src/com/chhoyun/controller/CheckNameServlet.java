package com.chhoyun.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.util.JdbcUtils;
import com.chhoyun.entity.Customer;
import com.chhoyun.service.CustomerService;
import com.chhoyun.service.impl.CustomerServiceImpl;
import com.chhoyun.util.JDBCUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huakaimay
 * @since: 2020-11-20
 */
@WebServlet(urlPatterns = "/checkNameServlet")
public class CheckNameServlet extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;

        try {
            response.setContentType("text/html; charset=UTF-8");
            String usernmae = request.getParameter("username");
            connection = JDBCUtils.getConnection();
            Customer customer = customerService.finUserByUsername(connection, usernmae);
            Map<String, String> map = new HashMap<>();
            if (customer != null) {
                map.put("isExist", "yes");
            } else {
                map.put("isExist", "no");
            }

            String json = JSONUtils.toJSONString(map);

            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(connection, null, null);
        }

    }
}
