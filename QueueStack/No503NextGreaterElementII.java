package QueueStack;

import java.util.Arrays;
import java.util.Stack;

import Util.Parser;

public class No503NextGreaterElementII {

	public static void main(String[] args) {
		No503NextGreaterElementII sol = new No503NextGreaterElementII();
		Parser parser = new Parser();
		String t = "[]\n" + 
				"[1,1]\n" + 
				"[1,2,1]\n" + 
				"[1,3,4,2]\n" + 
				"[4,3,5,-1,2,7]\n" + 
				"[-2, 4, 3, 0, -1, 1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int[] ans = sol.nextGreaterElements(A);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] nextGreaterElements(int[] A) {
        // --> time = O(2n), space = O(n), where n = A.length
        
        // 224 / 224 test cases passed
        // Runtime: 12 ms
        
        // Improvement
        // 1. Since we have stack idxs, we don't need stack to store elements.
        //    We use A[idxs.peek()]
        // 2. Don't concatenate 2 arrays, we use pointer loop back to index 0 again.
        //    For example A[i%A.length]
        // 3. Push indices that need to find the next greater number only.
        //    i.e. those indices i < A.length
        // 4. Crazy! We implement stack using int[] and size pointer.
        
        
        int[] idxs = new int[A.length];
        int idxSize = 0;
        int[] ret = new int[A.length];
        Arrays.fill(ret, -1);
        
        for(int i = 0; i < 2*A.length; i++){
            while(idxSize > 0 && A[idxs[idxSize - 1]] < A[i%A.length]){
                int top = idxs[--idxSize];
                ret[top] = A[i%A.length];
            }
            if(i < A.length) idxs[idxSize++] = i;
        }
        return ret;
    }
	
	public int[] nextGreaterElements0(int[] A) {
        // --> time = O(2n), space = O(2n), where n = A.length
        
        // 224 / 224 test cases passed
        // Runtime: 74 ms
        
        // Concatenate 2 A's and find the next greater number in cycle
        
        int[] B = new int[A.length*2];
        for(int i = 0; i < B.length; i++) B[i] = A[i%A.length];
        
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> idxs = new Stack<Integer>();
        int[] ret = new int[B.length];
        Arrays.fill(ret, -1);
        
        for(int i = 0; i < B.length; i++){
            while(stack.size() > 0 && stack.peek() < B[i]){
                stack.pop();
                ret[idxs.pop()] = B[i];
            }
            stack.push(B[i]);
            idxs.push(i);
        }
        return Arrays.copyOfRange(ret, 0, A.length);
    }
}
