package com.lwu.week2.sort;

/**
 * Move entries more than one position at a time by h-sorting
 * the array.
 *
 * Insertion sort, with stride length h.
 *
 * Step 1: Initialize the value of h.
 * Step 2: Divide the list into smaller sub-list of equal interval h
 * Step 3: Sort these sub-lists using insertion sort
 * Repeat until complete list is sorted.
 */
public class ShellSort {

    private void sort(Comparable[] a) {
        int N = a.length;

        int h = 1;
        while (h < N/3) {
            h = 3*h + 1;
        }

        while (h >= 1) {
            for (int i=h; i<N; i++) {
                for (int j=i; j>=h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
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
