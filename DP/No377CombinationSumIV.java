package DP;

import Util.Parser;

public class No377CombinationSumIV {

	public static void main(String[] args) {
		No377CombinationSumIV sol = new No377CombinationSumIV();
		Parser parser = new Parser();
		String t = "[1,2,3]\n" + 
				"4\n" + 
				"[40,4,6,1,2,7,11,5]\n" + 
				"1000\n" + 
				"[]\n" + 
				"0";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			int ans = sol.combinationSum4(A, T);
			System.out.println(ans);
		}
	}
	
    public int combinationSum4(int[] A, int T) {
    	// --> time = O(nT), where n = A.length
    	
    	// Intution
    	// eg. A = [1,2,3], T = 4
    	// To know dp[4], we got to know dp[1], dp[2], dp[3] first
    	// Then Sum them up.
    	//     4
    	//     | 1   \ 2   \ 3
    	//     3      2     1
    	//
    	//
    	// Let dp[i] denotes the number of combination ways of target i.
    	// dp[i] = Sum(dp[ i-A[j] ]), for j = 0 to n-1 and i - A[j] >= 0.
    	
        int[] dp = new int[T+1];
        dp[0] = 1;
        for(int i = 1; i <= T; i++){
            for(int j = 0; j < A.length; j++){
                if(i - A[j] >= 0) dp[i] += dp[i-A[j]];
            }
        }
        return dp[T];
    }

}
