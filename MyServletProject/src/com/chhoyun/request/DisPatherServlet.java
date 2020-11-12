package com.chhoyun.request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public class DisPatherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("当前是DispatcherServlet, 下面开始转发到DemoServlet");

        // 使用request域对象的功能     request的生命周期只是一次请求，一次响应。
        // request的域功能只有在转发的时候使用才是有意义的。
        request.setAttribute("username", "zhangsan");
        response.setContentType("text/html;charset=utf-8");
        // 请求的转发
        // 获取请求的转发器
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/demoServlet");
        response.getWriter().write("这是转发前的servlet给出的响应");
        // 2.执行转发  forward后面的servlet会将前面的响应的内容覆盖
         requestDispatcher.forward(request, response);
        // include会将2个servlet响应的内容合并
//        requestDispatcher.include(request, response);
    }
}
