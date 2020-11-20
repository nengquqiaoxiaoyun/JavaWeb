package com.chhoyun.controller;

import com.alibaba.fastjson.JSON;
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
 * @since: 2020-11-20
 */
@WebServlet(urlPatterns = "/searchProductsServlet")
public class SearchProductsServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");

        String inputVal = request.getParameter("inputVal");
        List<String> products = productService.listProductsNameByInput(inputVal);

        String json = JSON.toJSONString(products);
        response.getWriter().write(json);
    }


}
