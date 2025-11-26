package DataStruct.PriorityQueue;

public class MyPriorityQueue<T extends Comparable<T>> {
    public enum HeapType {
        MAX_HEAP,
        MIN_HEAP;
    }
    BinaryHeap<T> bh;
    boolean f = true; // default: maxH

    public MyPriorityQueue() {
        bh = new BinaryHeap<>();
    }

    public MyPriorityQueue(int initialSize) {
        bh = new BinaryHeap<>(initialSize);
    }

    public MyPriorityQueue(final HeapType ht) {
        bh = new BinaryHeap<>();
        f = ht.equals(HeapType.MAX_HEAP);
        if(!f) bh.setToMinH();
    }

    public MyPriorityQueue(final HeapType ht, int initialSize) {
        bh = new BinaryHeap<>(initialSize);
        f = ht.equals(HeapType.MAX_HEAP);
        if(!f) bh.setToMinH();
    }

    public void enqueue(T n) {
        bh.add(n);
    }

    public T poll() {
        return bh.poll();
    }

    public T peek() {
        return bh.data[1];
    }

    public void reveal() {
        bh.reveal();
    }
}
