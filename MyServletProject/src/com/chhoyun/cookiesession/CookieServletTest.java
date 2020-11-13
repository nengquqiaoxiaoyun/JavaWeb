package com.chhoyun.cookiesession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-12
 */
@WebServlet(name = "CookieServletTest", urlPatterns = "/cookieServlet")
public class CookieServletTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        Cookie cookie = new Cookie("name", "张三");
        Cookie cookie1 = new Cookie("name1", "lisi");

        response.addCookie(cookie);
        response.addCookie(cookie1);

    }
}
