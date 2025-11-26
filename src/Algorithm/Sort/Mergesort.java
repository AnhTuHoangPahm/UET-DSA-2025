package Algorithm.Sort;
import java.util.List;

public class Mergesort {
    public static <T extends Comparable<T>> void merge(List<T> arr, List<T> aux, int start, int mid, int end) {
        // auxiliary array keeps the original elements for later use
        for (int i=start; i<=end; i++) {
            aux.set(i, arr.get(i));
        }

        int i = start, j = mid+1;
        for (int k=start; k<=end; k++) {
            if (i>mid) // if out of element in first half, put the remaining in second half into arr
                arr.set(k, aux.get(j++));
            else if (j>end) // vice versa
                arr.set(k, aux.get(i++));
            else if (aux.get(i).compareTo(aux.get(j)) > 0)
                arr.set(k, aux.get(j++));
            else
                arr.set(k, aux.get(i++));
        }
    }

    /**
     * Sort a primitive-Type array with Merge Sort.<p>
     * Time: O(nlogn)
     * @param arr needs to be sorted
     */
    public static <T extends Comparable<T>> void mergeSort(List<T> arr, List<T> aux, int start, int end) {
        if (start >= end) return;
        if (end - start + 1 == 7) { // insertion sort for smol sub-arrays
            InsertionSort.insertionSort(arr);
            return;
        }
        int mid = start + (end - start)/2;
        // recursive calls
        mergeSort(arr, aux, start, mid);
        mergeSort(arr, aux,mid+1, end);
        // if 2 sub-arrays is in order, no merging is required
        if (arr.get(mid).compareTo(arr.get(mid+1)) <= 0) return;
        merge(arr, aux, start, mid, end);
    }
}
