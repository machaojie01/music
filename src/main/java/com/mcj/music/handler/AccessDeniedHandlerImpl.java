package com.mcj.music.handler;

import com.alibaba.fastjson.JSON;
import com.mcj.music.utils.ResponseUtils;
import com.mcj.music.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mcj
 * @date 2022/11/20 14:06
 * @email 2037612492@qq.com
 * @description 用户授权失败处理类
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtils result = new ResponseUtils(HttpStatus.FORBIDDEN.value(), "你的权限不足,请联系管理员");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
