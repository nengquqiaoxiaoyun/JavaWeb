package com.chhoyun.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

public class EncodeRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public EncodeRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        // 获取原有方法的返回值
        String param = request.getParameter(name);
        // 再进行反编译。
        if (param != null) {
            try {
                param = new String(param.getBytes("iso-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return param;
    }

}


