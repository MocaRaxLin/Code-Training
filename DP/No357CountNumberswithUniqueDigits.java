package DP;

public class No357CountNumberswithUniqueDigits {

	public static void main(String[] args) {
		No357CountNumberswithUniqueDigits sol = new No357CountNumberswithUniqueDigits();
		String t = "2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9\n" + 
				"10";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			int ans = sol.countNumbersWithUniqueDigits(n);
			System.out.println(ans);
		}
	}

	public int countNumbersWithUniqueDigits(int n) {
        // --> O(n)
        
        // # of digits -> combination
        // 1 -> 10
        // 2 -> 9*9
        // 3 -> 9*9*8
        // 4 -> 9*9*8*7
        // .
        // .
        // 10 -> 9*9*8*7*6*5*4*3*2*1
        //
        // n = 4 -> 10 + 9*9 + 9*9*8 + 9*9*8*7
        
        // Let dp[i] denotes the production of 9*8*...*i,
        // so n = 4 -> 10 + SUM(9*dp[11-i]) for i = 2 to 4
        
        if(n == 0) return 1;
        if(n > 10) n = 10;
        int[] dp = new int[10];
        dp[9] = 9;
        for(int i = 8; i > 0; i--) dp[i] = dp[i+1] * i;
        
        int ret = 10;
        if(n > 1){
            for(int i = 2; i <= n; i++){
                ret += 9*dp[11-i];
            }
        }
        return ret;
    }
}
