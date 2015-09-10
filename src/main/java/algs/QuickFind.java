package algs;

import org.jetbrains.annotations.NotNull;

public class QuickFind {

    private static final String FORMAT_STRING = "%2.2s ";

    @NotNull
    private final int[] id;

    public QuickFind(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public static void main(String[] args) {
        QuickFind uf = new QuickFind(10);
        int[][] input = new int[][]{
                new int[]{4, 3},
                new int[]{3, 8},
                new int[]{6, 5},
                new int[]{9, 4},
                new int[]{2, 1},
                new int[]{8, 9},
                new int[]{5, 0},
                new int[]{7, 2},
                new int[]{6, 1},
                new int[]{1, 0},
                new int[]{6, 7}
        };
        for (int[] inputPair : input) {
            System.out.println("\n\n");
            System.out.println(uf);
            if (!uf.connected(inputPair[0], inputPair[1])) {
                uf.union(inputPair[0], inputPair[1]);
                System.out.println(uf);
                assert uf.connected(inputPair[0], inputPair[1]);
                assert uf.connected(inputPair[1], inputPair[0]);
            }
        }
        System.out.println(uf);
    }

    public void union(int p, int q) {
        System.out.println(String.format("union(%d, %d)", p, q));
        if (connected(p, q)) {
            return;
        }
        int idp = id[p];
        int idq = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == idp) {
                id[i] = idq;
            }
        }
    }

    public boolean connected(int p, int q) {
        System.out.println(String.format("connected(%d, %d)?", p, q));
        checkBounds(p);
        checkBounds(q);
        return id[p] == id[q];
    }

    private void checkBounds(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException();
        }
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{ UF\n");
        for (int i = 0; i < id.length; i++) {
            stringBuilder.append("");
            stringBuilder.append(String.format(FORMAT_STRING, i));
        }
        stringBuilder.append("\n");
        for (int anId : id) {
            stringBuilder.append(String.format(FORMAT_STRING, anId));
        }
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public int size() {
        return id.length;
    }
}
