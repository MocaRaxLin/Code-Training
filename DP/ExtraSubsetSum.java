package DP;

import Util.Parser;

public class ExtraSubsetSum {

	public static void main(String[] args) {
		ExtraSubsetSum sol = new ExtraSubsetSum();
		Parser parser = new Parser();
		
		String t = "[1]\n"
				+ "1\n"
				+ "[100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100]\n"
				+ "5000\n"
				+ "[2,5,7,8,35,7,7]\n"
				+ "22\n"
				+ "[0,0,0,0,1]\n"
				+ "1";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			boolean ans = sol.subsetSum(A, T);
			System.out.println(ans);
		}
	}
	
	public boolean subsetSum(int[] A, int T){
        // let dp[i][j] denotes the number of the ways to form target j
        // considering A[0:i-1]
		
		// Note:
		// This function will overflow
		
//		int sum = 0;
//		for(int i: A) sum+= i;
//		System.out.println(sum);
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
