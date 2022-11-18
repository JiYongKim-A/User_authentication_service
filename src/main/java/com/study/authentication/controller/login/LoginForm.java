package com.study.authentication.controller.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;
}
