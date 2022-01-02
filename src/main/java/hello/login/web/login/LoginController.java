package hello.login.web.login;


import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm")LoginForm form) {

        return "login/loginForm";
    }
//    @PostMapping("/login")
    public String loginForm(@Validated @ModelAttribute("loginForm") LoginForm form,
                            BindingResult bindingResult,
                            HttpServletResponse response) {
       //검증
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login member = {}",loginMember);
        log.info("form  = {}, {}",form.getLoginId(),form.getPassword());

        // 로그인 실패 로직
        if(loginMember == null){
            log.info("login fail = {}",loginMember);
            // bindingResult.reject() 시 global 오류
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다. ");
            return "login/loginForm";
        }

        //로그인 성공


    // 쿠키 생성 , 쿠키 value에는 String이 들어가야함
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));

        //response에 넣어서 보내줘야 하기때문에 HttpServletResponse 추가
        // 쿠키에 시간 정보를 넣지 않으면 세션쿠기가 됨( 브라우저 종료시 모두 종료)
        response.addCookie(idCookie);
        log.info("login success");
        return "redirect:/";

    }

//    @PostMapping("/login")
    public String loginV2(@Validated @ModelAttribute("loginForm") LoginForm form,
                            BindingResult bindingResult,
                            HttpServletResponse response) {
       //검증
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login member = {}",loginMember);
        log.info("form  = {}, {}",form.getLoginId(),form.getPassword());

        // 로그인 실패 로직
        if(loginMember == null){
            log.info("login fail = {}",loginMember);
            // bindingResult.reject() 시 global 오류
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다. ");
            return "login/loginForm";
        }

        //로그인 성공

        //세션 관리자를 통해 세션 생성 , 회원 데이터 보관
        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute("loginForm") LoginForm form,
                          BindingResult bindingResult,
                          HttpServletRequest request,
                          @RequestParam(defaultValue = "/") String redirectURL) {
       //검증
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login member = {}",loginMember);
        log.info("form  = {}, {}",form.getLoginId(),form.getPassword());

        // 로그인 실패 로직
        if(loginMember == null){
            log.info("login fail = {}",loginMember);
            // bindingResult.reject() 시 global 오류
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다. ");
            return "login/loginForm";
        }


        //로그인 성공 처리
        //HttpSession은 request가 필요
        // 세션 있으면 있는 세션 반환 ,없으면 신규 세션 생성 반환
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        // 기본적 메모리 저장됨
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        // 없으면 defaual : / 로 가고 있으면 그 url로 간다
        return "redirect:"+redirectURL;


    }

//    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        //쿠키를 없애는 방법은 쿠키의 시간을 없애면 됨
        expireCookie(response,"memberId");
        return "redirect:/";


    }

//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request) {
        sessionManager.expire(request);
        return "redirect:/";


    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";


    }

    private void expireCookie(HttpServletResponse response,String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);

        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
