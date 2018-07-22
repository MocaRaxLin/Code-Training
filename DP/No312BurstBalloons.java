package DP;

import Util.Parser;

public class No312BurstBalloons {

	public static void main(String[] args) {
		No312BurstBalloons sol = new No312BurstBalloons();
		Parser parser = new Parser();
		String t = "[3,1,5,8]\n"
				+ "[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] nums = parser.parseArray(s[i]);
			int ans = sol.maxCoins(nums);
			System.out.println(ans);
		}
	}

    public int maxCoins(int[] nums) {
        // --> time = O(n^3), space = O(n^2)
        
        int n = nums.length;
        int[] A = new int[n+2];
        
        //convert to A = [1, ..., 1]
        A[0] = 1;
        for(int i = 0; i < n; i++) A[i+1] = nums[i];
        A[n+1] = 1;
        
        int[][] dp = new int[n+2][n+2];
        
        // Basic case are included,
        // because if the last baloon we burst A[i] in range[i:i], 
        // we can directly compute
        // dp[i][i] = dp[i][i-1] + dp[i+1][i] + A[i-1]*A[i]*A[i+1]
        //          = 0 + 0 + A[i-1]*A[i]*A[i+1].
        
        for(int l = 0; l < n; l++){
            for(int i = 1; i + l <= n; i++){
                int j = i + l;
                for(int k = i; k <= j; k++){
                    // burst A[i:k-1] and A[k+1:j], finally burst A[k]
                    dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + dp[k+1][j] + A[i-1]*A[k]*A[j+1]);
                }
            }
        }
        return dp[1][n];
        
    }
}
