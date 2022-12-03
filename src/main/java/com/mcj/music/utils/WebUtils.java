package com.mcj.music.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mcj
 * @date 2022/11/19 20:20
 * @email 2037612492@qq.com
 * @description
 */
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   带渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
