package com.chhoyun;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-09
 */
public class ServletTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("dopost");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doget");


        String username = getServletConfig().getInitParameter("username");
        System.out.println("servletTest的username： " + username);

        ServletContext servletContext = getServletConfig().getServletContext();
        String context = servletContext.getInitParameter("context");
        String contextPath1 = servletContext.getContextPath();
        System.out.println("servletTest的context: " + context);

        String contextPath = servletContext.getRealPath("/");
        System.out.println("servletTest的contextPath: " + contextPath);


    }
}
