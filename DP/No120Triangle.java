package DP;

import java.util.List;

import Util.Parser;

public class No120Triangle {

	public static void main(String[] args) {
		No120Triangle sol = new  No120Triangle();
		Parser parser = new Parser();
		String t = "[[2],[3,4],[6,5,7],[4,1,8,3]]";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i++) {
			List<List<Integer>> tri = parser.parseListList(s[i], true);
			int ans = sol.minimumTotal(tri);
			System.out.println(ans);
		}
	}

    public int minimumTotal(List<List<Integer>> tri) {
        // --> time O(n^2), space O(n), where n is the height of triange
        
        // Intuition:
        // climb this pyramid from base compute the shortest path to top
        // Let dp[i][j] denotes the distance of the shortest path to A[i][j]
        // from the last row (i.e. A[i+1][j], A[i+1][j+1])
        // dp[i][j] = MIN(dp[i+1][j], dp[i+1][j+1])
        
        if(tri.size() == 0) return 0;
        int n = tri.size();
        //int base = tri.get(n-1).size();
        int[] dp = new int[n];
        
        // basic case
        for(int i = 0; i < n; i++) dp[i] = tri.get(n-1).get(i);
        
        // move upwards
        for(int i = n-2; i >= 0; i--){
            for(int j = 0; j <= i; j++){
                dp[j] = Math.min(dp[j], dp[j+1]) + tri.get(i).get(j);
            }
            //System.out.println(Arrays.toString(dp));
        }
        
        return dp[0];
    }
}
