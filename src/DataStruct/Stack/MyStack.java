package DataStruct.Stack;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class MyStack<T extends Comparable<T>> implements Iterable<T> {
    private T[] data;
    private int N; // index
    private int CAPACITY;

    public MyStack() {
        CAPACITY = 4;
        data = (T[]) new Comparable[CAPACITY];
    }

    public MyStack(int capacity) {
        CAPACITY = capacity;
        data = (T[]) new Comparable[CAPACITY];
    }

    public void push(T val) {
        expand();
        data[N++] = val;
    }

    public T pop() {
        shrink();
        return data[--N];
    }

    public T peek() {
        return data[N-1];
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

    public String toString() {
        return Arrays.toString(data);
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
