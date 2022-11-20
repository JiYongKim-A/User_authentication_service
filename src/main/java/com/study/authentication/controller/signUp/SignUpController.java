package com.study.authentication.controller.signUp;

import com.study.authentication.domain.member.Member;
import com.study.authentication.service.signUp.SignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String signUpForm(@ModelAttribute("member") SignUpForm form) {
        return "/signUp/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@Validated @ModelAttribute("member") SignUpForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
//            log.info("[SignUpController Log] : 회원 가입 에러");
            log.info("err = {}",bindingResult);
            return "/signUp/signUp";

        }
        if(signUpService.idCheck(form.getId())){
            bindingResult.addError(new FieldError("member", "id", "이미 존재하는 ID 입니다."));
            log.info("err = {}",bindingResult);
            return "/signUp/signUp";
        }
        if (!form.getPassword().equals(form.getPasswordCheck())) {
            bindingResult.addError(new FieldError("member", "passwordCheck", "비밀번호가 동일하지 않습니다."));
            return "/signUp/signUp";
        }
        if(!validPhoneNumber(form.getTel())){
            bindingResult.addError(new FieldError("member","tel","전화번호 형식을 일치시켜 주세요."));
            return "/signUp/signUp";
        }
        if(signUpService.telCheck(form.getTel())){
            bindingResult.addError(new ObjectError("member","전화번호로 등록된 계정이 존재합니다."));
            return "/signUp/signUp";
        }

        //success logic
        signUpService.signUp(new Member(form.getId(), form.getPassword(), form.getName(), form.getTel()));
        return "redirect:/login";
    }

    public static boolean validPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
        Matcher matcher = pattern.matcher(number);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

    }
}
