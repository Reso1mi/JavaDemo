package classloader_study.myClassLoader;

/**
 * @author imlgw.top
 * @date 2019/8/22 16:43
 */
public class Parent {
    public Parent() {
        System.out.println("Parent is load by" + this.getClass().getClassLoader());
        //Hello();
    }

    public void Hello() {
        //父加载器访问子加载器加载的类
        System.out.println("Parent can see the " + Sub.class);
    }
}
