package advance_thread_study.Chapter8;

import java.util.stream.IntStream;

public class ImmutableClient {
    public static void main(String[] args) {
        Person person = new Person("IMLGW","湖北");
        IntStream.range(0,100).forEach(i->
            new UsePersonThread(person).start()
        );
    }
}
