package DP;

import java.util.Arrays;

import Util.Parser;

public class No322CoinChange {

	public static void main(String[] args) {
		No322CoinChange sol = new No322CoinChange();
		Parser parser = new Parser();
		String t = "[1,2,5]\n" + 
				"11\n" + 
				"[]\n" + 
				"0\n" + 
				"[]\n" + 
				"3\n" + 
				"[2,7,4]\n" + 
				"17\n" + 
				"[5,7,2,3]\n" + 
				"193";
		String[] s = t.split("\n");
		for(int i = 0; i <s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int amount = Integer.parseInt(s[i+1]);
			int ans = sol.coinChange(A, amount);
			System.out.println(ans);
		}
	}
	
    public int coinChange(int[] A, int amount) {
        // --> time = O(nT), where n = a.length, T= amount
        
        if(amount == 0) return 0; // no need to use coin
        if(A.length == 0) return -1;
        
        // Let dp[i][j] denotes the minimum coins to make j dollars
        // using A[0:i] different denominateions
        // dp[i][j] = MIN(dp[i-1][j], dp[i][j-A[i]] + 1)
        
        int[] dp = new int[amount+1]; // use 1-D array to save deploying time
        Arrays.fill(dp, Integer.MAX_VALUE);
        
        // basic case:
        // 0 coins for 0 dollar
        dp[0] = 0;
        
        for(int i = 0; i < A.length; i++){ // from first coin
            for(int j = 1; j <= amount; j++){ // from 1 dollar
                if(j-A[i] >= 0 && dp[j-A[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j-A[i]] + 1);
            }
        }
        
        int ret = dp[amount];
        return ret == Integer.MAX_VALUE? -1: ret;
    }
    

    public int coinChange0(int[] A, int amount) {
        // --> time = O(nT), where n = a.length, T= amount
        
        if(amount == 0) return 0; // no need to use coin
        if(A.length == 0) return -1;
        
        // Let dp[i][j] denotes the minimum coins to make j dollars
        // using A[0:i] different denominations
        // dp[i][j] = MIN(dp[i-1][j], dp[i][j-A[i]] + 1)
        // only if dp[i-1][j] != -1 and dp[i][j-A[i]] != -1, we need to compare,
        // otherwise just pick the non -1 source
        
        int[][] dp = new int[A.length][amount+1];
        for(int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);
        
        // basic case:
        // use the first coin of denomination A[0]
        // 0 coins for 0 dollar
        int c = 0;
        for(int j = 0; j <= amount; j++) if(j % A[0] == 0) dp[0][j] = c++;
        for(int i = 0; i < A.length; i++) dp[i][0] = 0;
        
        for(int i = 1; i < A.length; i++){
            for(int j = 1; j <= amount; j++){
                dp[i][j] = dp[i-1][j];
                if(j-A[i] >= 0 && dp[i][j-A[i]] != -1){
                    if(dp[i][j] == -1) dp[i][j] = dp[i][j-A[i]] + 1;
                    else dp[i][j] = Math.min(dp[i][j], dp[i][j-A[i]] + 1);
                }
            }
        }
        
        int ret = dp[A.length-1][amount];
        return ret;
    }
}
