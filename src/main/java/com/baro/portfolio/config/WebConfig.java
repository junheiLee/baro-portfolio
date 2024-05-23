package com.baro.portfolio.config;

import com.baro.portfolio.web.argumentresolver.CurrentAccountArgumentResolver;
import com.baro.portfolio.web.interceptor.CheckAccountInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static String[] WHITE_LIST = {"/", "/users/sign-up",
            "/account/sign-in", "account/sign-out",
            "/css/**", "/error"};

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CurrentAccountArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CheckAccountInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(WHITE_LIST);
    }
}
