package com.study.authentication.config.init;

import com.study.authentication.domain.member.Member;
import com.study.authentication.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class initData {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        memberRepository.saveMember(new Member("test", "1234", "kim", "XXX-XXXX-XXXX"));
    }
}
