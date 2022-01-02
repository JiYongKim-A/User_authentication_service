package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);

        store.put(member.getId(), member);

        return member;
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all =findAll();
//        for (Member member : all) {
//            if(member.getId().equals(loginId)){
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();
        // 람다형으로 변형하면
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
//test
    public void clearStore() {
        store.clear();
    }



}
