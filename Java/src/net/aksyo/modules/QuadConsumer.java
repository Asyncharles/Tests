package net.aksyo.modules;

import java.util.Objects;
import java.util.function.BiConsumer;

@FunctionalInterface
public interface QuadConsumer<T, V, X, U> {

    void accept(T t, V v, X x, U u);

    default QuadConsumer<T, V, X, U> andThen(QuadConsumer<? super T, ? super V, ? super X, ? super U> after) {
        Objects.requireNonNull(after);
        return (t, v ,x , u) -> {
            accept(t, v,  x, u);
            after.accept(t, v, x, u);
        };
    }

}
