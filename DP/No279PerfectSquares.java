package DP;

public class No279PerfectSquares {

	public static void main(String[] args) {
		No279PerfectSquares sol = new No279PerfectSquares();
		String t = "12\n" + 
				"34\n" + 
				"1";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			int ans = sol.numSquares(n);
			System.out.println(ans);
		}
	}
	
	public int numSquares(int n) {
        // --> O(n^(3/2))
		
        // Let dp[i][j] denotes the min amount of number to form j considering A[0:i];
        //
        // dp[i][j] = MIN(dp[i-1][j], dp[i][j-A[i]] + 1)
        
        // we produce A[i] along with the process.
        
        int[] dp = new int[n+1];
        // basic cases 1*1 : we use only 1 to compose numbers
        for(int i = 0; i <= n; i++) dp[i] = i;
        // start from 2*2
        for(int i = 2; i*i <= n; i++){
            int ai = i*i;
            for(int j = ai; j <= n; j++){
                dp[j] = Math.min(dp[j], dp[j-ai]+1);
            }
        }
        return dp[n];
    }

}
