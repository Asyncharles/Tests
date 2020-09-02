package net.aksyo.modules;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ListFiller {

    public static <T> void fill(List<T> list, T filler) {

        for(int i = 0; i < list.size(); i++) {

            list.set(i, filler);

        }

    }

    public static <T> void fillAction(List<T> list, Supplier<T> filler) {

        for(int i = 0; i < list.size(); i++) {

            list.set(i, filler.get());

        }

    }

    public static <T> Stack<T> toStack(List<T> list) {

        final Stack<T> stack = new Stack<>();

        for(T value : list) {
            stack.push(value);
        }

        return stack;

    }

    



}
