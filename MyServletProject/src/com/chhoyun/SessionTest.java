package com.chhoyun;

import org.omg.IOP.CodecOperations;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-16
 */
@WebServlet(name = "SessionTest", urlPatterns = "/sessionTest")
public class SessionTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("session: " + session);

        String id = session.getId();
        session.setAttribute("uname", "zhangsan");

//        Cookie cookie = new Cookie("JSESSIONID", id);
//        cookie.setMaxAge(60 * 10);
//        response.addCookie(cookie);

        System.out.println("id：" + id);
//        request.getRequestDispatcher("sessionDemo/session.jsp").forward(request, response);
        // 重定向路径测试
        System.out.println("虚拟路径： " + getServletContext().getContextPath());
        String realPath = getServletContext().getRealPath(getServletContext().getContextPath());
        System.out.println("实际路径： " + realPath);
//        response.sendRedirect(getServletContext().getContextPath() + "/sessionDemo/session.jsp");
//        response.sendRedirect("sessionDemo/session.jsp");
        response.sendRedirect("/servletProject/sessionDemo/session.jsp");

    }
}
