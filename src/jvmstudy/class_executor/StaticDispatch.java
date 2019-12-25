package jvmstudy.class_executor;

/**
 * @author imlgw.top
 * @date 2019/9/7 14:19
 */
public class StaticDispatch {
    public void eat(Animal animal){
        System.out.println("Animal eat food");
    }

    public void eat(Cat cat) {
        System.out.println("Cat eat fish");
    }

    public void eat(Dog dog) {
        System.out.println("Cat eat fish");
    }

    public static void main(String[] args) {
        StaticDispatch staticDispatch = new StaticDispatch();
        Animal cat= new Cat();
        Animal dog= new Dog();
        staticDispatch.eat(cat);
        staticDispatch.eat(dog);
    }
}

class Animal {

}

class Cat extends Animal{

}

class Dog extends Animal{

}
