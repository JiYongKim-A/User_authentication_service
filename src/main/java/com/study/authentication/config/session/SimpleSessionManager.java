package com.study.authentication.config.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
// 자체적 세션 구동 방식
public class SimpleSessionManager {
    public static final String SESSION_COOKIE_NAME = "simpleSessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    // Session 생성
    public void createSession(Object value, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie simpleSessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(simpleSessionCookie);
    }

    //Session 확인
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null;
        }
        return sessionStore.get(sessionCookie.getValue());


    }

    // 쿠키 확인
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        return Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    // 세션 만료
    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }


}
