package algs;

import org.jetbrains.annotations.NotNull;

class QuickUnionByHeight {

    private static final String FORMAT_STRING = "%2.2s ";

    @NotNull
    private final int[] id;

    @NotNull
    private final int[] depth;

    public QuickUnionByHeight(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        depth = new int[n];
        for (int i = 0; i < n; i++) {
            depth[i] = 0;
        }
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public void union(int first, int second) {
        // System.out.println(String.format("union(%d, %d)", first, second));
        checkBounds(first);
        checkBounds(second);
        if (first == second) {
            return;
        }
        int firstRoot = getRoot(first);
        int secondRoot = getRoot(second);
        if (firstRoot == secondRoot) {
            // they are already connected
            return;
        }
        int firstWeight = depth[firstRoot];
        int secondWeight = depth[secondRoot];
        if (firstWeight < secondWeight) {
            id[firstRoot] = secondRoot;
        } else if (firstWeight > secondWeight) {
            id[secondRoot] = firstRoot;
        } else {
            id[firstRoot] = secondRoot;
            depth[secondRoot]++;
        }
    }

    public boolean connected(int p, int q) {
        // System.out.println(String.format("connected(%d, %d)?", p, q));
        checkBounds(p);
        checkBounds(q);
        return getRoot(p) == getRoot(q);
    }

    public int[][] getPartitions() {
        // TODO
        return null;
    }

    public int size() {
        return id.length;
    }

    private void checkBounds(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException();
        }
    }

    private int getRoot(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        return root;
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QuickUnionByHeight.class.getCanonicalName());
        stringBuilder.append("@");
        stringBuilder.append(super.hashCode());
        stringBuilder.append(" { \n");
        stringBuilder.append(String.format("%6.6s", "index"));
        for (int i = 0; i < id.length; i++) {
            stringBuilder.append("");
            stringBuilder.append(String.format(FORMAT_STRING, i));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format("%6.6s", "parent"));
        for (int anId : id) {
            stringBuilder.append(String.format(FORMAT_STRING, anId));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format("%6.6s", "weight"));
        for (int weight : depth) {
            stringBuilder.append(String.format(FORMAT_STRING, weight));
        }
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

}
