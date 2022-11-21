package com.study.authentication.config.interceptor;

import com.study.authentication.config.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptorV2 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("[로그인 인증 체크 인터셉터 가동] : 접속 URI = {}", requestURI);

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIM_MEMBER) == null) {
            log.info("[미인증 사자 요청] : 접속 URI = {}", requestURI);
            response.sendRedirect("/login?redirectURL" + requestURI);
            return false;
        }
        return true;
    }
}
