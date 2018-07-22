package DP;

import Util.Parser;

public class No494TargetSum {

	public static void main(String[] args) {
		No494TargetSum sol = new No494TargetSum();
		Parser parser = new Parser();
		String t = "[1,1,1,1,1]\n" + 
				"3\n" + 
				"[1]\n" + 
				"4\n" + 
				"[1]\n" + 
				"2\n" + 
				"[0,0,0,0,0,0,0,0,1]\n" + 
				"1";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] nums = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			int ans = sol.findTargetSumWays(nums, T);
			System.out.println(ans);
		}
	}

    public int findTargetSumWays(int[] nums, int T) {
    	// P - N = T
    	// P + N = Sum
    	// 2P = T + Sum -> P = (T + Sum)/2
    	
        int sum = 0;
        for(int i: nums) sum+=i;
        if(sum < T || (T + sum) % 2 == 1) return 0;
        int P = (T + sum) / 2;
        return subsetSum(nums, P);
    }
    
    public int subsetSum(int[] A, int T){
    	// --> time = O(nT), where n = A.length
    	
        // let dp[i][j] denotes the number of the ways to form target j considering A[0:i-1]
    	
        int n = A.length;
        int[][] dp = new int[n+1][T+1];
        dp[0][0] = 1;
        
        // 0 has 2 choices, pick or not pick won't interfere the sum.
        
        for(int i = 1; i <= n; i++) dp[i][0] = A[i-1] == 0 ? dp[i-1][0]*2: dp[i-1][0];
        
        for(int j = 1; j <= T; j++){
            for(int i = 1; i <= n; i++){
                dp[i][j] = dp[i-1][j];
                if(j - A[i-1] >= 0) dp[i][j] += dp[i-1][j-A[i-1]];
            }
        }
        return dp[n][T];
    }
}
