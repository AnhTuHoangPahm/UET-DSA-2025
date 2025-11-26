package Algorithm.Sort;

import java.util.List;


public class InsertionSort {
    /**
     * Sort a primitive-Type array with Insertion Sort.<p>
     * Time: O(n^2)
     * @param arr needs to be sorted
     */
    public static <T extends Comparable<T>> void insertionSort(List<T> arr) {
        long n = arr.size();
        for(int i=1;i<n;i++) {
            T temp = arr.get(i);
            int j = i-1;
            while (j >= 0 && arr.get(j).compareTo(temp) > 0) {
                arr.set(j+1, arr.get(j));
                j--;
            }
            arr.set(j+1, temp);
        }
    }
}
