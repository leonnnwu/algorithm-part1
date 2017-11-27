package com.lwu.week1;

public class Percolation {

    private int[][] grid;
    private static final Integer FULL = 0;
    private static final Integer OPEN = 1;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        grid = new int[n][n];
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        grid[row-1][col-1] = 1;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row-1][col-1] == OPEN;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!validate(row, col)) {
            throw new IllegalArgumentException();
        }
        return grid[row-1][col-1] == FULL;
    }

    // number of open sites
    public int numberOfOpenSites() {
        int result = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                result += grid[i][j] == OPEN ? 1 : 0;
            }
        }

        return result;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i <= grid.length; i++) {
            if (percolates(1, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean validate(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid.length) {
            return false;
        }

        return true;
    }

    private boolean percolates(int row, int col) {

        if (!validate(row, col) || grid[row-1][col-1] == FULL) {
            return false;
        }

        if (row == grid.length && grid[row-1][col-1] == OPEN) {
            return true;
        }

        return percolates(row-1, col) || percolates(row, col-1) || percolates(row, col+1) || percolates(row+1, col);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}