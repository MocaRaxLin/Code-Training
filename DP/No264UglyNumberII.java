package DP;

public class No264UglyNumberII {

	public static void main(String[] args) {
		No264UglyNumberII sol = new No264UglyNumberII();
		String t = "10\n" + 
				"50\n" + 
				"666\n" + 
				"1690";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			int ans = sol.nthUglyNumber(n);
			System.out.println(ans);
		}
	}

	public int nthUglyNumber(int n) {
		// --> O(n) for time and space
		
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for(int i = 1; i < n; i++){
            int t2 = dp[a]*2;
            int t3 = dp[b]*3;
            int t5 = dp[c]*5;
            dp[i] = Math.min(t2, Math.min(t3, t5));
            // we need to skip all possible duplicate tx.
            if(dp[i] == t2) a++;
            if(dp[i] == t3) b++;
            if(dp[i] == t5) c++;
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n-1];
    }
}
