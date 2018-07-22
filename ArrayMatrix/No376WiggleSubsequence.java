package ArrayMatrix;

import Util.Parser;

public class No376WiggleSubsequence {

	public static void main(String[] args) {
		No376WiggleSubsequence sol = new No376WiggleSubsequence();
		Parser parser = new Parser();
		String t = "[1,7,4,9,2,5]\n" + 
				"[]\n" + 
				"[1,17,5,10,13,15,10,5,16,8]\n" + 
				"[1,2,3,4,5,6,7,8,9]\n" + 
				"[0,0]\n" + 
				"[1,1,1,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.wiggleMaxLength(A);
			System.out.println(ans);
		}
	}

    public int wiggleMaxLength(int[] A) {
        // --> time = O(n), space = O(n), can be reduced to O(1).
        
        // We don't have to use DP every time.
        
        // up[i] refers to the length of the longest wiggle subsequence in A[0:i]
        // down[i] refers to the length of the longest wiggle subsequence in A[0:i]
        // eg. A = [1,17, 5,10,13,15,10, 5,16, 8]
        //    Up = [1, 2, 2, 4, 4, 4, 4, 4, 6, 6]
        //  Down = [1, 1, 3, 3, 3, 3, 5, 5, 5, 7]
        
        // From A[i-1] to A[i] must be increasing, decreasing or the same
        // Incresing leads to up[i] = down[i-1] + 1, down[i] = down[i-1]
        // Decreasing will be the opposite, and the same will be stay as state i-1
        
        // The process is like collecting possible elements into the sequence
        // In the above example, if i = 7, A[i-1] > A[i], 10 > 5
        // Up[6] = [1,17,5,10]
        // Down[6] = [1,17,5,15,10]
        
        // Because A[i-1] > A[i], we set Down[7] = Up[6] + 1, Up[7] = Down[6]
        // Up[6] = [1,17,5,10]
        // Down[6] = [1,17,5,15,10]
        // Up[7] = [1,17,5,10]
        // Down[7] = [1,17,5,10,5]
        
        if(A.length < 2) return A.length;
        int[] up = new int[A.length];
        int[] down = new int[A.length];
        up[0] = down[0] = 1;
        for(int i = 1; i < A.length; i++){
            if (A[i-1] < A[i]){
                up[i] = down[i-1] + 1;
                down[i] = down[i-1];
            } else if (A[i-1] > A[i]){
                up[i] = up[i-1];
                down[i] = up[i-1] + 1;
            } else {
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[A.length - 1], down[A.length - 1]);
    }
    
    public int wiggleMaxLength0(int[] A) {
        // --> time = O(n^2), where n = A.length
        
        // up[i] refers to the length of the longest wiggle subsequence ending at raising A[i]
        // down[i] refers to the length of the longest wiggle subsequence ending at dropping A[i]
        
        // up[i] = Max(down[j] + 1), for j = 0 to i-1 and A[j] < A[i]
        // down[i] = Max(up[i] + 1), for j = 0 to i-1 and A[j] > A[i]
        
        // NOTE:
        // 2 directions in an array, you can try 2 1-D array P, Q for DP
        // Usually P[i] denotes an extreme value with property P ending at A[i]
        // and needs P[0:i-1] and Q[0:i-1] to compute.
        
        if(A.length < 2) return A.length;
        int[] up = new int[A.length];
        int[] down = new int[A.length];
        //up[0] = down[0] = 1;
        for(int i = 1; i < A.length; i++){
            for(int j = i; j >= 0; j--){
                if(A[j] > A[i]) down[i] = Math.max(down[i], up[j] + 1);
                else if(A[j] < A[i]) up[i] = Math.max(up[i], down[j] + 1);
            }
        }
        return 1 + Math.max(up[A.length - 1], down[A.length - 1]);
    }
}
