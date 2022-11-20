package com.study.authentication.config;

import com.study.authentication.config.filter.LoginCheckFilter;
import com.study.authentication.config.interceptor.LoginCheckInterceptor;
import com.study.authentication.config.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SessionManager sessionManager;

    // filter setting
//    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter(sessionManager));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    // interceptor setting
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //체인 형식으로 설정
        registry.addInterceptor(new LoginCheckInterceptor(sessionManager))
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/",
                        "/signUp",
                        "/login",
                        "/logout",
                        "/css/*",
                        "/error"
                );
    }
}
