package DP;

import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class No873LengthofLongestFibonacciSubsequence {

	public static void main(String[] args) {
		No873LengthofLongestFibonacciSubsequence sol = new No873LengthofLongestFibonacciSubsequence();
		Parser parser = new Parser();
		String t = "[1,2,3,4,5,6,7,8]\n" + 
				"[1,3,7,11,12,14,18]\n" + 
				"[2,4,7,8,9,10,14,15,18,23,32,50]\n" + 
				"[1,3,5]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.lenLongestFibSubseq(A);
			System.out.println(ans);
		}
	}
	
	public int lenLongestFibSubseq(int[] A) {
        // --> time = O(n^2), space = O(n^2)
        
        // Important!
        // Clam down and solve prolem
        // use paper and pencil
        // use some test cases
        // analyze time space complexity
        // .
        // .
        // .
        // Write code is the last.
        
        Map<Integer, Integer> idx = new HashMap<Integer, Integer>();
        for(int i = 0; i < A.length; i++) idx.put(A[i], i);
        
        // Let dp[i][j] stores the length of longest FibSubseq
        // ending at A[i] then A[j].
        // eg. ......, Ai, Aj.
        // eg. ......, Ah, Ai, Aj.
        // => dp[i][j] = dp[h][i] + 1
        // => basic case: dp[h][i] = 0 then dp[i][j] = 3.
        
        int[][] dp = new int[A.length][A.length];
        
        int max = 0;
        for(int k = 0; k < A.length; k++){
            for(int j = k-1; j >= 0; j--){
                int Ai = A[k] - A[j];
                int i = idx.getOrDefault(Ai, A.length);
                if(i < j){
                    dp[j][k] = dp[i][j] == 0? 3 : dp[i][j] + 1;
                }
                max = Math.max(max, dp[j][k]);
            }
        }
        return max < 3? 0: max;
    }

}
