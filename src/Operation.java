@FunctionalInterface
public interface Operation<T> {
    T operator(T value1,T value2);
}

