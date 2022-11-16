package com.study.authentication.domain.repository.member;

import com.study.authentication.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 회원 저장 메소드
    Member saveMember(Member member);

    // 회원 관리 번호로 회원 검색
    Optional<Member> findMemberByManageSeq(Long manageSeq);

    // 회원 ID로 회원 검색
    Optional<Member> findMemberById(String id);

    // 모든 회원 검색
    List<Member> findAll();

    // 회원 관리 번호로 업데이트
    Member updateMemberByManageSeq(Long manageSeq, Member updatedMember);

    // 회원 관리 번호로 회원 탈퇴
    void removeMemberByManageSeq(Long manageSeq);

    // Test 용도 저장된 회원 삭제
    void clearStore();

}
