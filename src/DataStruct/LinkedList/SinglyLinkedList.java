package DataStruct.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<T> implements Iterable<T> {
    public SNode<T> head;
    public SNode<T> tail;
    private long size = 0;

    public SinglyLinkedList() {
        head = null;
        tail = null;
    }

    public SinglyLinkedList(T data) { // linked list with initial data
        head = new SNode<>(data);
        tail = head;
        size++;
    }

    // Insertion
    //------------------------
    public void addFirst(T data) {
        final SNode<T> nfNode = new SNode<>(data);
        nfNode.next = head;
        if (head == null) // empty
            tail = nfNode;
        head = nfNode;
        size++;
    }

    public void addLast(T data) {
        if (head == null) { // empty
            addFirst(data);
            return;
        }

        final SNode<T> nlNode = new SNode<>(data);
        tail.next = nlNode;
        tail = nlNode;
        size++;
    }

    public void addBefore(T data, T newData) {
        if (head == null)
            return;
        if (head.data == data) {
            addFirst(newData);
            return;
        }

        SNode<T> iterator = head;
        while (iterator.next != null && !(iterator.next.data).equals(data)) {
            iterator = iterator.next;
        }

        if (iterator != tail) { // key is found
            final SNode<T> nNode = new SNode<>(newData);
            nNode.next = iterator.next;
            iterator.next = nNode;
            size++;
        }
    }

    public void addAfter(T data, T newData) {
        if (head == null) {
            addFirst(newData);
            return;
        }

        SNode<T> iterator = head;
        while (iterator != null && !(iterator.data).equals(data)) {
            iterator = iterator.next;
        }

        if (iterator != null) { // key is found
            final SNode<T> nNode = new SNode<>(newData);
            nNode.next = iterator.next;
            iterator.next = nNode;
            if (iterator == tail) tail = nNode;
            size++;
        }
    }

    /**
     * addAt node before position
     * @param position p (1 <= p <= size)
     */
    public void addAt(long position, T data) {
        if (position < 1 || position > size) {
            System.out.print("Can't insert value: " + data + ", invalid position: " + position);
            return;
        }
        if (position == 1) {
            addFirst(data);
            return;
        }
        if (position == size) {
            addLast(data);
            return;
        }

        SNode<T> iterator = head;
        for (int i = 1; i < position-1; i++) {
            iterator = iterator.next;
        }
        addAfter(iterator.data, data);
    }
    //----------------------------

    // Removal
    // ---------------------------
    public T removeFirst() {
        if (head == null) //empty
            return null;
        T removed = head.data;
        SNode<T> iterator = head;
        head = head.next;
        if (head == null) // only 1 data
            tail = null;
        iterator.next = null; // make old head refers to nothing -> get freed by garbage collector
        size--;
        return removed;
    }

    public T removeLast() {
        if (head == null) // empty
            return null;
        if (head.next == null) // only 1 data
            return removeFirst();
        T removed = tail.data;

        SNode<T> iterator = head;
        while (iterator.next!=tail) { // stops before tail
            iterator = iterator.next;
        }
        tail = iterator;
        tail.next = null;
        size--;
        return removed;
    }

    public void removeData(T data) {
        if (head.data == data) {
            removeFirst();
            return;
        }

        SNode<T> iterator = head;
        while(iterator.next != null && !(iterator.next.data).equals(data)) {
            iterator = iterator.next;
        }

        if (iterator == tail) // key not found
            return;

        if (iterator.next == tail) {
            removeLast();
            return;
        }
        // here, key is found, it.next can't be null
        assert iterator.next != null; // suppress warning
        iterator.next = iterator.next.next;
        size--;
    }
    //------------------------------

    public long size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    private SNode<T> findNode(T data) {
        for (SNode<T> iterator = head; iterator!=null; iterator = iterator.next) {
            if (iterator.data.equals(data)) return iterator;
        }
        return null;
    }

    public void deleteLL() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Delete Nodes, from certain Node containing data to end
     * @param data starting Node's data
     */
    public void deleteFrom(T data) {
        SNode<T> start = findNode(data);
        if (start == null) {
            System.out.println("Can't find Node: " + data);
            return;
        }

        if (start == head) {
            deleteLL();
            return;
        }
        if (start == tail) {
            removeLast();
            return;
        }

        while (start.next!=tail) {
            SNode<T> temp = start.next;
            start.next = start.next.next;
            temp.next = null;
            size--;
        } // start and tail remains
        removeLast();
        removeLast(); // clean up
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            SNode<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                T data = currentNode.data;
                if (data == null) return null;
                currentNode = currentNode.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
