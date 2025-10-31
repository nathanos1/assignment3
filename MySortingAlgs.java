package sorting;

import java.util.Arrays;
import java.util.Stack;

public class MySortingAlgs {

    public static void sortStrings(String[] a) {
        if (a == null || a.length <= 1) return;
        Arrays.sort(a, (s1, s2) -> {
            if (s1.length() != s2.length()) return Integer.compare(s1.length(), s2.length());
            return s1.compareTo(s2);
        });
    }


	/**
	 * Assumption arr[start...mid-1] is sorted and arr[mid...end] is sorted.
     * The function merges the two parts into a sorted subarray arr[start...end]
     * All elements outside arr[start...end] remain unchanged
	 */
	public static <T extends Comparable<T>> void merge(T[] arr, int start, int mid, int end) {
        // arr[start..mid-1] and arr[mid..end] are sorted
        if (arr == null || start > end || mid < start || mid > end + 1) return;

        int i = start;
        int j = mid;
        Object[] temp = new Object[end - start + 1];
        int k = 0;

        while (i < mid && j <= end) {
            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i < mid) temp[k++] = arr[i++];
        while (j <= end) temp[k++] = arr[j++];

        // copy back
        for (int t = 0; t < temp.length; t++) {
            @SuppressWarnings("unchecked")
            T val = (T) temp[t];
            arr[start + t] = val;
        }
	}


	/**
	 * sorts the subarray arr[start...end] using the Merge Sort algorithm
	 */
    public static <T extends Comparable<T>> void mergeSort(T[] arr, int start, int end) {
        if (start == end) return;

        // mid is the index where the right sorted subarray starts (contract used by merge)
        int mid = (start + end + 1) / 2;
        mergeSort(arr, start, mid - 1);
        mergeSort(arr, mid, end);
        merge(arr, start, mid, end);
    }

    /**
     * sorts the subarray arr using the Merge Sort algorithm
     */
    public static <T extends Comparable<T>> void mergeSort(T[] arr) {
        mergeSort(arr,0,arr.length-1);
    }

}
