package algs;

import algs.exception.ContainsCycleException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickUnionBySize {

    public static final String FORMAT_STRING_FIRST_COL = "%8.8s";
    private static final String FORMAT_STRING = "%3.3s ";
    private static final int SIZE_INVARIANCT_HOLD = -1;
    private static final int HEIGHT_INVARIANT_HOLD = -1;
    @NotNull
    final int[] id;

    @NotNull
    final int[] sizes;

    private int componentsCount;

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
        componentsCount = n;
    }

    public QuickUnionBySize(int[] id) throws ContainsCycleException {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        this.id = id;
        if (containsCycle()) {
            throw new ContainsCycleException();
        }
        componentsCount = countComponents();
        sizes = new int[id.length];
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = computeSizeOfTreeRootedAt(i);
        }
/*
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 0;
            int iRoot = getRoot(i);
            if (iRoot == i) {
                for (int j = 0; j < sizes.length; j++) {
                    if (iRoot == getRoot(j)) {
                        sizes[i]++;
                    }
                }
            }
        }
*/
    }

    public static void applyUnionSequence(QuickUnionBySize qubs, int[] id) {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < id.length; i++) {
            qubs.union(i, id[i]);
        }
    }

    private int countComponents() {
        int cc = 0;
        for (int i = 0; i < id.length; i++) {
            if (isRoot(i)) {
                cc++;
            }
        }
        return cc;
    }

    private int computeSizeOfTreeRootedAt(int p) {
        checkBounds(p);
        if (isLeaf(p)) {
            return 1;
        }
        ArrayList<Integer> childs = getChilds(p);
        int size = 1;
        for (int c : childs) {
            size = size + computeSizeOfTreeRootedAt(c);
        }
        return size;
    }

    private ArrayList<Integer> getChilds(int p) {
        ArrayList<Integer> childs = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            if (i != p && id[i] == p) {
                childs.add(i);
            }
        }
        return childs;
    }

    private boolean isLeaf(int p) {
        for (int i = 0; i < id.length; i++) {
            if (i != p && id[i] == p) {
                return false;
            }
        }
        return true;
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
        componentsCount--;
    }

    public int count() {
        return componentsCount;
    }

    public boolean connected(int p, int q) {
        // System.out.println(String.format("connected(%d, %d)?", p, q));
        checkBounds(p);
        checkBounds(q);
        return getRoot(p) == getRoot(q);
    }

    public ArrayList[] getPartitions() {
        ArrayList<Integer>[] partitions = new ArrayList[id.length];
        for (int i = 0; i < id.length; i++) {
            if (isRoot(i)) {
                partitions[i] = new ArrayList<>(sizes[i]);
            } else {
                partitions[i] = null;
            }
        }
        for (int i = 0; i < id.length; i++) {
            partitions[getRoot(i)].add(i);
        }
        return partitions;
    }

    private boolean isRoot(int p) {
        checkBounds(p);
        return id[p] == p;
    }

    public int size() {
        return id.length;
    }

    private void checkBounds(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException();
        }
    }

    int getRoot(int p) {
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        return root;
    }

    private int getRootWithCycleCheck(int p) {
        boolean visited[] = new boolean[id.length];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        int root = p;
        for (; root != id[root]; root = id[root]) {
            if (visited[root]) {
                System.out.format("\n cycle found at index %d ", root);
                System.out.format("\nvisited=%s\n", java.util.Arrays.toString(visited));
                return -1;
            }
            visited[root] = true;
        }
        return root;
    }

    public boolean containsCycle() {
        for (int i = 0; i < id.length; i++) {
            if (getRootWithCycleCheck(i) == -1) {
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
        stringBuilder.append(String.format(FORMAT_STRING_FIRST_COL, "index"));
        for (int i = 0; i < id.length; i++) {
            stringBuilder.append("");
            stringBuilder.append(String.format(FORMAT_STRING, i));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format(FORMAT_STRING_FIRST_COL, "parent"));
        for (int anId : id) {
            stringBuilder.append(String.format(FORMAT_STRING, anId));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format(FORMAT_STRING_FIRST_COL, "size"));
        for (int size : sizes) {
            stringBuilder.append(String.format(FORMAT_STRING, size));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format(FORMAT_STRING_FIRST_COL, "root"));
        for (int i = 0; i < id.length; i++) {
            stringBuilder.append(String.format(FORMAT_STRING, getRoot(i)));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format(FORMAT_STRING_FIRST_COL, "depth"));
        for (int i = 0; i < id.length; i++) {
            stringBuilder.append(String.format(FORMAT_STRING, getDistanceToRoot(i)));
        }
        stringBuilder.append("\n");
        stringBuilder.append(String.format("height is %d", getMaxHeight()));
        stringBuilder.append("\n");
        stringBuilder.append(String.format("max component size is %d", getMaxSize()));
        stringBuilder.append("\n");
        stringBuilder.append(String.format("component count is %d", count()));
        stringBuilder.append("\n");
        int heightInvariantViolationIndex = this.getHeightInvariantViolationIndex();
        if (heightInvariantViolationIndex == QuickUnionBySize.HEIGHT_INVARIANT_HOLD) {
            stringBuilder.append(String.format("height invariant holds"));
        } else {
            stringBuilder.append(String.format("height invariant does not hold at %d", heightInvariantViolationIndex));
        }
        stringBuilder.append("\n");
        int sizeInvariantViolationIndex = this.getSizeInvariantViolationIndex();
        if (sizeInvariantViolationIndex == QuickUnionBySize.SIZE_INVARIANCT_HOLD) {
            stringBuilder.append(String.format("size invariant holds"));
        } else {
            stringBuilder.append(String.format("size invariant does not hold at %d", sizeInvariantViolationIndex));
        }
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    public int getHeightInvariantViolationIndex() {
        int maxHeight = 0;
        int maxHeightIndex = 0;
        for (int i = 0; i < id.length; i++) {
            int distanceToRoot = getDistanceToRoot(i);
            if (distanceToRoot > maxHeight) {
                maxHeight = distanceToRoot;
                maxHeightIndex = i;
            }
        }
        double heightLimit = (Math.log(id.length) / Math.log(2));
        if (maxHeight <= heightLimit) {
            return -1;
        } else {
            return maxHeightIndex;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuickUnionBySize)) return false;

        QuickUnionBySize that = (QuickUnionBySize) o;

        if (componentsCount != that.componentsCount) return false;
        if (!Arrays.equals(id, that.id)) return false;
        return Arrays.equals(sizes, that.sizes);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(id);
        result = 31 * result + Arrays.hashCode(sizes);
        result = 31 * result + componentsCount;
        return result;
    }

    /**
     *
     * @return -1 if size invariant hold;
     */
    public int getSizeInvariantViolationIndex() {
        for (int i = 0; i < id.length; i++) {
            if (!isRoot(i) && sizes[id[i]] < 2 * sizes[i]) {
                System.out.format("\nSize of tree rooted at parent of %d" +
                        " is less than twice the size of tree rooted at %d\n", i, i);
                return i;
            }
        }
        return -1;
    }

    public boolean heightInvariantViolationHold() {
        return getHeightInvariantViolationIndex() == HEIGHT_INVARIANT_HOLD;
    }


    /*
    public ArrayList<UnionArguments> divideAll() {
        ArrayList<UnionArguments> reverseUnionList = new ArrayList<>();
        while (componentsCount < successors.length) {
            divideOneStep(reverseUnionList);
        }
        return reverseUnionList;
    }
    private void divideOneStep(ArrayList<UnionArguments> reverseUnionList) {
        int firstRootNonLeaf = 0;
        while (firstRootNonLeaf < successors.length) {
            if (isRoot(firstRootNonLeaf) && !isLeaf(firstRootNonLeaf)) {
                ArrayList<Integer> children = getChilds(firstRootNonLeaf);
                children.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return sizes[o2] - sizes[o1];
                    }
                });
                reverseUnionList.add(new UnionArguments(firstRootNonLeaf, children.get(children.size() - 1)));
                this.divide(children.get(children.size() - 1), firstRootNonLeaf);
                componentsCount++;
                return;
            } else {
                firstRootNonLeaf++;
            }
        }

    }

    public void divide(int p, int pRoot) {
        checkBounds(p);
        checkBounds(pRoot);
        if (!this.connected(p, pRoot) || p == pRoot || getRoot(p) != pRoot) {
            throw new IllegalArgumentException();
        }
        successors[p] = p;
        sizes[pRoot] = sizes[pRoot] - sizes[p];
    }*/
}
