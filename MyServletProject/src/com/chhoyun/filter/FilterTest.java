package com.chhoyun.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-18
 */
public class FilterTest {

    public static void main(String[] args) {

        String msg = "heello, <script>, 996哈哈哈, :) ok";

        Message message = new Message();
        message.setMsg(msg);

        FilterChain1 filterChain1 = new FilterChain1();
        filterChain1.add(new FaceFilter());
        filterChain1.add(new SenstiveFilter());
        filterChain1.add(new HMLFilter());
        filterChain1.doFilter(message);

        System.out.println(message);


    }


}

class Message {
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter1 {

    boolean doFilter(Message msg);

}


class HMLFilter implements Filter1 {

    @Override
    public boolean doFilter(Message msg) {
        String msg1 = msg.getMsg();
        msg1 = msg1.replace("<", "[");
        msg1 = msg1.replace(">", "]");
        msg.setMsg(msg1);
        return true;
    }
}

class SenstiveFilter implements Filter1 {

    @Override
    public boolean doFilter(Message msg) {
        String msg1 = msg.getMsg();
        msg1 = msg1.replace("996", "965");
        msg.setMsg(msg1);
        return false;
    }
}

class FaceFilter implements Filter1 {

    @Override
    public boolean doFilter(Message msg) {
        String msg1 = msg.getMsg();
        msg1 = msg1.replace(":)", "^V");
        msg.setMsg(msg1);

        return true;
    }
}

class FilterChain1 implements Filter1{
    List<Filter1> list = new ArrayList<>();


    public FilterChain1 add(Filter1 filter1) {
        list.add(filter1);
        return this;
    }

    public boolean doFilter(Message msg) {
        for (Filter1 filter1 : list) {
            if (!filter1.doFilter(msg)) {
                return false;
            }
        }

        return true;
    }

}
