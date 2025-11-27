package Algorithm.Other;

//@SuppressWarnings("unchecked")
public class HeapUtil {
    public static int[] heapifyMaxH(int[] array) {
        int N = array.length;
        int[] arr = new int[N + 1];

        System.arraycopy(array, 0, arr, 1, N);

        for (int i = N / 2; i > 0; i--) {
            int prev_i = i;
            while (2 * i <= N) {
                int j = 2 * i;
                if (j < N && arr[j] < arr[j + 1]) j++;
                if (arr[i] > arr[j]) break;
                // exchange
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                i = j;
            }
            i = prev_i;
        }
        return arr;
    }

    public static int[] HeapSort(int[] arr) {
        return new int[]{0};
    }
}
