package com.huarun.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
