package DP;

import Util.Parser;

public class No486PredicttheWinner {

	public static void main(String[] args) {
		No486PredicttheWinner sol = new No486PredicttheWinner();
		Parser parser = new Parser();
		String t = "[1,5,2]\n" + 
				"[1,5,233,7]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] nums = parser.parseArray(s[i]);
			boolean ans = sol.PredictTheWinner(nums);
			System.out.println(ans);
		}
	}

    public boolean PredictTheWinner(int[] nums) {
    	// --> time = O(n^2), where n = nums.length
    	
    	// Intuition:
    	// The maximum possible score a player can get from A[i:j]
    	// is depend on 2 scenarios.
    	// Pick A[i] left A[i+1:j], pick A[i:j-1] left A[j].
    	
    	// Let dp[i][j] denotes the maximum effective score possible for the sub array A[i,j].
    	// dp[i][j] = max(A[i] - dp[i+1][j], A[j] - dp[i][j-1]).
    	// Finally we let player 1 pick first,
    	// so if dp[0][n-1] >= 0, player 1 won the game.
    	
    	// Score counting like this.
    	// Player 1 get points using addition,
    	// while player 2 get using subtraction.
    	// eg. 2 players get the following points in turns [4, 6, 8, 5]
    	// -> score = 4 - 6 + 8 - 5 = 1, so player 1 gets more points and win :)
    	
        int n = nums.length;
        int[][] dp = new int[n][n];
        //for(int i = 0; i < n; i++) dp[i][i] = 0;
        for(int k = 1; k < n; k++){
            for(int i = 0; i + k < n; i++){
                int j = i + k;
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][n-1] >= 0;
    }
}
