package com.study.authentication.service.signUp;

import com.study.authentication.domain.member.Member;
import com.study.authentication.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimpleSignUpService implements SignUpService {
    private final MemberRepository memberRepository;

    @Override
    public Boolean idCheck(String id) {
        return memberRepository.findMemberById(id).isPresent();
    }

    @Override
    public Boolean telCheck(String number) {
        return memberRepository.findAll().stream()
                .anyMatch(m -> m.getTel().equals(number));
    }

    @Override
    public Member signUp(Member member) {
        memberRepository.saveMember(member);
        return member;
    }
}
