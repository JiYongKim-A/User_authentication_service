package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");
        log.info("");

        // ServletRequest는 HttpRequest의 부모라서 기능이 별로없음
        // HttpRequest로 downCasting 해주자
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 모든 사용자의 uri남김
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        // 사용자별 구분을 위해 uuid 사용용
       String uuid = UUID.randomUUID().toString();

       try{
           log.info("Request = [{}][{}]",uuid,requestURI);
        // 필수 해야하는 것
           // 다음 필터가 있으면 넘겨줘야함
           chain.doFilter(request,response);
       } catch (Exception e){
            throw e;
       }finally {
           log.info("RESPONSE[{}][{}]",uuid,requestURI);
       }



    }

    @Override
    public void destroy() {
        log.info("log filter destroy");

    }
}
