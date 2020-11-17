package com.chhoyun.controller;

import com.chhoyun.entity.Product;
import com.chhoyun.service.ProductService;
import com.chhoyun.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-17
 */
@WebServlet("/productServlet")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> products = productService.listProducts();

        if (products != null) {
            request.setAttribute("products", products);
        }

        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }
}
