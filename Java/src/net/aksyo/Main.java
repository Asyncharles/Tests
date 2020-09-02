package net.aksyo;




import net.aksyo.modules.Executor;
import net.aksyo.modules.ListFiller;
import net.aksyo.modules.Pair;
import net.aksyo.modules.QuadConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {

    public static void main(String[] args) {

        final Random random = new Random();
        final List<String> stringList = new ArrayList<>();
        stringList.add("Test 1");
        stringList.add("Test 2");
        stringList.add("Test 3");

        ListFiller.fillAction(stringList, () -> random.nextBoolean() ? "true" : "false");

        Pair<String, String> pair = Pair.of("Java executors ", "are cool");

        stringList.forEach(System.out::println);

        Executor<Integer> executor = i -> {
            i++;
            System.out.println(i);
        };

        executor.execute(2);
        executor.iterateAsync(executor, 4, 3);


    }




}
