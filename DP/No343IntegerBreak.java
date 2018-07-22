package DP;

public class No343IntegerBreak {

	public static void main(String[] args) {
		No343IntegerBreak sol = new No343IntegerBreak();
		String t = "2\n" + 
				"8\n" + 
				"10\n" + 
				"24\n" + 
				"58";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.integerBreak(Integer.parseInt(s[i]));
			System.out.println(ans);
		}
	}

    public int integerBreak(int n) {
        // --> O(n^2)
        
        // Intution:
        // given n = 10 we got to check every combination
        // (1,9) (2,8) (3,7) (4,6) (5,5)
        // for each number in those combinations,
        // we may use it as original number (non break), or break it.
        
        // Let dp[i] denotes the maximum product for number i;
        // dp[i] = MAX(a*b, dp[a]*b, a*dp[b], dp[a]*dp[b]), (a,b) -> for all pairs
        
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int a = 1; a <= i/2; a++){
                int b = i - a;
                dp[i] = Math.max(dp[i], a*b);
                dp[i] = Math.max(dp[i], dp[a]*dp[b]);
                dp[i] = Math.max(dp[i], a*dp[b]);
                dp[i] = Math.max(dp[i], dp[a]*b);
            }
        }
        return dp[n];
    }
}
