package algs;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickUnionSimpleOnePassPathCompressionTest {

    @Test
    public void testQuickUnionGetMaxDepth() {
        QuickUnionSimpleOnePassPathCompression uf = new QuickUnionSimpleOnePassPathCompression(8);
        int[][] input = new int[][]{
                new int[]{0, 1},
                new int[]{2, 3},
                new int[]{4, 5},
                new int[]{6, 7},

                new int[]{0, 2},
                new int[]{4, 6},

                new int[]{0, 4},
        };
        System.out.println("\n\n");
        for (int[] inputPair : input) {
            System.out.println("\n\n\n\n");
            System.out.println("before union");
            System.out.println(uf);
            if (!uf.connected(inputPair[0], inputPair[1])) {
                uf.union(inputPair[0], inputPair[1]);
                System.out.println("after union");
                System.out.println(uf);
                assertTrue(uf.connected(inputPair[0], inputPair[1]));
                assertTrue(uf.connected(inputPair[1], inputPair[0]));
            }
        }
    }


    @Test
    public void testQuickUnion() {
        QuickUnionSimpleOnePassPathCompression uf = new QuickUnionSimpleOnePassPathCompression(10);
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
        System.out.println("\n\n");
        for (int[] inputPair : input) {
            System.out.println("\n\n\n\n");
            System.out.println("before union");
            System.out.println(uf);
            if (!uf.connected(inputPair[0], inputPair[1])) {
                uf.union(inputPair[0], inputPair[1]);
                System.out.println("after union");
                System.out.println(uf);
                assertTrue(uf.connected(inputPair[0], inputPair[1]));
                assertTrue(uf.connected(inputPair[1], inputPair[0]));
            }
        }
    }

    @Test
    public void testEmptyAllNotConnected() {
        QuickUnionSimpleOnePassPathCompression qf = new QuickUnionSimpleOnePassPathCompression(10);
        for (int i = 0; i < qf.size(); i++) {
            for (int j = 0; j < qf.size(); j++) {
                if (i != j) {
                    assertFalse(qf.connected(i, j));
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfBoundConstructor() {
        new QuickUnionSimpleOnePassPathCompression(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfBoundUnion() {
        QuickUnionSimpleOnePassPathCompression qf = new QuickUnionSimpleOnePassPathCompression(10);
        qf.union(0, qf.size());
    }

    // TODO
    public void testPartitions() {
        QuickUnionSimpleOnePassPathCompression uf = new QuickUnionSimpleOnePassPathCompression(10);
        int[][] input = new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{3, 4},
                new int[]{5, 6},
                new int[]{6, 7},
                new int[]{7, 8},
        };
        for (int[] inputPair : input) {
            uf.union(inputPair[0], inputPair[1]);
        }
        System.out.println(uf);
        int[][] partitions = uf.getPartitions();
        int[][] expected = new int[][]{
                new int[]{0, 1, 2},
                new int[]{3, 4},
                new int[]{5, 6, 7, 8},
                new int[]{9}
        };

        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], partitions[i]);
        }
    }
}
