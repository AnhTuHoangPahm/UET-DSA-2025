package Algorithm.Sort;

import java.util.List;

public class QuickSort {
    /**
     * Sort a primitive-Type array with Merge Sort.<p>
     * Time: O(nlogn)
     * @param list needs to be sorted.<\p>
     */
    public static <T extends Comparable<T>> void quickSort(List<T> list, int begin, int end) {
        if (end <= begin) return;

        int swapIndex = partition(list, begin, end);

        quickSort(list, begin, swapIndex-1);
        quickSort(list, swapIndex+1, end);
    }

    private static <T extends Comparable<T>> int partition(List<T> list, int begin, int end) {
        T pivot = list.get(end);
        int swapIndex = begin - 1;
        for (int i=begin; i<=end; i++) {
            if (list.get(i).compareTo(pivot) > 0) continue;

            swapIndex++;
            if (i == swapIndex) continue;
            // swap
            T temp = list.get(swapIndex);
            list.set(swapIndex, list.get(i));
            list.set(i, temp);
        }
        return swapIndex;
    }
}