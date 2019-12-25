package jvmstudy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author imlgw.top
 * @date 2019/8/17 16:35
 */
public class Parallel{

    /*-XX:+PrintGCDetails
    * -Xms30m
    * -Xmx30m
    * -Xmn10m
    * */
    public static void main(String[] args) throws InterruptedException {
        List<Object> caches=new ArrayList<Object>();
        for(int i=0;i<7;i++){
            caches.add(new byte[1024*1024*3]);
        }
        caches.clear();
        for(int i=0;i<2;i++){
            caches.add(new byte[1024*1024*3]);
        }
        Thread.sleep(10000);
    }
}
