package com.task.zari.advice.filter;

import com.task.zari.dto.response.ResponseResult;
import com.task.zari.util.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (UsernameNotFoundException e) {
            HttpUtils.jsonErrorResponse(HttpServletResponse.SC_FORBIDDEN, ResponseResult.USERNAME_NOT_FOUND, response);
        }
    }
}
