package com.mcj.music.handler;

import com.alibaba.fastjson.JSON;
import com.mcj.music.utils.ResponseUtils;
import com.mcj.music.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mcj
 * @date 2022/11/20 14:06
 * @email 2037612492@qq.com
 * @description 用户认证失败处理类
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils result = new ResponseUtils(HttpStatus.UNAUTHORIZED.value(), "用户认证失败，请重新登陆");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
