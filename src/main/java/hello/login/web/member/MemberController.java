package hello.login.web.member;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) { // Member 생략 가능
        // 빈객체를 넘기는 이유는 바인딩과  검증하기 위해, 잘못입력시 데이터 남아있또록
        return "members/addMemberForm";

    }

    @PostMapping("/add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }

        //success logic
        memberRepository.save(member);
        log.info("Save Success name = {}, id = {} , pw= {}",member.getName(),member.getLoginId(),member.getPassword());


        return "redirect:/";
    }

}
