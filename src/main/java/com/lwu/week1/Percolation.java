package com.lwu.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] flag;
    private final int gridLength;
    private int openCount;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final WeightedQuickUnionUF weightedQuickUnionUFBackWash;

    public Percolation(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n should be a number larger than 0");
        }

        gridLength = n;
        flag = new boolean[n*n];
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n);
        weightedQuickUnionUFBackWash = new WeightedQuickUnionUF(n*n);

        for (int i = 1; i < gridLength; i++) {
            weightedQuickUnionUF.union(0, i);
            weightedQuickUnionUFBackWash.union(0, i);
        }

        for (int i = n*(n-1); i < n*n-1; i++) {
            weightedQuickUnionUF.union(i, n*n-1);
        }
    }

    public void open(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }

        flag[index(row, col)] = true;
        openCount++;

        if (validate(row-1, col) && isOpen(row-1, col)) {
            weightedQuickUnionUF.union(index(row, col), index(row-1, col));
            weightedQuickUnionUFBackWash.union(index(row, col), index(row-1, col));
        }

        if (validate(row+1, col) && isOpen(row+1, col)) {
            weightedQuickUnionUF.union(index(row, col), index(row+1, col));
            weightedQuickUnionUFBackWash.union(index(row, col), index(row+1, col));
        }

        if (validate(row, col-1) && isOpen(row, col-1)) {
            weightedQuickUnionUF.union(index(row, col), index(row, col-1));
            weightedQuickUnionUFBackWash.union(index(row, col), index(row, col-1));
        }

        if (validate(row, col+1) && isOpen(row, col+1)) {
            weightedQuickUnionUF.union(index(row, col), index(row, col+1));
            weightedQuickUnionUFBackWash.union(index(row, col), index(row, col+1));
        }
    }

    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        return flag[index(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }

        return isOpen(row, col) && weightedQuickUnionUFBackWash.connected(0, index(row, col));
    }

    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, gridLength*gridLength-1) && openCount != 0;
    }

    private int index(int row, int col) {
        return (row-1)*gridLength + (col-1);
    }

    private boolean validate(int row, int col) {
        if (row < 1 || row > gridLength || col < 1 || col > gridLength) {
            return false;
        }

        return true;
    }

}