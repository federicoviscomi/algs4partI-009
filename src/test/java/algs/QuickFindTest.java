package algs;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuickFindTest {

    @Test
    public void testWeek1AssessmentUnionFindQuestion1Seed86428() {
        QuickFind qf = new QuickFind(10);
        int[][] input = new int[][]{
                new int[]{7, 3},
                new int[]{9, 1},
                new int[]{7, 0},
                new int[]{3, 1},
                new int[]{2, 7},
                new int[]{6, 0},
        };
        for (int[] inputPair : input) {
            qf.union(inputPair[0], inputPair[1]);
        }
        System.out.println(qf);
        assertArrayEquals(new int[]{1, 1, 1, 1, 4, 5, 1, 1, 8, 1}, qf.id);
    }

    @Test
    public void testWeek1AssessmentUnionFindQuestion1Seed918325() {
        //TODO
        QuickFind qf = new QuickFind(10);
        int[][] input = new int[][]{
                new int[]{4, 9},
                new int[]{8, 2},
                new int[]{9, 6},
                new int[]{6, 0},
                new int[]{9, 2},
                new int[]{8, 5},
        };
        for (int[] inputPair : input) {
            qf.union(inputPair[0], inputPair[1]);
        }
        System.out.println(qf);
    }

    @Test
    public void testQuickFind() {
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
                assertTrue(uf.connected(inputPair[0], inputPair[1]));
                assertTrue(uf.connected(inputPair[1], inputPair[0]));
            }
        }
        System.out.println(uf);
    }

    @Test
    public void testEmptyAllNotConnected() {
        QuickFind qf = new QuickFind(10);
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
        new QuickFind(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfBoundUnion() {
        QuickFind qf = new QuickFind(10);
        qf.union(0, qf.size());
    }

}
