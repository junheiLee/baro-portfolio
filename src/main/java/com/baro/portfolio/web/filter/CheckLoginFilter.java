package com.baro.portfolio.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class CheckLoginFilter implements Filter {

    private static final String[] whiteList = {"/", "/account/sign-in", "/account/sign-out", "/users/sign-up", "/css/*"};


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        try {

            if (needLogin(requestURI)) {
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute("account") == null) {
                    httpResponse.sendRedirect("/account/sign-in?redirectUrl=" + requestURI);
                    return;
                }
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (Exception ignored) {
        }
    }

    private static boolean needLogin(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
