package Algorithm.Other;

import DataStruct.PriorityQueue.BinaryHeap;

//@SuppressWarnings("unchecked")
public class HeapUtil {
    public static BinaryHeap<Integer> heapifyMaxH(int[] arr) {
        int N = arr.length;
        BinaryHeap<Integer> bh = new BinaryHeap<>(N);
        for (int i : arr) {
            bh.add(i);
        }
        return bh;
    }

    // use a MaxHeap
    public static int[] HeapSort(int[] arr) {
        int N = arr.length;
        // first, heapify the array
        BinaryHeap<Integer> temp = heapifyMaxH(arr);
        // secondly, poll elements in turn & put into a new array
        int[] res = new int[N];
        for (int i = N-1; i >=0; i--) {
            res[i] = temp.poll();
        }
        return res;
    }
}
