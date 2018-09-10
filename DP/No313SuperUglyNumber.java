package DP;

import Util.Parser;

public class No313SuperUglyNumber {

	public static void main(String[] args) {
		No313SuperUglyNumber sol = new No313SuperUglyNumber();
		Parser parser = new Parser();
		String t = "12\n" + 
				"[2,7,13,19]\n" + 
				"7\n" + 
				"[2,3,5]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int n = Integer.parseInt(s[i]);
			int[] primes = parser.parseArray(s[i+1]);
			int ans = sol.nthSuperUglyNumber(n, primes);
			System.out.println(ans);
		}
	}

	public int nthSuperUglyNumber(int n, int[] primes) {
        // --> O(nm), where m = primes.length
        
		// Intution:
		// Extend from No 264 Ugly Number.
		// 
		// Let dp[i-1] denotes the i-th super ugly number.
		//
		// Make an index array to store index for each prime to extend.
		// eg. idx = [2,4,1,0], primes = [2,5,3,7]
		//     our candidate = [dp[2]*2, dp[4]*5, dp[1]*3, dp[0]*7]   ps. just example
		// Then we find the min in candidates as dp[i].
		// After we increase the index where candidate[j] == min
		// eg. dp[1]*3 is the smallest, then idx = [2, 4, 1+1, 0]
		
		// Ps. we can use min heap to extract min value to speed up performance.
		
        if(n == 1) return 1;
        int[] idx = new int[primes.length];
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 1; i < n; i++){
            dp[i] = Integer.MAX_VALUE;
            int[] candidates = new int[primes.length];
            for(int j = 0; j < primes.length; j++){
                candidates[j] = dp[idx[j]] * primes[j];
                dp[i] = Math.min(dp[i], candidates[j]);
            }
            for(int j = 0; j < primes.length; j++){
                if(dp[i] == candidates[j]) idx[j]++;
            }
        }
        return dp[n-1];
    }
}
