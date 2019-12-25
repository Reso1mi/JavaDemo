package advance_thread_study.Chapter8;

final public class Person {
    //定义成final
    private final String name;
    private final String address;

    public Person(final String name,final String address) {
        this.name = name;
        this.address = address;
    }

    public String getAddress() {

        return address;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
