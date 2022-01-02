package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        // 로그 id를 넘겨야하는데 여기서만 생성됨
        // 필드에 넣어버리면 싱글톤으로 유지되기때문에 줫댐
        // request에 setAttribute를 사용하자
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID,uuid);

        //컨트롤러의 자세 내용 보고싶다면
        //@RquestMapping 사용하는 경우 :HandlerMethod 타입
        //정적 리소스 : ResourcesRequestHandler 타입
        if(handler instanceof HandlerMethod){// 타입 확인후 사용
            HandlerMethod hm = (HandlerMethod) handler;
            //hm.get~ 사용해서 모든걸 확인 가능

        }

        //로그 uuid, requestUri, 어떤 handler(즉 컨틀롤러) 가 사용됬는지도 확인 가능
        log.info("REQUEST [{}][{}][{}]",uuid,requestURI,handler);

        //return false -> 여기서 끝남
        //return true -> 컨트롤러까지 넘어감
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle [{}]",modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String)request.getAttribute(LOG_ID);

        log.info("AFTERCOMPLETION [{}][{}][{}]", logId, requestURI, handler);

        //여기서는 예외 터질시 예외 정보도 함께 넘겨줌
        if(ex != null){
            log.error("afterCompletion error!!",ex);
        }

    }
}
