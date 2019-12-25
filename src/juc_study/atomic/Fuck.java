package juc_study.atomic;


/**
 * @author imlgw.top
 * @date 2019/4/23 21:37
 */
public class Fuck {
    public static void main(String[] args) {
        int A = 0x35;
        int B = 0x63;
        System.out.println(Integer.toHexString(A) + ","+Integer.toHexString(A+1)+", "+Integer.toHexString(~A));
        System.out.println(Integer.toHexString(A|B) + ","+Integer.toHexString((A|B)+1)+", "+Integer.toHexString(~(A+B)));
        System.out.println(Integer.toHexString(A|(~B)) + ","+Integer.toHexString((A|(~B))+1)+", "+Integer.toHexString(~A&B));
        System.out.println("ff" + ","+Integer.toHexString(00)+", "+Integer.toHexString(00));
        System.out.println(Integer.toHexString(A+(A&(~B))) + ","+Integer.toHexString(A+(A&(~B))+1)+", "+Integer.toHexString(~(A&B)));
        System.out.println(Integer.toHexString((A|B)+(A&(~B))) + ","+Integer.toHexString((A|B)+(A&(~B))+1)+", "+Integer.toHexString(~B));
        System.out.println(Integer.toHexString(A-B-1) + ","+Integer.toHexString(A-B)+", "+Integer.toHexString(A^B));
        System.out.println(Integer.toHexString(A&(~B)-1) + ","+Integer.toHexString(A&(~B))+", "+Integer.toHexString(A&(~B)-1));
        System.out.println(Integer.toHexString(A+(A&B)) + ","+Integer.toHexString(A+(A&B)+1)+", "+Integer.toHexString(~A|B));
        System.out.println(Integer.toHexString(A+B) + ","+Integer.toHexString(A+B+1)+", "+Integer.toHexString(~(A^B)));
        System.out.println(Integer.toHexString((A|(~B))+(A&B)) + ","+Integer.toHexString((A|(~B))+(A&B)+1)+", "+Integer.toHexString(B));
        System.out.println(Integer.toHexString((A&B)-1) + ","+Integer.toHexString(A&B)+", "+Integer.toHexString(A&B));
        System.out.println(Integer.toHexString(A+A) + ","+Integer.toHexString(A+A+1)+", "+Integer.toHexString(1));
        System.out.println(Integer.toHexString((A|B)+A) + ","+Integer.toHexString((A|B)+A+1)+", "+Integer.toHexString(A|(~B)));
        System.out.println(Integer.toHexString((A|(~B))+A) + ","+Integer.toHexString((A|(~B))+A+1)+", "+Integer.toHexString(A|B));
        System.out.println(Integer.toHexString(A-1) + ","+Integer.toHexString(A)+", "+Integer.toHexString(A));
    }
}
