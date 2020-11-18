package com.chhoyun.filter;

import javax.servlet.*;
import javax.servlet.Filter;
import java.io.IOException;

/**
 * @author: huakaimay
 * @since: 2020-11-18
 */
public class FilterImpl implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter 初始化了");
        String filterName = filterConfig.getFilterName();
        System.out.println("filter的名字是： " + filterName);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, javax.servlet.FilterChain chain) throws IOException, ServletException {
        System.out.println("进入doFilter方法");

        System.out.println("请求");
        // 放行
        chain.doFilter(request, response);
        System.out.println("响应");
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }
}
