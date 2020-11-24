package com.cnhoyun.servlet;

import com.cnhoyun.dto.PageBean;
import com.cnhoyun.entity.Product;
import com.cnhoyun.service.ProductService;
import com.cnhoyun.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
@WebServlet(name = "PageServlet", urlPatterns = "/page")
public class PageServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          int currentPage = 0;

        String page = request.getParameter("currentPage");
        if(page != null) {
            currentPage = Integer.parseInt(page);
        }

        PageBean<Product> pageBean = productService.findPage(currentPage);

        request.setAttribute("pageBean", pageBean);

        request.getRequestDispatcher("product_list.jsp").forward(request, response);
                                                                 
        


    }
}
