package DP;

public class No790DominoandTrominoTiling {

	public static void main(String[] args) {
		No790DominoandTrominoTiling sol = new No790DominoandTrominoTiling();
		
		// Constraint
		// -> N  will be in range [1, 1000]
		// Return your answer modulo 10^9 + 7.
		String testcase = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9\n" + 
				"10\n" + 
				"20\n" + 
				"30\n" + 
				"40\n" + 
				"50\n" + 
				"100\n" + 
				"200\n" + 
				"300\n" + 
				"400\n" + 
				"500\n" + 
				"999\n" + 
				"1000";
		String[] s = testcase.split("\n");
		for(int i = 0; i<s.length; i++) {
			int N = Integer.parseInt(s[i]);
			int ans = sol.numTilings(N);
			System.out.println(ans);
		}
	}
	
	public int numTilings(int N) {
		// --> time O(N), space O(N) can reduce to O(1).
		
		// N -> {possible Domino arrangement }
		// 0 -> {}
		// 1 -> {|} -> 1
		// 2 -> {||, ＝} -> 2
		// 3 -> {|||, ｜＝, ＝｜, 「」, ＬＴ} -> 5
		//      dp[2] x {|} + dp[1] x {＝} + dp[0]x{「」, ＬＴ}
		
		// Don't forget:
		// 「...」, Ｌ...Ｔ, where ... means =.
		// That's why 2*(dp[n-3] + dp[n-4] + ... + dp[1] + dp[0]) exists.
		
		// dp[3] = dp[2] + dp[1] + 2*(dp[0])
		// dp[4] = dp[3] + dp[2] + 2*(dp[1] + dp[0])
		// dp[5] = dp[4] + dp[3] + 2*(dp[2] + dp[1] + dp[0])
		// dp[n] = dp[n-1] + dp[n-2] + 2*(dp[n-3] + dp[n-4] + ... + dp[1] + dp[0]).
		//       = dp[n-1] + dp[n-3] + dp[n-2] + dp[n-3] + 2*(dp[n-4] + dp[n-5] + ... + dp[1] + dp[0]).
		//       = dp[n-1] + dp[n-3] + dp[n-1].
		
		// NOTE:
		// To modulo a very large integer
		// 1. convert to long
		// 2. modulo
		// 3. convert back to int
		
        int n = N < 3? 3: N;
        int mod = (int)1e9 + 7;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= N; i++){
            long t = (long) 2*dp[i-1] + dp[i-3];
            dp[i] = (int) (t % mod);
        }
        return dp[N];
    }

}
