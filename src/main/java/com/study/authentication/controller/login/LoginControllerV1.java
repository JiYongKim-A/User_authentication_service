package com.study.authentication.controller.login;

import com.study.authentication.config.session.SessionManager;
import com.study.authentication.domain.member.Member;
import com.study.authentication.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginControllerV1 {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getId(), form.getPassword());
        if (loginMember == null) {
            log.info("[loginController Log] : 일치하지 않은 로그인 시도 ID = {}, PW = {}", form.getId(), form.getPassword());
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        //success logic
        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        sessionManager.expireSession(request);
        return "redirect:/";
    }
}