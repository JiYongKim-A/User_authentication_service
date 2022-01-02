package hello.login.web.session;


import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

// 세션 관리
@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "mySessionId";

    // 동시 여러쓰레드 접근시를 위해 ConcurrentHashMap 사용
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();


    /**
     * 세션 생성
     * ⇒ SessionID생성 (임의의 추정 불가능 랜덤 값)
     *
     * ⇒ Session 저장소에 sessionId와 보관할 값 저장
     *
     * ⇒SessionID로 응답 쿠키를 생성해서 클라에게 전달
     */

    // 객체를 넣어주면 세션값을 넣어주는것
    public void createSession(Object value, HttpServletResponse response){

//    ⇒ SessionID생성 , 값을 세션에 저장
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        // 쿠키 생성, 응답에 쿠키 넣어주기
        Cookie mySessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회
     * 클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 값 조회
     */

    public Object getSession(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);

        if(sessionCookie == null){
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());

    }

    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return  null;
        }
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);

    }


    /**
     * 세션 만료
     * =>  클라가 요청한 SessionID쿠키의 값으로, 세션 저장소에 보관한 sessionId와 값을 제거
     */

    public void expire(HttpServletRequest request){
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null){
            sessionStore.remove(sessionCookie.getValue());
        }
    }


}
