package com.study.authentication.service.login;

import com.study.authentication.domain.member.Member;



public interface LoginService {
    Member login(String id, String pw);
}
