package com.lwu.week2.sort;

/**
 * Entries to the left of pivot (including pivot) are in ascending order.
 * Entries to the right of pivot have not yet been seen.
 *
 * Best case: Array is ascending order, it makes N-1 compares and 0 exchanges.
 * Worst case; Array is descending order, it makes 1/2 N^2 compares and 1/2 N^2 exchanges.
 * */
public class InsertionSort {
    private void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j-1])) {
                    exch(a, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
