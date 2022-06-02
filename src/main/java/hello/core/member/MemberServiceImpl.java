package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired//기존에는 AppConfig에서 의존관계를 수작업으로 넣어줬지만 AutoAppConfig보면 아무것도 설정되어있는것이 없기에 여기서 @Autowired를 써서 자동으로 의존관계를 주입해준다
    //ac.getBean(MemberRepository.class)와 같은 역할을 한다고 생각하면 된다(디테일하게 보면 다르지만 대략적으로)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도(Appconfig에서 보면 MemberRepository가 두번 호출되니 싱글톤이 깨지는것이 아닌가에 대한 테스트)
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

