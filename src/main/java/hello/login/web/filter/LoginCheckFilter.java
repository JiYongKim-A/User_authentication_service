package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    //로그인 안한 사용자가 들어올 수 있는 경로들 모아둠
    // 즉 로그인 없어도 가능한 경로
    private static final String[] whiteList = {"/","/members/add", "/login","/logout","/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //uri 사용할것임
        String requestURI = httpRequest.getRequestURI();

        //로직 작성

        try{
            log.info("인증 체크 필터 시작{}",requestURI);
    // 로그인이 필요 없는 경로인지 아닌지 체크

            // 로그인 필요 경로경우
        if(isLoginCheckPath(requestURI)){
            //white리스트가 아닌경우 여기 로직을 따름
            log.info("인증 체크 로직 실행 {}", requestURI);
            HttpSession session = httpRequest.getSession(false);
            if(session==null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){

                log.info("미인증 사용자 요청 {}", requestURI);
                //로그인으로 redirect
                //이전 사용자의 경로를 함께 보내 로그인완료시 다시 그페이지로 갈 수 있도록 경로도 함께 보냄
                httpResponse.sendRedirect("/login?redirectURL="+requestURI);
                return;
            }
        }

        //로그인 필요 없는 경로이면 필터로 넘어감
        chain.doFilter(request,response);

        }catch (Exception e){
            //예외 로깅 가능하지만 여기서 먹어버리면 정상동작 되니, 톰캣까지 예외를 보내주어야함
            throw e;
        }finally {
        log.info("인증 체크 필터 종료 {}",requestURI );
        }




    }

    /**
     * whiteList 의 경우 인증 체크를 X 메소드
     * => requestURI가 white리스트에 없을 경우 false 뜨도록 하는 메소드
     */
    //파라미터 경로가 list에 없을 경우 true 반환
    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whiteList, requestURI);
    }

}
