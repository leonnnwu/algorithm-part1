package com.lwu.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final static double CONFIDENCE_VALUE = 1.96;
    private final double[] threshold;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int n, int trials) {

        if (n < 1) {
            throw new IllegalArgumentException("n should be a number larger than 0");
        }

        if (trials < 1) {
            throw new IllegalArgumentException("trails should be a number larger than 0");
        }

        threshold = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(1, n+1);
                int randomCol = StdRandom.uniform(1, n+1);

                while (percolation.isOpen(randomRow, randomCol)) {
                    randomRow = StdRandom.uniform(1, n+1);
                    randomCol = StdRandom.uniform(1, n+1);
                }

                percolation.open(randomRow, randomCol);
            }

            threshold[i] = percolation.numberOfOpenSites() / (double) (n * n);
        }

        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
        confidenceLo = mean() - CONFIDENCE_VALUE * stddev() / Math.sqrt(threshold.length);
        confidenceHi = mean() + CONFIDENCE_VALUE * stddev() / Math.sqrt(threshold.length);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }

    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(length, trials);
        StdOut.println("mean                    = " + percolationStats.mean());
        StdOut.println("stddev                    = " + percolationStats.stddev());
        StdOut.println("95%% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }
}