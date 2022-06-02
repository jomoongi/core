package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component//해당 어노테이션을 넣어줌으로써 class가 스프링 빈에 등록된다
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<Long, Member>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
