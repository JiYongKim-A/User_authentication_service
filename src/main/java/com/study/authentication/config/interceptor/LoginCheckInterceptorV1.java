package com.study.authentication.config.interceptor;

import com.study.authentication.config.session.SessionManager;
import com.study.authentication.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginCheckInterceptorV1 implements HandlerInterceptor {
    private final SessionManager sessionManager;

    public LoginCheckInterceptorV1(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 접속하려는 URI
        String requestURI = request.getRequestURI();
        log.info("[로그인 인증 체크 인터셉터 가동] : 접속 URI = {}", requestURI);
        Member member = (Member)sessionManager.getSession(request);
        if(member == null){
            log.info("[미인증 사자 요청] : 접속 URI = {}", requestURI);
            response.sendRedirect("/login?redirectURL"+requestURI);
            log.info("[로그인 인증 체크 인터셉터 종료] : 접속 URI = {}", requestURI);
            return false;
        }
        return true;
    }
}
