package DataStruct.PriorityQueue;

import java.util.Arrays;

@SuppressWarnings("unchecked")
public class BinaryHeap<T extends Comparable<T>> {
    T[] data;
    int N;
    int CAPACITY;
    boolean isMaxH = true;

    public BinaryHeap() {
        N = 0;
        CAPACITY = 4; // this is 1-indexed
        data = (T[]) new Comparable[5];
    }

    public BinaryHeap(int initialSize) {
        N = 0;
        CAPACITY = initialSize;
        data = (T[]) new Comparable[initialSize+1];
    }

    void setToMinH() {
        this.isMaxH = false;
    }

    private void add(T n, boolean isMaxH) {
        expand();
        data[++N] = n;
        if(isMaxH) swimMaxH(N);
        else swimMinH(N);
    }
    public void add(T n) {
        add(n, isMaxH);
    }

    // k: index
    private void swimMaxH(int k) {
        while (k > 1 && less(k/2, k)) {
            exchange(k, k/2);
            k /= 2;
        }
    }

    private void swimMinH(int k) {
        while (k > 1 && less(k, k/2)) {
            exchange(k, k/2);
            k /= 2;
        }
    }

    // parent value should be > its 2 child
    private void sinkMaxH(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (less(j, k)) break; // satisfied
            exchange(j, k);
            k = j;
        }
    }

    private void sinkMinH(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j + 1, j)) j++;
            if (!less(j, k)) break; // satisfied
            exchange(j, k);
            k = j;
        }
    }

    // delete most significant element
    public T poll() {
        T r = data[1];
        exchange(1, N--);

        if(isMaxH) sinkMaxH(1);
        else sinkMinH(1);

        shrink();
        return r;
    }

    // util
    boolean isEmpty() {
        return N==0;
    }

    void expand() {
        if(N == CAPACITY) {
            CAPACITY *= 2;
            data = Arrays.copyOf(data, CAPACITY);
        }
    }

    void shrink() {
        if(N == CAPACITY/4) {
            CAPACITY /= 2;
            data = Arrays.copyOf(data, CAPACITY);
        }
    }

    // helpers
    void exchange(int k1, int k2) {
        T t = data[k1];
        data[k1] = data[k2];
        data[k2] = t;
    }

    boolean less(int k1, int k2) {
        int cmp = data[k1].compareTo(data[k2]);
        return cmp < 0;
    }

    public void reveal() {
        for (int i = 1; i <= N; i++) {
            System.out.print(data[i] + " ");
        }
    }
}