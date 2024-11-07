import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* Performs computational experiments to estimate percolation threshold */ 
public class PercolationStats {
    private int trials;
    private double[] thresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n<=0 || trials<=0){
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        thresholds = new double[trials];

        for (int i=0; i<trials; i++){
            Percolation percolation = new Percolation(n);
            int openSites = 0;

            while (!percolation.percolates()){
                int row = StdRandom.uniformInt(1, n+1);
                int col = StdRandom.uniformInt(1, n+1);
                if (!percolation.isOpen(row, col)){
                    percolation.open(row, col);
                    openSites++;
                }
            }
            thresholds[i] = (double) openSites/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96*stddev() / (trials^(1/2)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96*stddev() / (trials^(1/2)));
    }

   // test client (see below)
   public static void main(String[] args){
        if (args.length !=2){
            throw new IllegalArgumentException();
        }

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.printf("mean                    = %.16f%n", stats.mean());
        StdOut.printf("stddev                  = %.16f%n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%.16f, %.16f]%n", stats.confidenceLo(), stats.confidenceHi());
   }
}
