package com.chhoyun.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-18
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("encodingFilter 开始执行");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        // 乱码的处理
        // post乱码的处理
        String method = req.getMethod();

        System.out.println("进入编码过滤器");
        System.out.println("此处的方法为：" + method);

        if ("POST".equalsIgnoreCase(method)) {
            //直接设置请求体的解析方式为utf-8
            req.setCharacterEncoding("utf-8");
            //放行
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
        System.out.println("响应");

    }

    @Override
    public void destroy() {
        System.out.println("encodingFilter 销毁");
    }
}
