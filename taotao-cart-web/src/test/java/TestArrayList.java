import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestArrayList {

    public static void main(String[] args) {
        Long a = 129l;
        Long b = 129l;
        System.out.println(a == b);
        List list = new ArrayList<String>();
        list.add("一");
        list.add("二");
        list.add("三");
        list.add("四");
        for(int i = list.size()-1; i >= 0; i --){
            if ("二".equals(list.get(i).toString())){
                list.remove(i);
            }
            System.out.println(i);
        }
//        Iterator iterator = list.iterator();
//        while(iterator.hasNext()){
//            if ("二".equals(iterator.next().toString())) {
//                iterator.remove();
//            }
//        }
        System.out.println(list);
//        System.out.println(iterator);
//        for (Object o : list) {
//            if ("二".equals(o.toString())) {
//                list.remove(o);
//            }
//        }
    }
}
