package ArrayMatrix;

import java.util.Stack;

public class No85MaximalRectangle {

	public static void main(String[] args) {
		No85MaximalRectangle sol = new No85MaximalRectangle();
		char[][] matrix = new char[][] {
			{'1','0','1','0','0'},
			{'1','0','1','1','1'},
			{'1','1','1','1','1'},
			{'1','0','0','1','0'}
		};
		int ans = sol.maximalRectangle(matrix);
		System.out.println(ans);
	}

    public int maximalRectangle(char[][] matrix) {
        // --> O(mn), where m = row of matrix, n = column of matrix
        
        // Intuition:
        // For every row, we view consecutive '1' from bottom to top as a building
        // And then apply Largest Rectangle Area to find maximal area.
        
        // NOTE:
        // When we deal with a matrix problem,
        // solve it by increasing area row by row is a good start.
        // row: [0:0] -> [0:1] -> [0:2] -> ... -> [0:m-1]
        
        if(matrix.length == 0) return 0;
        int m = matrix.length;
        if(matrix[0].length == 0) return 0;
        int n = matrix[0].length;
        
        int ret = 0;
        int[] dp = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1') dp[j]++;
                else dp[j] = 0;
            }
            int LRA = largestRectangleArea(dp);
            ret = Math.max(ret, LRA);
        }
        return ret;
    }
    
    public int largestRectangleArea(int[] A) {
    	// --> time = O(n), where n = A.length
        // Leetcode No 84
    	// Stack
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
        for(int j = 0; j < A.length; j++)
            ret = Math.max(ret, A[j]*(right[j] - left[j] - 1));
        return ret;
    }
}
