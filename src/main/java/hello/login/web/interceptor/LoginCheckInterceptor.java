package hello.login.web.interceptor;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
    //인증 체크는 preHandle만 있으면 됨

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 여기서 whiteList가 필요 없음
        // 인터셉터 등록시에 경로 지정해줄검

        String requestURI = request.getRequestURI();
        log.info("인증 확인 인터셉터 확인: {}",requestURI);

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER)==null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/login?redirectURL"+requestURI);
            return false; // 여기서 끝냄
        }

        return true;
    }
}
