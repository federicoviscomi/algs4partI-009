package algs;

import org.jetbrains.annotations.NotNull;

public class QuickSuccessorWithDelete {

    private static final String FORMAT_STRING = "%2.2s ";

    @NotNull
    private final int[] successors;
    @NotNull
    private final int[] predecessors;
    @NotNull
    private final boolean[] isDeleted;

    public QuickSuccessorWithDelete(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        successors = new int[n];
        predecessors = new int[n];
        isDeleted = new boolean[n];
        for (int i = 0; i < n; i++) {
            successors[i] = i + 1;
            predecessors[i] = i - 1;
            isDeleted[i] = false;
        }
    }

    public int getSuccessor(int p) {
        if (!isDeleted[p]) {
            return p;
        }
        return successors[p];
    }

    public int getPredecessor(int p) {
        if (!isDeleted[p]) {
            return p;
        }
        return predecessors[p];
    }

    public void delete(int p) {
        if (isDeleted[p]) {
            return;
        }
        // TODO
    }

    public boolean isDeleted(int p) {
        return isDeleted[p];
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QuickSuccessorWithDelete");
        sb.append("\n\t");
        sb.append(String.format("%15.15s", "index "));
        for (int i = 0; i < successors.length; ++i) {
            sb.append(String.format("%4.4s", i));
        }
        sb.append("\n\t");
        sb.append(String.format("%15.15s", "successor "));
        for (int i = 0; i < successors.length; ++i) {
            sb.append(String.format("%4.4s", successors[i]));
        }
        sb.append("\n\t");
        sb.append(String.format("%15.15s", "predecessor "));
        for (int i = 0; i < predecessors.length; ++i) {
            sb.append(String.format("%4.4s", predecessors[i]));
        }
        sb.append("\n");
        return sb.toString();
    }

}
