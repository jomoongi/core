package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//Type이라고 하면 class level에 붙는다
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    
}
