package com.study.authentication.controller.member;

import com.study.authentication.config.session.SessionManager;
import com.study.authentication.domain.member.Member;
import com.study.authentication.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberControllerV1 {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping("/memberInfo")
    public String memberInfo(HttpServletRequest request, Model model) {
        Member member = (Member) sessionManager.getSession(request);
        model.addAttribute("member", member);
        return "member/memberInfo";
    }

    @GetMapping("/memberInfo/edit")
    public String memberEditForm(HttpServletRequest request, Model model) {
        Member member = (Member) sessionManager.getSession(request);

        model.addAttribute("member", new MemberEditForm(member.getId(), "","",member.getName(),member.getTel()));
        return "member/memberInfoEditForm";
    }

    @PostMapping("/memberInfo/edit")
    public String memberEdit(@Validated @ModelAttribute("member")MemberEditForm form,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("[MemberControllerV1 Log] : 회원 정보 수정 에러");
            return "member/memberInfoEditForm";
        }

        if (!form.getPassword().equals(form.getPasswordCheck())) {
            bindingResult.addError(new FieldError("member","passwordCheck","비밀번호가 동일하지 않습니다."));
        }

        //success logic
        Optional<Member> findMem = memberRepository.findMemberById(form.getId());
        if(findMem.isEmpty()){
            return "redirect:/logout";
        }
        memberRepository.updateMemberByManageSeq(findMem.get().getManageSeq(),
                new Member(form.getId(), form.getPassword(), form.getName(), form.getTel()));
        return "redirect:/memberInfo";
    }

}
