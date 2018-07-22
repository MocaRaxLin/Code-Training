package DP;

public class No50Powxn {

	public static void main(String[] args) {
		No50Powxn sol = new No50Powxn();
		String t = "2.00000\n" + 
				"10\n" + 
				"3.00000\n" + 
				"31\n" + 
				"2.00000\n" + 
				"-2\n" + 
				"0.00001\n" + 
				"2147483647\n" + 
				"2.00000\n" + 
				"-2147483648";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			double x = Double.parseDouble(s[i]);
			int p = Integer.parseInt(s[i+1]);
			double ans = sol.myPow(x, p);
			System.out.println(ans);
		}
	}

	public double myPow(double x, int p) {
        // --> time = O(logn) and space must be = O(logn),
        // because n in range [-2^31,2^31-1],
        // there is no space for array having A[2^31]
        
        // Intuition:
        // 31 -> 15 -> 7 -> 3 -> 1 -> 0
        
        // NOTE:
        // p could be negative
        // be careful n = -2^31
        long n = (long) p;
        if(n < 0) n = -n;
        
        // assign logn space
        int last = 0;
        long t = n;
        while(t > 0){ t/=2; last++; }
        
        double[] dp = new double[last+1];
        dp[0] = 1;
        dp[last] = dpMyPow(x, n, dp, last);
        return p > 0 ? dp[last]: 1/dp[last];
    }
    
    public double dpMyPow(double x, long n, double[] dp, int idx){
        if(dp[idx] != 0) return dp[idx];
        dp[idx] = dpMyPow(x, n/2, dp, idx - 1) * dpMyPow(x, n/2, dp, idx - 1);
        if(n%2 == 1) dp[idx] *= x;
        return dp[idx];
    }
}
