package com.cnhoyun.servlet;

import com.cnhoyun.dao.ProductDao;
import com.cnhoyun.dao.impl.ProductDaoImpl;
import com.cnhoyun.entity.Category;
import com.cnhoyun.entity.Product;
import com.cnhoyun.utils.JdbcUtils;
import com.mysql.jdbc.Driver;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
@WebServlet(name = "AddProductServlet", urlPatterns = "/addProduct")
public class AddProductServlet extends HttpServlet {

    private ProductDao productDao = new ProductDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Product product = new Product();
        Category category = new Category();
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            BeanUtils.populate(product, parameterMap);
            BeanUtils.populate(category, parameterMap);
            product.setCategory(category);
            product.setPid(UUID.randomUUID().toString());

            productDao.addProduct(connection, product);
            connection.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JdbcUtils.close(connection, null, null);
        }

        response.sendRedirect("productList");

    }
}
