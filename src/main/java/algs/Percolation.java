package algs;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final boolean[][] open;
    private final int virtualTop;
    private final int virtualBottom;
    private final int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        int size = n * n + 2;
        this.virtualTop = size - 1;
        this.virtualBottom = size - 2;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(size);
        this.open = new boolean[n][n];
        for (boolean[] anOpen : open) {
            Arrays.fill(anOpen, false);
        }
    }

    public boolean isOpen(int row, int col) {
        row--;
        col--;
        //checkMatrixBounds(row, col);
        return open[row][col];
    }

    public boolean isFull(int row, int col) {
        row--;
        col--;
        //checkMatrixBounds(row, col);
        return weightedQuickUnionUF.connected(matrixToLinear(row, col), virtualTop);
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(virtualTop, virtualBottom);
    }

    public void open(int row, int col) {
        row--;
        col--;
        // checkMatrixBounds(row, col);
        if (open[row][col]) {
            return;
        }
        open[row][col] = true;
        int linearIndex = matrixToLinear(row, col);
        if (row == 0) { // top
            weightedQuickUnionUF.union(linearIndex, virtualTop);
        }
        if (row == n - 1) { // bottom
            weightedQuickUnionUF.union(linearIndex, virtualBottom);
        }
        int neighbours[][] = new int[][]{
                new int[]{row - 1, col},
                new int[]{row + 1, col},
                new int[]{row, col - 1},
                new int[]{row, col + 1}
        };
        for (int[] neighbour : neighbours) {
            if (isWithinMatrixBounds(neighbour[0], neighbour[1])) {
                if (open[neighbour[0]][neighbour[1]]) {
                    weightedQuickUnionUF.union(linearIndex, matrixToLinear(neighbour[0], neighbour[1]));
                }
            }
        }
    }

    private int matrixToLinear(int row, int col) {
        return (row * n) + col;
    }

   /* private void checkMatrixBounds(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IllegalArgumentException();
        }
    }*/

    private boolean isWithinMatrixBounds(int row, int col) {
        return !(row < 0 || row >= open.length || col < 0 || col >= open.length);
    }

   /* @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n\n\n");
        sb.append("percolates?");
        sb.append(this.percolates());
        sb.append("\n");
        sb.append(String.format("%3.3s", ""));
        for (int col = 1; col <= n; col++) {
            sb.append(String.format("%3.3s", col));
        }
        for (int row = 1; row <= n; row++) {
            sb.append("\n");
            sb.append(String.format("%3.3s", row));
            for (int col = 1; col <= n; col++) {
                if (this.isFull(row, col)) {
                    sb.append(String.format("%3.3s", "|"));
                } else if (this.isOpen(row, col)) {
                    sb.append(String.format("%3.3s", " "));
                } else {
                    sb.append(String.format("%3.3s", "*"));
                }
            }
        }
        return sb.toString();
    }*/
}


