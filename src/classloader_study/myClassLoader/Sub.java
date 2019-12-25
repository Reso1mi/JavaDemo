package classloader_study.myClassLoader;

/**
 * @author imlgw.top
 * @date 2019/8/22 16:43
 */
public class Sub {
    public Sub(){
        System.out.println("Sub is load by"+this.getClass().getClassLoader());
        new Parent(); //构造Sub类
        //访问父加载器加载的类
        System.out.println("Sub can see "+Parent.class);
    }
}
