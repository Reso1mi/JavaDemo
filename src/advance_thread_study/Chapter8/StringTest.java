package advance_thread_study.Chapter8;
import java.lang.reflect.Field;

public class StringTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        /*String s="Hello";
        String s1=s.replace("l","K");
        System.out.println(s.hashCode());
        System.out.println(s1.hashCode());*/
        String s = "Hello World";
        System.out.println("s = " + s);//Hello World

        Field valueFieldOfString = String.class.getDeclaredField("value");
        valueFieldOfString.setAccessible(true);

        char[] value = (char[]) valueFieldOfString.get(s);
        value[5] = '_';
        System.out.println("s = " + s); //Hello_World
    }
}