package com.cnhoyun.servlet;

import com.cnhoyun.service.ProductService;
import com.cnhoyun.service.impl.ProductServiceImpl;
import com.cnhoyun.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = "/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");

        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            con.setAutoCommit(false);
            productService.deleteProductByPid(con, pid);

            con.commit();
        } catch (Exception throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JdbcUtils.close(con, null, null);
        }
        response.sendRedirect("productList");
    }
}
