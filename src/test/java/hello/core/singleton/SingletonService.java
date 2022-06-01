package hello.core.singleton;

public class SingletonService {

    //singleton 생성하는 방법은 여러가지이지만 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택

    //static 선언하면 java가 뜰때 static 영역에 이 객체 딱 하나만 올라가게끔 한다
    private static final SingletonService instance = new SingletonService();

    //public으로 열어서 해당 객체를 쓰기위해서는 getInstance()를 호출해야만 가능
    public static SingletonService getInstance(){
        return instance;
    }

    private  SingletonService(){

    }

    public void logic(){
        System.out.println("싱글통 객체 로직 호출");
    }
    
    public static void main(String[] args) {
        
        //static로 선언한다해도 외부에서 누군가 아래와같이 쓰면 계속 객체가 생성되는 문제가 발생
        //그래서 private로 못쓰게 막는다 지금은 같은파일내에 존재하기에 가능한것이다
        SingletonService singletonService1 = new SingletonService();
        SingletonService singletonService2 = new SingletonService();
    }
}
