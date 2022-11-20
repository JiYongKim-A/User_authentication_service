package com.study.authentication.domain.repository.member;

import com.study.authentication.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepositoryImpl implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long manageSeq = 0L;
    @Override
    public Member saveMember(Member member) {
        if(findMemberById(member.getId()).isEmpty()){
            member.setManageSeq(++manageSeq);
            store.put(member.getManageSeq(), member);
            return member;
        }
        return null;
    }

    @Override
    public Optional<Member> findMemberByManageSeq(Long manageSeq) {
        return findAll().stream()
                .filter(m -> m.getManageSeq().equals(manageSeq))
                .findFirst();
    }

    @Override
    public Optional<Member> findMemberById(String id) {
        return findAll().stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member updateMemberByManageSeq(Long manageSeq, Member updatedMember) {
        Optional<Member> optionalMember = findMemberByManageSeq(manageSeq);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.setId(updatedMember.getId());
            member.setPassword(updatedMember.getPassword());
            member.setName(updatedMember.getName());
            member.setTel(updatedMember.getTel());
            return updatedMember;
        }
        return null;
    }

    @Override
    public void removeMemberByManageSeq(Long manageSeq) {
        Optional<Member> optionalMember = findMemberByManageSeq(manageSeq);
        if (optionalMember.isPresent()) {
            store.remove(manageSeq);
        }
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
