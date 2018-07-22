package DP;

import Util.Parser;

public class No416PartitionEqualSubsetSum {

	public static void main(String[] args) {
		No416PartitionEqualSubsetSum sol = new No416PartitionEqualSubsetSum();
		Parser parser = new Parser();
		
		String t = "[1,5,11,5]\n" + 
				"[1, 2, 3, 5]\n" + 
				"[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100]\n" + 
				"[2,3,5]\n" + 
				"[10,10,10,10,10]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] nums = parser.parseArray(s[i]);
			boolean ans = sol.canPartition(nums);
			System.out.println(ans);
		}
	}
	
	public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i: nums) sum += i;
        if(sum%2 == 1) return false;
        int half = sum/2;
        return subsetSum(nums, half);
    }
    
    public boolean subsetSum(int[] A, int T){
    	// --> O(nT), where n = A.length
    	
        // let dp[i][j] denotes the number of the ways to form target j
        // considering A[0:i-1]
        int n = A.length;
        boolean[][] dp = new boolean[n+1][T+1];
        for(int i = 0; i <= n; i++) dp[i][0] = true;
        for(int j = 1; j <= T; j++){
            for(int i = 1; i <= n; i++){
                dp[i][j] = dp[i-1][j];
                if(j - A[i-1] >= 0) dp[i][j] |= dp[i-1][j-A[i-1]];
            }
        }
        return dp[n][T];
    }
}
