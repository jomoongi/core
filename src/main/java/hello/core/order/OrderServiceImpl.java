package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class  OrderServiceImpl implements OrderService{

    //DI 주입방식 중 @Autowired private MemberRepository memberRepository; 이런 방식은 스프링에서만 실행해야 테스트가 가능하고
    //일반 자바코드로는 테스트 할 수가없다
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
 
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    //테스트 용도(Appconfig에서 보면 MemberRepository가 두번 호출되니 싱글톤이 깨지는것이 아닌가에 대한 테스트)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
