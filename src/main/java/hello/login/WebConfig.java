package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

@Configuration
public class WebConfig implements WebMvcConfigurer {
// 인터셉터는 요런 형식으로 지원 외우는게 아니라 사용법을 익히자
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //체인 형식으로 설정
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**") // 서블릿이랑 패턴이 다름
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 여기서는 interceptor 사용 안함 설절 경로

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**") //모든 경로에 대해 인터셉트적용
                .excludePathPatterns( // 단 밑의 경로경우는 빼줌
                        "/",
                        "/members/add",
                        "/login",
                        "logout",
                        "/css/**",
                        "/*.ico",
                        "/error");
    }

//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
