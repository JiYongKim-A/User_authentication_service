package com.study.authentication.config.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionManager {

    void createSession(Object value, HttpServletResponse response);

    Object getSession(HttpServletRequest request);

    void expireSession(HttpServletRequest request);
}
