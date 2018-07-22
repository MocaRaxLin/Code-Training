package DP;

public class No650_2KeysKeyboard {

	public static void main(String[] args) {
		No650_2KeysKeyboard sol = new No650_2KeysKeyboard();
		
		// Input Constraint:
		// n = [1, 1000]
		
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9\n" + 
				"10\n" + 
				"11\n" + 
				"12\n" + 
				"13\n" + 
				"14\n" + 
				"23\n" + 
				"50\n" + 
				"100\n" + 
				"200\n" + 
				"400\n" + 
				"555\n" + 
				"789\n" + 
				"919\n" + 
				"999\n" + 
				"1000";
		String[] s = t.split("\n");
		for(String i: s) {
			int n = Integer.parseInt(i);
			int ans = sol.minSteps(n);
			System.out.println(ans);
		}
	}
	
    public int minSteps(int n) {
    	// --> time = O(sqrt(n)), space = O(n)
    	
    	// Intuition by Observation:
    	// 1 -> 0
    	// 2 -> cp -> 2, c stands for copy, p stands for paste
    	// 3 -> cpp -> 3
    	// 4 -> cppp, cpcp -> 4
    	// 5 -> cpppp -> 5
    	// 6 -> dp[3] + cp -> 5
    	//   -> dp[2] + cpp -> 5
    	// 7 -> cpppppp -> 7
    	// 8 -> dp[4] + cp -> 6
    	//   -> dp[2] + cppp -> 6
    	// 9 -> dp[3] + cpp -> 6
    	// 10 -> dp[5] + cp -> 7
    	//    -> dp[2] + cpppp -> 7
    	// 11 -> cpppppppppp -> 11
    	// 12 -> dp[6] + 2 = 7
    	//    -> dp[4] + 3 = 7
    	//    -> dp[3] + 4 = 7
    	//    -> dp[2] + 6 = 8
    	
    	// Using DP store minimum steps of factors.
    	// Using recursion from top to down
    	// to check each dp[i] is computed or not.
    	// If computed, we use it otherwise compute it by recursion.
    	
    	// time complexity will "converge" to sqrt(n),
    	// The exact time is Sum(sqrt(n)/f), for f is all factor of n.
    	// because we only go through 2 to sqrt(n)
    	// to find out dp[i], for i in factors of n.
    	
        if(n == 1) return 0;
        int[] dp = new int[n+1];
        dp[1] = 1;
        return coreMinSteps(dp, n);
    }
    
    public int coreMinSteps(int[] dp, int n){
        if(n == 1) return 0;
        if(dp[n] != 0) return dp[n];
        double root = Math.sqrt(n);
        dp[n] = n;
        for(int i = 2; i <= root ; i++){
            if(n % i == 0){
                int factor = n/i;
                int fStep = coreMinSteps(dp, factor);
                dp[n] = Math.min(dp[n], fStep + i);
            }
        }
        return dp[n];
    }

}
