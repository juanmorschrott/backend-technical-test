package com.tui.proof.ws.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityFilter extends GenericFilterBean {

    /**
     * In real life, a "check if valid token" would be necessary.
     * Getting a user token from a database or a third party service call.
     */
    private static final String mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6Ikp1YW4gTW9yZW5vIiwiaWF0IjoxNTE2MjM5MDIyfQ.kpW8f1gJjRU-ha1_eG-mDCDdCRcZWokR2sLoQqHS9Js";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("Authorization");

        if (!StringUtils.isEmpty(token) && token.equals(mockToken)) {
            filterChain.doFilter(request, servletResponse);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: missing or invalid token");
        }

    }
}
