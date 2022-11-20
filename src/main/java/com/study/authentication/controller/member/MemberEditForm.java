package com.study.authentication.controller.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class MemberEditForm {
    @NotEmpty
    private String id;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordCheck;

    @NotEmpty
    private String name;

    @NotEmpty
    private String tel;

}
