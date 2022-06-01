package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("@configuration이 잘 동작해서 싱글톤으로 생성되는지 보기위한 테스트")
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //원래는 당연히 구체타입(impl)을 꺼내면 안되지만 구체타입에 get을 써서 테스트를 하기위함이다
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        //모두 같은 인스턴스를 참조하고 있다
        System.out.println("memberService -> memberRepository1 = " + memberRepository1);
        System.out.println("orderService-> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        //모두 같은 인스턴스를 참조하고 있다
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("@configuration이 붙으면서 어떻게 싱글튼을 적용시켜주는지")
    void configurationDeep() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        //우리가 등록한것은 AppConfig(부모)이지만 실제 출력해보면 AppConfig@CGLIB(자식)로 되어있다
        //바이트코드를 조작하는 CGLIB기술
        //CGLIB내부 코드는 if(memberRepository){return 스프링 컨테이너에서 찾아서 반환} else(기존 로직에서 MemberRepository를 생성하고 스프링 컨테이너에 등록){return 반환}
        System.out.println("bean = " + bean.getClass());
    }
}
