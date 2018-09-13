package DP;

public class No375GuessNumberHigherorLowerII {

	public static void main(String[] args) {
		No375GuessNumberHigherorLowerII sol = new No375GuessNumberHigherorLowerII();
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"5\n" + 
				"7\n" + 
				"10\n" + 
				"30\n" + 
				"50\n" + 
				"64";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.getMoneyAmount(Integer.parseInt(s[i]));
			System.out.println(ans);
		}
	}

    public int getMoneyAmount(int n) {
        // --> time = O(n^3)
        
        // Let dp[i][j] denotes the minumum cost to guarentee a win.
        // dp[i][j] = MIN(x[k]), for k in [i:j]
        // Let x[k] denotes the highest cost no matter target t < k or k < t.
        // x[k] = MAX(dp[i][k-1], dp[k+1][j]) + k
        
        // basic case dp[i][i] = 0
        
        int[][] dp = new int[n+1][n+1];
        for(int l = 1; l <= n; l++){
            for(int i = 1; i + l <= n; i++){
                int j = i + l;
                // pick i
                int x = i + dp[i+1][j];
                dp[i][j] = x;
                int k = i+1;
                for(; k < j; k++){
                    x = k + Math.max(dp[i][k-1], dp[k+1][j]);
                    dp[i][j] = Math.min(dp[i][j], x);
                }
                // pick j
                x = dp[i][k-1] + k;
                dp[i][j] = Math.min(dp[i][j], x);
            }
        }
        return dp[1][n];
    }
}
