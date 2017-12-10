package com.lwu.week2.sort;

/**
 * Invariants
 *  - Entries the left of pivot (including pivot) fixed and in ascending order.
 *  - No entry to right of pivot is smaller than any entry to the left of pivot.
 */
public class SelectionSort {

    public void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = min+1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }

            if (min != i) {
                exch(a, i, min);
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
