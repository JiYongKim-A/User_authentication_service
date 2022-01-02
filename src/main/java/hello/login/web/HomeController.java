package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {

        return "/home";
    }

//    @GetMapping("/") // 쿠키꺼내는 방법은 여러가지 그중 spring 제공하는 @CookieValue 사용
    // name 은 쿠키의 키, required는 로그인 안한 사용자도 들어와야하니까 false
    // 타입 컨버팅 되서 Long으로 받을 수 있음
    public String homeLogin(@CookieValue(name = "memberId",required = false) Long memberId, Model model) {

        // 로그인을 안한 사용자
        if (memberId == null){
            return"/home";
        }

        // 쿠키가 작동되지 않는 사용자
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "/home";
        }

        //쿠키 사용되는 사용자
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        //세션 관리자에 저장된 회원 정보를 조회해보아야 함
        Member member = (Member)sessionManager.getSession(request);
        if(member == null){
            return "home";
        }

        //쿠키 사용되는 사용자
        model.addAttribute("member", member);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {
    //세션은 메모리 사용하기 때문에 필요할때만 만들어야함
        // getSession(true) 일경우 없어도 생성해서 반환해줌
        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원 데이터가 없으면 home으로
        if(loginMember == null){
            return "home";
        }

        //세션 회원 데이터 있으면 로그인된 홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
    @GetMapping("/")
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember
            , Model model) {

        // @SessionAttribute 에서
        //세션 조회, 없으면 null값 반환하고, session을 통해 loginMember 를 반환받음음
        // 이기능은 세션을 생성기능이 없음!!!
        // 세션을 찾아 올때만 사용하는 것!

        //세션에 회원 데이터가 없으면 home으로
        if(loginMember == null){
            return "home";
        }

        //세션 회원 데이터 있으면 로그인된 홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}