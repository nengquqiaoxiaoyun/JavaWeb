package com.cnhoyun.servlet;

import com.cnhoyun.entity.Category;
import com.cnhoyun.entity.Product;
import com.cnhoyun.service.CategoryService;
import com.cnhoyun.service.ProductService;
import com.cnhoyun.service.impl.CategoryServiceImpl;
import com.cnhoyun.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
@WebServlet(name = "GetProductByIdServlet", urlPatterns = "/getProduct")
public class GetProductByIdServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        Product product = productService.getProductByPid(pid);

        request.setAttribute("product", product);

        List<Category> categories = categoryService.listCategory();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("admin/product/edit.jsp").forward(request, response);
    }
}
