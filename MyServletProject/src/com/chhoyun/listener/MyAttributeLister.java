package com.chhoyun.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author: huakaimay
 * @since: 2020-11-19
 */
public class MyAttributeLister implements ServletContextAttributeListener, ServletRequestAttributeListener, HttpSessionAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        System.out.println("ServletContextAttributeEvent add");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent scae) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent scae) {

    }

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequestAttributeListener add");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequestAttributeListener attributeRemoved");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("ServletRequestAttributeListener attributeReplaced");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("HttpSessionAttributeListener add");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

    }
}
