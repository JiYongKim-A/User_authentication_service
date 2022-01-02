package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    /**
     *
     * @param loginId
     * @param password
     * @return null 이면 로그인 실패패     */

    public Member login(String loginId, String password) {


        // 기본 사용
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get();
//        if(member.getPassword().equals(password)){
//            return member;
//        } else {
//            return null;
//        }

        //개선 1
//        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
//        byLoginId.filter(m -> m.getPassword().equals(password))
//                .orElse(null);

        //개선 2
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
