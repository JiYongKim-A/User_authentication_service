package com.study.authentication.controller.signUp;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignUpForm {
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
