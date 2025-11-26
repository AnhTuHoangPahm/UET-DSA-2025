package DataStruct.Stack;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Iterator;

public class MyStack<T extends Comparable<T>> implements Iterable<T> {
    private T[] data;
    private int N; // index
    private int CAPACITY;

    @SuppressWarnings("unchecked")
    public MyStack(T defaultVal, int capacity) {
        try {
            if (defaultVal == null) throw new InvalidParameterException();
            CAPACITY = capacity != 0 ? capacity : 1; // if creates empty MyStack
            data = (T[]) Array.newInstance(defaultVal.getClass(), CAPACITY);
            Arrays.fill(data, defaultVal);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void push(T val) {
        expand();
        data[N++] = val;
    }

    public T pop() {
        shrink();
        return data[--N];
    }

    // utility
    public boolean isEmpty() {
        return N==0;
    }

    public long size() {
        return CAPACITY;
    }

    private void expand() {
        if (N == CAPACITY) { // array used up
            CAPACITY*=2;
            data = Arrays.copyOf(data, CAPACITY);
        }
    }

    private void shrink() {
        if (N == CAPACITY/4) { // array 1/4 full
            CAPACITY/=2;
            data = Arrays.copyOf(data, CAPACITY);
        }
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < CAPACITY && data[currentIndex] != null;
            }

            @Override
            public T next() {
                return data[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
