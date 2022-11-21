package com.study.authentication.config;

import com.study.authentication.config.filter.LoginCheckFilterV1;
import com.study.authentication.config.filter.LoginCheckFilterV2;
import com.study.authentication.config.interceptor.LoginCheckInterceptorV1;
import com.study.authentication.config.interceptor.LoginCheckInterceptorV2;
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
    public FilterRegistrationBean<Filter> loginCheckFilterV1() {
        // 자체적 SessionManager 방식을 통한 인증 필터
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilterV1(sessionManager));
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
//    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilterV2() {
        // 스프링에서 제공하는 Session 방식을 통한 인증 필터
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilterV2());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    // interceptor setting
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //체인 형식으로 설정

        // 자체적 SessionManager 방식을 통한 인증 인터셉터
//        registry.addInterceptor(new LoginCheckInterceptorV1(sessionManager))
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns(
//                        "/",
//                        "/signUp",
//                        "/login",
//                        "/logout",
//                        "/css/*",
//                        "/error"
//                );
        registry.addInterceptor(new LoginCheckInterceptorV2())
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
