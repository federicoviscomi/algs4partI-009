import java.util.Arrays;
import java.util.Random;

public class PercolationStats {


    private final int[] numberOfSitesOpenedAtExperiment;
    private final int n;
    private final int numberOfExperiments;
    private double totalNumberOfOpenedSites;
    private double totalNumberOfSites;

    // perform numberOfExperiments independent experiments on an n-by-n grid
    public PercolationStats(int n, int numberOfExperiments) {
        if (n <= 0 || numberOfExperiments <= 0) {
            throw new IllegalArgumentException();
        }
        this.totalNumberOfSites = n * n;
        this.n = n;
        this.numberOfExperiments = numberOfExperiments;
        this.totalNumberOfOpenedSites = 0;
        this.numberOfSitesOpenedAtExperiment = new int[numberOfExperiments];
        for (int i = 0; i < numberOfExperiments; i++) {
            numberOfSitesOpenedAtExperiment[i] = 0;
            experiment(i);
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            throw new IllegalArgumentException();
        }
        System.out.println(Arrays.toString(args));
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.format("\n%25.25s=%10.10s" +
                "\n%25.25s=%10.10s" +
                "\n%25.25s=%10.10s, %10.10s", "mean", percolationStats.mean(),
                "stddev", percolationStats.stddev(),
                "95% confidence interval", percolationStats.confidenceLo(),
                percolationStats.confidenceHi());
    }

    private void experiment(int experimentIndex) {
        Percolation percolation = new Percolation(n);
        Random random = new Random(System.currentTimeMillis());
        while (!percolation.percolates()) {
            //System.out.println(percolation);
            int row = random.nextInt(n) + 1;
            int col = random.nextInt(n) + 1;
            //System.out.format("\nopen(%d, %d)\n", row, col);
            if (!percolation.isOpen(row, col)) {
                numberOfSitesOpenedAtExperiment[experimentIndex]++;
                totalNumberOfOpenedSites++;
                percolation.open(row, col);
            }
        }
        //System.out.println(percolation);
    }

    // sample mean of percolation threshold
    public double mean() {
        return totalNumberOfOpenedSites / (totalNumberOfSites * numberOfExperiments);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double standardDeviation = 0;
        double mean = this.mean();
        for (int i = 0; i < numberOfExperiments; i++) {
            double stddevAtI = (numberOfSitesOpenedAtExperiment[i] / totalNumberOfSites) - mean;
            standardDeviation = standardDeviation + (stddevAtI * stddevAtI);
        }
        standardDeviation = standardDeviation / (numberOfExperiments - 1);
        return Math.sqrt(standardDeviation);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - ((1.96 * this.stddev()) / (Math.sqrt(numberOfExperiments)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + ((1.96 * this.stddev()) / (Math.sqrt(numberOfExperiments)));
    }
}
