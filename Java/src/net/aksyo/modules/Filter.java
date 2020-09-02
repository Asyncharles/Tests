package net.aksyo.modules;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Filter {

    public static <T> List<Object> filter(T filter, Map... values) {

        List<Object> obj = new ArrayList<>();

        for(Map m : values) {
            if(m.containsKey(filter)) {
                obj.add(m.get(filter));
            }
        }

        return obj;

    }


}
