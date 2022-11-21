package com.study.authentication.controller.home;

import com.study.authentication.config.session.SessionConst;
import com.study.authentication.config.session.SessionManager;
import com.study.authentication.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequiredArgsConstructor
public class HomeControllerV2 {

    // SpringFramework 에서 지원하는 Session 방식 사용
    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIM_MEMBER,required = false)Member member, Model model) {
        if(member == null){
            return "home";
        }
        model.addAttribute("memberName", member.getName());
        return "loginHome";
    }
}
