package DataStruct.Queue;
import DataStruct.LinkedList.SinglyLinkedList;

public class Queue<T> {
    protected final SinglyLinkedList<T> ll;

    public Queue() {
        ll = new SinglyLinkedList<>();
    }

    public void enqueue(T data) {
        ll.addLast(data);
    }

    public T dequeue() {
        T removed = ll.removeFirst();
        if (removed == null) {
            System.out.println("Empty queue!");
        }
        return removed;
    }

    public T peek() {
        return ll.head.getData();
    }

    public boolean isEmpty() {
        return ll.isEmpty();
    }
    public long size() {
        return ll.size();
    }

}
