package algs;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class QuickSuccessorWithDeleteTest {

    @Test
    public void testDelete() {
        QuickSuccessorWithDelete qswd = new QuickSuccessorWithDelete(10);
        int[] input = new int[]{
                1, 2, 4, 5
        };
        for (int delete : input) {
            qswd.delete(delete);
            assertTrue(qswd.isDeleted(delete));
        }
        System.out.println(qswd);
    }

    @Test
    public void testSuccessor() {
        QuickSuccessorWithDelete qswd = new QuickSuccessorWithDelete(10);
        int[] successors = new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 9
        };
        int[] predecessors  = new int[]{
                0, 0, 1, 2, 3, 4, 5, 6, 7, 8
        };
        for (int i = 0; i < successors.length; i++) {
            assertEquals(successors[i], qswd.getSuccessor(i));
            assertEquals(predecessors[i], qswd.getPredecessor(i));
        }

        qswd.delete(4);
        successors = new int[]{
              //0, 1, 2, 3, 4,  5, 6, 7, 8, 9
                1, 2, 3, 5, -1, 6, 7, 8, 9, 9
        };
        predecessors  = new int[]{
             // 0, 1, 2, 3, 4,  5, 6, 7, 8, 9
                0, 0, 1, 2, -1, 3, 5, 6, 7, 8
        };
        for (int i = 0; i < successors.length; i++) {
            assertEquals(successors[i], qswd.getSuccessor(i));
            assertEquals(predecessors[i], qswd.getPredecessor(i));
        }

        qswd.delete(4);
        successors = new int[]{
                //0, 1, 2, 3, 4,  5, 6, 7, 8, 9
                1, 2, 3, 5, -1, 6, 7, 8, 9, 9
        };
        predecessors  = new int[]{
                // 0, 1, 2, 3, 4,  5, 6, 7, 8, 9
                0, 0, 1, 2, -1, 3, 5, 6, 7, 8
        };
        for (int i = 0; i < successors.length; i++) {
            assertEquals(successors[i], qswd.getSuccessor(i));
            assertEquals(predecessors[i], qswd.getPredecessor(i));
        }

        qswd.delete(9);
        successors = new int[]{
              //0, 1, 2, 3, 4,  5, 6, 7, 8, 9
                1, 2, 3, 5, -1, 6, 7, 8, 8, -1
        };
        predecessors  = new int[]{
                // 0, 1, 2, 3, 4,  5, 6, 7, 8, 9
                   0, 0, 1, 2, -1, 3, 5, 6, 7, -1
        };
        for (int i = 0; i < successors.length; i++) {
            assertEquals(successors[i], qswd.getSuccessor(i));
            assertEquals(predecessors[i], qswd.getPredecessor(i));
        }

        qswd.delete(0);
        successors = new int[]{
                //0,  1, 2, 3, 4,  5, 6, 7, 8, 9
                  -1, 2, 3, 5, -1, 6, 7, 8, 8, -1
        };
        predecessors  = new int[]{
                // 0,  1, 2, 3, 4,  5, 6, 7, 8, 9
                   -1, 1, 1, 2, -1, 3, 5, 6, 7, -1
        };
        for (int i = 0; i < successors.length; i++) {
            assertEquals(successors[i], qswd.getSuccessor(i));
            assertEquals(predecessors[i], qswd.getPredecessor(i));
        }

        qswd.delete(7);
        successors = new int[]{
                //0,  1, 2, 3,  4,  5, 6,  7, 8, 9
                 -1,  2, 3, 5, -1,  6, 8, -1, 8, -1
        };
        predecessors  = new int[]{
                // 0, 1, 2, 3,  4,  5, 6, 7,  8, 9
                  -1, 1, 1, 2, -1,  3, 5, -1, 8, -1
        };
        for (int i = 0; i < successors.length; i++) {
            assertEquals(successors[i], qswd.getSuccessor(i));
            assertEquals(predecessors[i], qswd.getPredecessor(i));
        }
    }
}
