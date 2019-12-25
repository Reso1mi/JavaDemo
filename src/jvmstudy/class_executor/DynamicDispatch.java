package jvmstudy.class_executor;

/**
 * @author imlgw.top
 * @date 2019/9/7 17:32
 */
public class DynamicDispatch {
    public static void main(String[] args) {
        Human man= new Man();
        Human woman= new Woman();
        man.eat();
        woman.eat();

        man=new Woman();
        man.eat();
    }
}

class Human {
    public void eat(){
        System.out.println("Human eat");
    }
}

class Woman extends Human{
    public void eat(){
        System.out.println("Woman eat");
    }
}

class Man extends Human{
    public void eat(){
        System.out.println("Man eat");
    }
}


