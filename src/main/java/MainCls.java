import com.google.gson.Gson;

import java.util.*;
import java.util.function.Supplier;

public class MainCls {
    public static void main(String[] args) throws Throwable {
//        Integer a = 1000, b = 1000;
//        System.out.println(a==b);
//
//        Integer c = 100, d = 100;
//        System.out.println(c==d);
//        List<Integer> data = Arrays.asList(1,2,3,4);
//        data.stream()
//                .filter(integer -> integer>5)
//                .findFirst()
//                .orElseThrow(() -> new Throwable("Not found"));
//
//        new Gson().toJson(args);

        Set<String> data = new HashSet<>();
        data.add("data1");
        data.add("data2");
        data.add("data3");
        String[] strings1 = new String[]{"1", "2", "3", "4", "5",};
        String[] strings = data.toArray(strings1);
        System.out.println(new Gson().toJson(strings));

    }
}
