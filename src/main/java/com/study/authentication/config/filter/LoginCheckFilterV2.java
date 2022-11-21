package com.study.authentication.config.filter;

import com.study.authentication.config.session.SessionConst;
import com.study.authentication.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilterV2 implements Filter {
    // 미제한 경로 지정
    private static final String[] whiteList = {"/", "/signUp", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 접속하려는 URI
        String requestURI = httpRequest.getRequestURI();

        try {
            log.info("[로그인 인증 체크 필터 가동] : 접속 URI = {}", requestURI);
            // 로그인이 필요한 URI 접속시

            if (isLoginCheckPath(requestURI)) {
                // filter logic
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIM_MEMBER) == null) {
                    log.info("[미인증 사용자 요청] : 접속 URI = {}", requestURI);
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return;
                }
            }

            // 다음 필터로 넘겨주기
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("[로그인 인증 체크 필터 종료] : 접속 URI = {}", requestURI);
        }


    }

    private boolean isLoginCheckPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }
}
