package com.study.authentication.service.signUp;

import com.study.authentication.domain.member.Member;

public interface SignUpService {
    // ID 중복 체크
    Boolean idCheck(String id);

    // 전화 번호 중복 체크
    Boolean telCheck(String number);

    // 회원 가입
    Member signUp(Member member);
}
