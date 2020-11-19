package com.chhoyun.listener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-19
 */
@WebServlet(urlPatterns = "/linstenerServlet")
public class LinstenerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.getWriter().write(session.getId());

        
        request.setAttribute("unamez", null);
        request.getAttribute("unamez");
    }
}