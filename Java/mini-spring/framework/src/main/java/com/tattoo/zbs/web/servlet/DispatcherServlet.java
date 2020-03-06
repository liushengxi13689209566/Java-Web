package com.tattoo.zbs.web.servlet;

import javax.servlet.*;
import java.io.IOException;

public class DispatcherServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override //重点实现的方法
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("just a test");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
