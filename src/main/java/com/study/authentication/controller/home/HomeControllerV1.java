package com.study.authentication.controller.home;

import com.study.authentication.config.session.SessionManager;
import com.study.authentication.config.session.SimpleSessionManager;
import com.study.authentication.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeControllerV1 {
    private final SessionManager sessionManager;

    // 자체적 SessionManager 사용 방법
    @GetMapping("/")
    public String homeV1(HttpServletRequest request, Model model) {
        if(sessionManager.getSession(request) == null){
            return "home";
        }
        Member member = (Member) sessionManager.getSession(request);
        model.addAttribute("memberName", member.getName());
        return "loginHome";
    }

}
