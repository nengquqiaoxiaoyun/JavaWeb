package com.cnhoyun.servlet;

import com.cnhoyun.entity.Category;
import com.cnhoyun.entity.Product;
import com.cnhoyun.service.ProductService;
import com.cnhoyun.service.impl.ProductServiceImpl;
import com.cnhoyun.utils.JdbcUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
@WebServlet(name = "EditProductServlet", urlPatterns = "/editProduct")
public class EditProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        System.out.println("name: " + name);

        Map<String, String[]> parameterMap = request.getParameterMap();
        Product product = new Product();
        Category category = new Category();
        Connection con = null;

        try {
            con = JdbcUtils.getConnection();
            con.setAutoCommit(false);
            BeanUtils.populate(product, parameterMap);
            BeanUtils.populate(category, parameterMap);
            product.setCategory(category);

            System.out.println(product);
            System.out.println("category: " +category);

            productService.editProduct(con, product);
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
