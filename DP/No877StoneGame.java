package DP;

import Util.Parser;

public class No877StoneGame {

	public static void main(String[] args) {
		No877StoneGame sol = new No877StoneGame();
		Parser parser = new Parser();
		String t = "[5,3,4,5]\n" + 
				"[2,5,3,1]\n" + 
				"[3,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] piles = parser.parseArray(s[i]);
			boolean ans = sol.stoneGame(piles);
			System.out.println(ans);
		}
	}
	
	public boolean stoneGame(int[] piles) {
        // --> time = O(n^2), space = O(n), where n = pile.length
		
        // Let dp[i,j] denotes the biggest number of stones you can get more than opponent in range i to j.
        // dp[i,j] = MAX(p[i] - dp[i+1,j], p[j] - dp[i,j-1])
		// This is the key point worth to remember!
        // Also, we can use 1D array because the dpendency only on i+1 and j-1
        
        int[] dp = new int[piles.length];
        for(int i = 0; i < piles.length; i++) dp[i] = piles[i];
        for(int i = piles.length - 2; i >= 0; i--){
            for(int j = i+1; j < piles.length; j++){
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j-1]);
            }
        }
        return dp[piles.length-1] > 0;
    }
    
    // Fun fact:
    // source: https://leetcode.com/problems/stone-game/discuss/154610/C++JavaPython-DP-or-Just-return-true
    
    /*
    Alex is first to pick pile.
    piles.length is even, and this lead to an interesting fact:
    Alex can always pick odd piles or always pick even piles!
    
    For example,
    If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
    he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
    Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.
    
    In the description, we know that sum(piles) is odd.
    If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
    If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.
    So, Alex always defeats Lee in this game.
    */
}
