package com.lxb.security.interceptor;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
@Component("myAccessDeniedHandlerImpl")
public class MyAccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");

        response.getWriter().println("{\"code\":403,\"message\":\"你没有权限访问呀！\",\"data\":\"\"}");

        response.getWriter().flush();
    }
}
