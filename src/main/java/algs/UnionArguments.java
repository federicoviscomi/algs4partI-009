package algs;

public class UnionArguments {
    private final int first;
    private final int second;

    public UnionArguments(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("union(").
                append(first).append(", ").append(second)
                .append(')');
        return sb.toString();
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}
