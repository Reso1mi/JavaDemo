package jvmstudy;

import java.util.*;

/**
 * @author imlgw.top
 * @date 2019/8/16 19:18
 */
public class GCAgeTest {
    private static int _1M= 1024*1024; //1m



    public static void main(String[] args) {
        PriorityQueue<HashMap.Entry<Integer,Integer>> pq=new PriorityQueue(new ComparatorMap());
        final Map.Entry poll = pq.poll();
        poll.getValue();
        pq.remove();
        byte[] alloc1,alloc2,alloc3,alloc4;
        alloc1=new byte[_1M/4];
        alloc2=new byte[4*_1M];
        alloc3=new byte[4*_1M];
        alloc3=null;
        alloc4=new byte[4*_1M];
    }

    static class ComparatorMap implements Comparator<HashMap.Entry<Integer,Integer>>{
        @Override
        public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
            return o1.getValue()-o2.getValue();
        }
    }
}
