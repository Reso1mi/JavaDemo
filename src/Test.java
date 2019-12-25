
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author imlgw.top
 * @date 2019/9/8 9:59
 */
public class Test {
    static class MyStack<T>{

        T [] objValues=null;

        int top=-1;

        public MyStack(int size){
            objValues= (T[]) new Object[size];
        }

        public void push(T obj){
            objValues[++top]=obj;
        }

        public T peek(){
            if (top<0) {
                throw new RuntimeException("stack is isEmpty");
            }
            return objValues[top];
        }

        public T pop(){
            if (top<0) {
                throw new RuntimeException("stack is isEmpty");
            }
            //覆盖
            return objValues[top--];
        }

        public T get(int index){
            if (index>top || index < 0) {
                throw new RuntimeException("index is wrong");
            }
            return objValues[index];
        }

        public boolean isEmpty(){
            return top<0;
        }

        public int size(){
            return top+1;
        }
    }


    public String frequencySort(String s) {
        if (s==null || s.length()<1) {
            return s;
        }
        HashMap<Character,Integer> map=new HashMap<>();
        for (int i=0;i<s.length();i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0)+1);
        }
        ArrayList<HashMap.Entry> list=new ArrayList<>();
        for(HashMap.Entry entry:map.entrySet()){
            list.add(entry);
        }
        list.sort((e1,e2)->(Integer)e2.getValue()-(Integer)e1.getValue());
        String res="";
        for (int i = 0; i < list.size(); i++) {
            Integer value = (Integer)list.get(i).getValue();
            while (value>=0){
                res+=list.get(i).getKey();
                value--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //HashMap<StringBuilder,Integer> hashMap=new HashMap<>();
        //StringBuilder stringBuilder=new StringBuilder("1");
        //StringBuilder stringBuilder2=new StringBuilder("1");
        HashMap<String,Integer> hashMap=new HashMap<>();
        String string=new String("1");
        hashMap.put(string,1);
        System.out.println(hashMap.containsKey(new String("1")));


/*        List<Integer>[] res=new ArrayList[2];
        Object[] rrr=res;
        rrr[0]=1;
        System.out.println(rrr[0]);*/

        Integer integer= (Integer) new Object();
        System.out.println(integer);
    }

    public String frequencySort2(String s) {

        if (s==null || s.length()<1) {
            return s;
        }
        int[] freq=new int[256];
        for (int i=0;i<s.length();i++) {
            freq[s.charAt(i)]++;
        }
        Integer[] freq_bak=new Integer[freq.length];
        //clone一份用于找index
        for (int i=0;i<freq.length;i++){
            freq_bak[i]=freq[i];
        }
        Arrays.sort(freq_bak,Collections.reverseOrder());
        String res="";
        for (int i = 0; i <255 &&freq[i]!=0; i++) {
            for (int j=0;j<255;j++) {
                if(freq_bak[j].intValue()==freq[i]){
                    //根据freq_bak[j]构造结果
                    while(freq_bak[j]>0){
                        res+=(char)j;
                        freq_bak[j]--;
                    }
                }
            }
        }
        return res;
    }

    public static List<Integer> topKFrequent2(int[] nums, int k) {
        if(nums==null||nums.length<=0){
            return null;
        }
        HashMap<Integer,Integer> fre=new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            //fre.get(i) nums[i]出现的频次
            fre.put(nums[i],fre.getOrDefault(nums[i],0)+1);
        }
        //1:3,2:3,3:1
        PriorityQueue<HashMap.Entry<Integer,Integer>> pq=new PriorityQueue<>(( o1,o2)->o1.getValue());

        //维护k小根堆
        for (HashMap.Entry ent:fre.entrySet()) {
            pq.add(ent);
            if(pq.size()>k){
                pq.poll();
            }
        }
        ArrayList<Integer> res=new ArrayList<>();
        while (pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        return res;
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res=new LinkedList<>();
        if (root==null) {
            return res;
        }
        if (root!=null && root.left==null && root.right!=null && root.val==sum) {
            ((LinkedList<List<Integer>>) res).getLast().add(root.val);
        }
        List<List<Integer>> lefts=pathSum(root.left,sum-root.val);
        List<List<Integer>> rights=pathSum(root.right,sum-root.val);

        for (int i=0;i<lefts.size();i++) {
            ((LinkedList<Integer>)lefts.get(i)).addFirst(root.val);
        }

        for (int i=0;i<rights.size();i++) {
            ((LinkedList<Integer>)rights.get(i)).addFirst(root.val);
        }
        return res;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
