package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

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

