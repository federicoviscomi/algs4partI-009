package algs;

import java.lang.StringBuilder;

public class QuickUnion {

  private static final String FORMAT_STRING = "%2.2s ";

  private final int[] id;

  public QuickUnion(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
  }

  private int getRoot(int p) {
    int root = p;
    while (root != id[root]) {
      root = id[root];
    }
    return root;
  }

  private void setRoot(int p, int newRoot) {
    int root = getRoot(p);
    id[root] = newRoot;
  }

  public void union(int p, int q) {
    System.out.println(String.format("union(%d, %d)", p, q));
    checkBounds(p);
    checkBounds(q);
    int pRoot = getRoot(p);
    setRoot(q, pRoot);
  }

  public boolean connected(int p, int q) {
    System.out.println(String.format("connected(%d, %d)?", p, q));
    checkBounds(p);
    checkBounds(q);
    return getRoot(p) == getRoot(q);
  }

  private void checkBounds(int p) {
    if (p < 0 || p >= id.length) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{ UF\n");
    for (int i = 0; i < id.length; i++) {
      stringBuilder.append("");
      stringBuilder.append(String.format(FORMAT_STRING, i));
    }
    stringBuilder.append("\n");
    for (int i = 0; i < id.length; i++) {
      stringBuilder.append(String.format(FORMAT_STRING, id[i]));
    }
    stringBuilder.append("\n}");
    return stringBuilder.toString();
  }
 
  public int size() {
    return id.length;
  }
}
