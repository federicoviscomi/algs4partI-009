package algs;

import edu.princeton.cs.algs4.Stack;

public class StackAndQueueQuestion1 {
    public static boolean validate(int[] testCase) {
        Stack<Integer> stack = new Stack<>();
        int[] out = new int[testCase.length];
        int nextToPush = 0;
        for (int i = 0; i < testCase.length; i++) {
            while (nextToPush < testCase.length && (stack.isEmpty() || (stack.peek() < testCase[i]))) {
                //System.out.println("push " + nextToPush);
                stack.push(nextToPush);
                nextToPush++;
            }
            if (testCase[i] != stack.pop()) {
                return false;
            }
        }
        return true;
    }
}
