package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);//memberService가 MemberServiceImpl의 인스턴스면 맞다
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = applicationContext.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);//memberService가 MemberServiceImpl의 인스턴스면 맞다
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("구체 타입으로 조회")//역할과구현으로 interface 를 조회하는게 맞다 구현체로 (impl)하면 안되지만 어쩔수없을때 사용
    void findBeanByName2(){
        MemberServiceImpl memberService = applicationContext.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);//memberService가 MemberServiceImpl의 인스턴스면 맞다
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("빈 이름으로 조회x")//assertThrows -> junit
    void findBeanByNameX(){
//        MemberService xxx = applicationContext.getBean("xxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                ()->applicationContext.getBean("xxx", MemberService.class));//->에있는 메서드를 실행해서 NoSuch exception 이 발생해야 테스트 성공
    }
}
