package com.task.zari.auth;


import com.task.zari.entity.account.AccountType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authentication";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        PrincipalDetails principal = resolveInfo(request);
        if(principal!=null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal, "1", principal.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

    private PrincipalDetails resolveInfo(ServletRequest request) {
        String header = ((HttpServletRequest)request).getHeader(AUTHORIZATION_HEADER);
        if(!StringUtils.hasText(header)) return null;
        String[] split = header.split(" ");
        if(split.length != 2) return null;
        return new PrincipalDetails(split[1], AccountType.valueOf(split[0]));
    }
}
