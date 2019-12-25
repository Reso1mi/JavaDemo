/**
 * @author imlgw.top
 * @date 2019/10/3 16:38
 */
public class GenericArray<T extends Integer> {
    private T[] array;

    @SuppressWarnings("unchecked")
    public GenericArray(int sz) {
        array = (T[]) new Object[sz];   // 创建泛型数组
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) { return array[index]; }

    // Method that exposes the underlying representation:
    public T[] rep() { return array; }     //返回数组 会报错

    public static void main(String[] args) {
        GenericArray<Integer> gai =
                new GenericArray<>(10);
        // This causes a ClassCastException:
        Integer[] ia = gai.rep();
        // This is OK:
        //Object[] oa = gai.rep();
    }
}
