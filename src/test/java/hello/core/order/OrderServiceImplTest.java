package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    //OrderServiceImpl에는 생성자 주입방식을 썼다 그래서 스프링없이 자바코드로 순수하게 OrderServiceImpl만 테스트하고싶을때
    //아래와같이 할수있고 생성자에 MemoryMemberRepository, FixDiscountPolicy 객체를 강제로 넣어야 컴파일에러가 안나기에 개발자가
    //사전에 인지할수있다
    @Test
    void createOrder(){
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}