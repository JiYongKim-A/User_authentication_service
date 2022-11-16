package com.study.authentication.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    // 회원 관리 번호
    private Long manageSeq;

    // 회원 ID
    @NotEmpty
    private String id;

    // 회원 PW
    @NotEmpty
    private String password;

    // 회원 이름
    @NotEmpty
    private String name;

    // 회원 전화 번호
    @NotEmpty
    private String tel;

    public Member(String id, String password, String name, String tel) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.tel = tel;
    }
}
