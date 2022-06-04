package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(3);

        //helloLombok자체가 helloLombok.toString()이다
        System.out.println("helloLombok = " + helloLombok);
    }
}
