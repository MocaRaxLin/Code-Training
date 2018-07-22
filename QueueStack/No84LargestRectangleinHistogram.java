package QueueStack;

import java.util.Stack;

import Util.Parser;

public class No84LargestRectangleinHistogram {

	public static void main(String[] args) {
		No84LargestRectangleinHistogram sol = new No84LargestRectangleinHistogram();
		Parser parser = new Parser();
		String t = "[2,1,5,6,2,3]\n" + 
				"[39,6,17,8,22]\n" + 
				"[1]\n" + 
				"[1,2,3,4,5]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.largestRectangleArea(A);
			System.out.println(ans);
		}
	}
	
    public int largestRectangleArea(int[] A) {
        // --> O(n), where n = A.length
        
        // source: https://www.geeksforgeeks.org/largest-rectangle-under-histogram
        // When we traverse to index i
        // We need to know index of the first smaller (smaller than 'A[i]') bar on left of A[i]
        // and index of first smaller bar on right of A[i].
        
        // Key Points:
        // 1. if A[top] <= A[i], then bound[i] = [top, ?], and we push it into stack
        // 2. if A[top] > A[i], then bound[top] = [known, i], and we pop it out.
        
        // This is an advanced stack technique
        // Very classic, Keep it in your mind!
        
        if(A.length == 0) return 0;
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        Stack<Integer> stack = new Stack<Integer>();
        
        int i = 0;
        for(; i < A.length; i++){
            if(stack.size() == 0){
                left[i] = -1;
                stack.push(i);
            }else{
                while(stack.size() > 0 && A[stack.peek()] > A[i]){
                    int top = stack.pop();
                    right[top] = i;
                }
                left[i] = stack.size() == 0? -1: stack.peek();
                stack.push(i);
            }
        }
        while(stack.size() > 0){
            int top = stack.pop();
            right[top] = i;
        }
        
        int ret = 0;
        for(int j = 0; j < A.length; j++){
            ret = Math.max(ret, A[j]*(right[j] - left[j] - 1));
        }
        return ret;
    }

}
