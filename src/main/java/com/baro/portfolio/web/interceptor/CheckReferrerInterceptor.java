package com.baro.portfolio.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class CheckReferrerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String referer = request.getHeader("Referer");
        String host = request.getHeader("host");

        if (referer == null || !referer.contains(host)) {

            log.info("referrer not contains host");
            signOutStranger(request);
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    private static void signOutStranger(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }
}
