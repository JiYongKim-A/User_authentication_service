package com.study.authentication.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
    private Long manageSeq;

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @NotEmpty
    private String tel;

    public Member(String id, String password, String name, String tel) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.tel = tel;
    }
}
