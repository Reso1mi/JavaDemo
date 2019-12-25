package advance_thread_study.Chapter8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {
    private final int age;
    private final String name;
    private final List<String> list;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    //这里是有问题的,引用类型不能直接返回
    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    public ImmutableTest(int age, String name) {
        this.age = age;
        this.name = name;
        this.list = new ArrayList<>();
    }

}
