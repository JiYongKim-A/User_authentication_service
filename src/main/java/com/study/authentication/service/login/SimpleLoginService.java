package com.study.authentication.service.login;

import com.study.authentication.domain.member.Member;
import com.study.authentication.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleLoginService implements LoginService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String id, String pw) {
        return memberRepository.findMemberById(id)
                .filter(m -> m.getPassword().equals(pw))
                .orElse(null);
    }
}
