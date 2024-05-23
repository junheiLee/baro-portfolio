package com.baro.portfolio.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

import static com.baro.portfolio.constant.ModelConst.ACCOUNT;

@Slf4j
public class CheckAccountInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        String requestUrl = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(ACCOUNT) == null) {
            response.sendRedirect("/account/sign-in?redirectUrl=" + requestUrl);
            return false;
        }
        return true;
    }
}
