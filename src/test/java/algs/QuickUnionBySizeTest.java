package algs;

import algs.exception.ContainsCycleException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class QuickUnionBySizeTest {
    @Test
    public void testWeek1AssessmentUnionFindQuestion2Seed36342() {
        QuickUnionBySize qf = new QuickUnionBySize(10);
        int[][] input = new int[][]{
                new int[]{7, 8},
                new int[]{5, 9},
                new int[]{4, 1},
                new int[]{6, 1},
                new int[]{6, 0},
                new int[]{2, 0},
                new int[]{7, 5},
                new int[]{9, 6},
                new int[]{1, 3}
        };
        for (int[] inputPair : input) {
            qf.union(inputPair[0], inputPair[1]);
        }
        System.out.println(qf);
        assertArrayEquals(new int[]{4, 4, 4, 4, 4, 7, 4, 4, 7, 5}, qf.id);
    }


    @Test
    public void testWeek1AssessmentUnionFindQuestion3Seed510829Array1() throws ContainsCycleException {
        int[] id = new int[]{0, 8, 1, 0, 1, 7, 7, 8, 0, 1};
        System.out.println("\n\n\n");
        QuickUnionBySize qubs = new QuickUnionBySize(id);
        System.out.println(qubs);
        assertEquals(8, qubs.getSizeInvariantViolationIndex());
        System.out.println("\n\n");
    }

    @Test(expected = ContainsCycleException.class)
    public void testWeek1AssessmentUnionFindQuestion3Seed510829Array0() throws ContainsCycleException {
        int[] id = new int[]{4, 8, 3, 8, 8, 4, 5, 9, 6, 8};
        new QuickUnionBySize(id);
    }

    @Test
    public void testWeek1AssessmentUnionFindQuestion3Seed220878() {
        // TODO
        int[][] id = new int[][]{
                new int[]{8, 6, 6, 2, 8, 0, 8, 6, 8, 8},
                new int[]{9, 6, 3, 3, 8, 3, 3, 7, 0, 3},
                new int[]{9, 6, 9, 2, 1, 6, 3, 8, 6, 6},
                new int[]{0, 7, 2, 3, 9, 0, 6, 7, 8, 9},
                new int[]{7, 7, 1, 0, 7, 7, 1, 8, 8, 7}
        };
        for (int[] anId : id) {
            try {
                System.out.println("\n\n\n\n\n\n\n\n");
                QuickUnionBySize qubs = new QuickUnionBySize(anId);
                System.out.println(qubs);
            } catch (ContainsCycleException e) {

            }
        }
        System.out.println("\n\n");
    }

    @Test
    public void testWeek1AssessmentUnionFindQuestion2Seed682713() {
        QuickUnionBySize qf = new QuickUnionBySize(10);
        int[][] input = new int[][]{
                new int[]{1, 2},
                new int[]{2, 9},
                new int[]{7, 8},
                new int[]{5, 3},
                new int[]{1, 6},
                new int[]{3, 8},
                new int[]{0, 6},
                new int[]{9, 8},
                new int[]{4, 0}
        };
        for (int[] inputPair : input) {
            qf.union(inputPair[0], inputPair[1]);
        }
        assertArrayEquals(new int[]{1, 1, 1, 5, 1, 1, 1, 5, 7, 1}, qf.id);
    }

    @Test
    public void testQuickUnionGetMaxDepth() {
        QuickUnionBySize uf = new QuickUnionBySize(8);
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
        QuickUnionBySize uf = new QuickUnionBySize(10);
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
        QuickUnionBySize qf = new QuickUnionBySize(10);
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
        new QuickUnionBySize(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOutOfBoundUnion() {
        QuickUnionBySize qf = new QuickUnionBySize(10);
        qf.union(0, qf.size());
    }

    @Test
    public void testApplyUnionSequence() {
        QuickUnionBySize qubs = new QuickUnionBySize(10);
        int[][] input = new int[][]{
                new int[]{0, 1},
                new int[]{1, 2},
                new int[]{3, 4},
                new int[]{5, 6},
                new int[]{6, 7},
                new int[]{7, 8},
        };
        for (int[] inputPair : input) {
            qubs.union(inputPair[0], inputPair[1]);
        }
        System.out.println(qubs);
        ArrayList[] partitions = qubs.getPartitions();

        assertEquals(0, partitions[0].get(0));
        assertEquals(1, partitions[0].get(1));
        assertEquals(2, partitions[0].get(2));

        assertEquals(3, partitions[3].get(0));
        assertEquals(4, partitions[3].get(1));

        assertEquals(5, partitions[5].get(0));
        assertEquals(6, partitions[5].get(1));
        assertEquals(7, partitions[5].get(2));
        assertEquals(8, partitions[5].get(3));

        assertEquals(9, partitions[9].get(0));

        QuickUnionBySize aus = new QuickUnionBySize(10);
        QuickUnionBySize.applyUnionSequence(aus, qubs.id);
        partitions = aus.getPartitions();
        System.out.println(aus);
        assertEquals(0, partitions[1].get(0));
        assertEquals(1, partitions[1].get(1));
        assertEquals(2, partitions[1].get(2));

        assertEquals(3, partitions[4].get(0));
        assertEquals(4, partitions[4].get(1));

        assertEquals(5, partitions[6].get(0));
        assertEquals(6, partitions[6].get(1));
        assertEquals(7, partitions[6].get(2));
        assertEquals(8, partitions[6].get(3));

        assertEquals(9, partitions[9].get(0));
    }


    @Test
    public void testPartitions() {
        QuickUnionBySize uf = new QuickUnionBySize(10);
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
        ArrayList[] partitions = uf.getPartitions();

        assertEquals(0, partitions[0].get(0));
        assertEquals(1, partitions[0].get(1));
        assertEquals(2, partitions[0].get(2));

        assertEquals(3, partitions[3].get(0));
        assertEquals(4, partitions[3].get(1));

        assertEquals(5, partitions[5].get(0));
        assertEquals(6, partitions[5].get(1));
        assertEquals(7, partitions[5].get(2));
        assertEquals(8, partitions[5].get(3));

        assertEquals(9, partitions[9].get(0));
    }

    @Test
    public void testInvariants() {
        QuickUnionBySize qubs = new QuickUnionBySize(10);
        int[][] unions = new int[][]{
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
        for (int[] union : unions) {
            assertTrue(qubs.heightInvariantViolationHold());
            assertEquals(-1, qubs.getSizeInvariantViolationIndex());
            qubs.union(union[0], union[1]);
        }
        System.out.println(qubs);
    }

    @Test(expected = ContainsCycleException.class)
    public void testCycle() throws ContainsCycleException {
        int[] id = new int[]{1, 1, 3, 3, 5, 6, 4, 7, 8};
        new QuickUnionBySize(id);
    }

    @Test
    public void testNoCycle() throws ContainsCycleException {
        int[] id = new int[]{1, 1, 3, 3, 5, 6, 7, 7, 8};
        try {
            System.out.println(new QuickUnionBySize(id));
        } catch (ContainsCycleException e) {
            assertTrue(false);
        }
    }

    @Test
    public void testSizeComputation() throws ContainsCycleException {
        /*
algs.QuickUnionBySize@233530418 {
   index  0   1   2   3   4   5   6   7   8   9
  parent  1   1   1   5   1   1   1   5   7   1
    size  1  10   1   1   1   4   1   2   1   1
    root  1   1   1   1   1   1   1   1   1   1
   depth  1   0   1   2   1   1   1   2   3   1
height is 3
max component size is 10
component count is 0
height invariant holds
}         */
        QuickUnionBySize qubs = new QuickUnionBySize(new int[]{1, 1, 1, 5, 1, 1, 1, 5, 7, 1});
        assertArrayEquals(new int[]{1, 10, 1, 1, 1, 4, 1, 2, 1, 1}, qubs.sizes);
        System.out.println(qubs);
    }

    @Test
    public void testSizeInvariants() {
        // TODO
    }


    /*
    @Test
    public void testDivide() throws ContainsCycleException {
        QuickUnionBySize qubsOriginal = new QuickUnionBySize(new int[]{1, 1, 1, 5, 1, 1, 1, 5, 7, 1});
        QuickUnionBySize qubs = new QuickUnionBySize(new int[]{1, 1, 1, 5, 1, 1, 1, 5, 7, 1});
        ArrayList<UnionArguments> reverseUnionList = qubs.divideAll();
        //Collections.reverse(reverseUnionList);
        System.out.println("\n\n\n");
        System.out.println(Arrays.toString(reverseUnionList.toArray()));
        System.out.println("\n\n\n");
        for (UnionArguments unionArgs : reverseUnionList) {
            System.out.println(qubs);
            qubs.union(unionArgs.getFirst(), unionArgs.getSecond());
        }
        assertEquals(qubsOriginal, qubs);
    }
    */
}
