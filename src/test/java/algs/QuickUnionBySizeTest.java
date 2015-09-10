package algs;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuickUnionBySizeTest {

// TODO
// check for cycles
// check height < log(10)
// check for ??
// TODO
/*
Your Answer		Score	Explanation

4 8 3 8 8 4 5 9 6 8 
Correct	0.20	The id[] array contains a cycle: 6->5->4->8->6

0 8 1 0 1 7 7 8 0 1 
Inorrect	0.00	Size of tree rooted at parent of 8 < twice the size of tree rooted at 8

0 8 2 8 4 5 6 8 8 9 
Correct	0.20	8-3 3-7 1-8

5 4 5 3 2 3 2 2 2 9 
Inorrect	0.00	Height of forest = 4 > lg N = lg(10)

7 2 2 2 2 6 2 6 2 4 
Correct	0.20	2-3 2-8 6-5 7-0 4-9 9-2 5-7 8-6 7-1
Total		0.60 / 1.00	
*/
    @Test
    public void testWeek1AssessmentUnionFindQuestion3Seed510829() {
      int[][] id = new int[][] {
          new int[] {4, 8, 3, 8, 8, 4, 5, 9, 6, 8},
          new int[] {0, 8, 1, 0, 1, 7, 7, 8, 0, 1},
          new int[] {0, 8, 2, 8, 4, 5, 6, 8, 8, 9},
          new int[] {5, 4, 5, 3, 2, 3, 2, 2, 2, 9},
          new int[] {7, 2, 2, 2, 2, 6, 2, 6, 2, 4}
      };
      for (int i = 0; i < 4; i++) {
          System.out.println("\n\n\n");
          QuickUnionBySize qubs = new QuickUnionBySize(id[i]);
          System.out.println(qubs);
          for (int j = 0; j < id[i].length; j++) {
              System.out.format("\nindex=%d root=%d", j, qubs.getRootCycleCheck(j));
          }
      }
      System.out.println("\n\n");
    }

    @Test
    public void testWeek1AssessmentUnionFindQuestion2Seed682713() {
        QuickUnionBySize qf = new QuickUnionBySize(10);
        int[][] input = new int[][] { 
                new int[] {1, 2},
                new int[] {2, 9},
                new int[] {7, 8},
                new int[] {5, 3},
                new int[] {1, 6},
                new int[] {3, 8},
                new int[] {0, 6},
                new int[] {9, 8},
                new int[] {4, 0}
        };
        for (int[] inputPair : input) {
            qf.union(inputPair[0], inputPair[1]);
        }        
        System.out.println(qf);
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

    // TODO
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
