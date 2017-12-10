package com.lwu.week2.sort;

import edu.princeton.cs.algs4.*;

public class Experiment2 {
    public static void main(String[] args) {
        In file = new In(args[0]);
        String[] a = file.readAllStrings();
        Insertion.sort(a);
        for (String str: a) {
            StdOut.println(str);
        }
    }
}
