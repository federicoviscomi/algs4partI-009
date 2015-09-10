package algs;

import org.jetbrains.annotations.NotNull;

class QuickUnionBySize {

    private static final String FORMAT_STRING = "%2.2s ";

    @NotNull
    private final int[] id;

    @NotNull
    private final int[] sizes;

    public QuickUnionBySize(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        sizes = new int[n];
        for (int i = 0; i < n; i++) {
            sizes[i] = 1;
        }
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }
 
    public QuickUnionBySize(int[] id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        if (containsCycle()) {
            throw new IllegalArgumentException("contains cycles");
        }
        sizes = new int[id.length];
        for (int i = 0; i < sizes.length; i++) {
           sizes[i] = 0;
           int iRoot = getRoot(i);
           for (int j = 0; j < sizes.length; j++) {
               if (iRoot == getRoot(j)) {
                   sizes[i]++;
               }
           }
        }
    }

    public int getMaxSize() {
        int max = 0;
        for (int i = 0; i < id.length; i++) {
            if (sizes[i] > max) {
                max = sizes[i];
            }
        }
        return max;
    }

    public int getDistanceToRoot(int p) {
        int distanceToRoot = 0;
        while (p != id[p]) {
            distanceToRoot++;
            p = id[p];
        }
        return distanceToRoot;
    }

    public int getMaxHeight() {
        int max = 0;
        for (int i = 0; i < id.length; i++) {
            int distanceToRoot = getDistanceToRoot(i);
            if (distanceToRoot > max) {
                max = distanceToRoot;
            }
        }
        return max;
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
        int firstSize = sizes[firstRoot];
        int secondSize = sizes[secondRoot];
        if (firstSize < secondSize) {
            id[firstRoot] = secondRoot;
            sizes[secondRoot] += sizes[firstRoot];
        } else {
            id[secondRoot] = firstRoot;
            sizes[firstRoot] += sizes[secondRoot];
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

    private int getRootWithCycleCheck(int p) {
        int root = p;
        boolean visited[] = new boolean[id.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        visited[root] = true;
        while (root != id[root]) {
            root = id[root];
            if (visited[root]) {
                return -1;
            }
            visited[root] = true;
        }
        return root;    
    }

    public boolean containsCycle() {
        for (int i = 0; i < id.length; i++) {
            if (getRooyWithCycleCheck(i) == -1) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(QuickUnionBySize.class.getCanonicalName());
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
        stringBuilder.append(String.format("%6.6s", "sizes"));
        for (int size : sizes) {
            stringBuilder.append(String.format(FORMAT_STRING, size));
        }
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

}
