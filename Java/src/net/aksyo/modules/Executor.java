package net.aksyo.modules;

import java.util.Objects;
import java.util.concurrent.Executors;

/**
 *
 * Executes a operation
 *
 * @param <T> the type of input
 */

@FunctionalInterface
public interface Executor<T> {


    void execute(T t);


    default void iterateAsync(Executor<? super T> executor, T value, int i) {
        Executors.newCachedThreadPool().submit(() -> {
            for(int j = 0; j < i; j++) {
                execute(value);
            }
        });

    }

    default void iterate(Executor<? super T> executor, T value, int i) {
        for (int j = 0; j < i; j++) {
            execute(value);
        }

    }

    default Executor<T> andThen(Executor<? super T> after) {
        Objects.requireNonNull(after);
        return (t) -> {
            execute(t);
            after.execute(t);
        };
    }
}
