package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core",//만약 이것을 안해준다면? 현재 class파일 위치(hello.core)부터 스캔한다
        //이것은 우리가 만들었던 AppConfig에 있는것들이 스프링 컨테이너에 또 bean으로 등록되는것을 막기위함
        //요즘은 basePackages를 쓰지않고 설정파일을 프로젝트 최상단에 둔다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //같은 빈 이름인데도 충돌이 안나는 이유는?
    //아래와같이 수동으로 등록한 빈이 자동 빈을 오버라이딩 해버린다
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
