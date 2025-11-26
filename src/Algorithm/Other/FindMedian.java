package Algorithm.Other;

import java.util.List;

public class FindMedian {
    public static <T extends Comparable<T>> int partition(List<T> list, int begin, int end) {
        T pivot = list.get(end);
        int partitionIndex = begin - 1;

        for (int i=begin; i<=end; i++) {
            if (list.get(i).compareTo(pivot) > 0) continue;

            partitionIndex++;
            if (partitionIndex == i) continue;
            // swap
            T temp = list.get(partitionIndex);
            list.set(partitionIndex, list.get(i));
            list.set(i, temp);
        }
        return partitionIndex;
    }

    public static <T extends Comparable<T>> T findMedian(List<T> list, int begin, int end) {
        assert list.size()%2 != 0;
        int target = (list.size()-1)/2;
        int medianIndex = partition(list, begin, end);

        if (medianIndex < target)
            return findMedian(list, medianIndex+1, end);
        else if (medianIndex > target)
            return findMedian(list, begin, medianIndex-1);
        // else,if equals target
        return list.get(medianIndex);
    }
}