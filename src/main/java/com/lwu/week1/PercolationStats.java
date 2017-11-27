package com.lwu.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Monte Carlo simulation. To estimate the percolation threshold, consider the following computational experiment:

 Initialize all sites to be blocked.
 Repeat the following until the system percolates:
 Choose a site uniformly at random among all blocked sites.
 Open the site.
 The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.
 */

public class PercolationStats {

    double[] threshold;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        threshold = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(1, n+1);
                int randomCol = StdRandom.uniform(1, n+1);

                while (!percolation.isFull(randomRow, randomCol)) {
                    randomRow = StdRandom.uniform(1, n+1);
                    randomCol = StdRandom.uniform(1, n+1);
                }

                StdOut.printf("open %d, %d", randomRow, randomCol);
                StdOut.println();
                percolation.open(randomRow, randomCol);
            }

            threshold[i] = percolation.numberOfOpenSites() / (double) (n * n);
            StdOut.printf("%dst experiment, the threshold is %f", i, threshold[i]);
        }
        StdOut.println();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(threshold.length);
    }

    // test client (described below)
    public static void main(String[] args) {
        StdOut.println("======Start==========");
        PercolationStats percolationStats = new PercolationStats(2, 10000);
        StdOut.printf("mean                    = %f", percolationStats.mean());
        StdOut.println();
        StdOut.printf("stddev                    = %f", percolationStats.stddev());
        StdOut.println();
        StdOut.printf("95%% confidence interval = [%f, %f]", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}