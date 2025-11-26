package DataStruct.LinkedList;

public class SNode<T> {
    T data;
    public SNode<T> next;

    public SNode(T newData) {
        data = newData;
        next = null;
    }

    public T getData() {
        return data;
    }
}
