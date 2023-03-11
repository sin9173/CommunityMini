package com.task.zari.advice.filter;

import com.task.zari.dto.response.ResponseResult;
import com.task.zari.util.HttpUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Class eClass = authException.getClass();
        if (eClass.equals(BadCredentialsException.class)) {
            HttpUtils.jsonErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ResponseResult.BAD_CREDENTIAL, response);
        } else if(eClass.equals(InsufficientAuthenticationException.class)) {
            HttpUtils.jsonErrorResponse(HttpServletResponse.SC_FORBIDDEN, ResponseResult.FORBIDDEN, response);
        } else {
            HttpUtils.jsonErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, ResponseResult.NOT_AUTHENTICATION, response);
        }
    }
}
