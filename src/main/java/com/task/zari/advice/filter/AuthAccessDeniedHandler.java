package com.task.zari.advice.filter;

import com.task.zari.dto.response.ResponseResult;
import com.task.zari.util.HttpUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpUtils.jsonErrorResponse(HttpServletResponse.SC_FORBIDDEN, ResponseResult.BAD_CREDENTIAL, response, accessDeniedException);
    }
}
