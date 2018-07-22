package DP;

import java.util.Arrays;

import Util.Parser;

public class No787CheapestFlightsWithinKStops {

	public static void main(String[] args) {
		No787CheapestFlightsWithinKStops sol = new No787CheapestFlightsWithinKStops();
		Parser parser = new Parser();
		
		String testcase = "3\n" + 
				"[[0,1,100],[1,2,100],[0,2,500]]\n" + 
				"0\n" + 
				"2\n" + 
				"0\n" + 
				"5\n" + 
				"[[4,1,1],[1,2,3],[0,3,2],[0,4,10],[3,1,1],[1,4,3]]\n" + 
				"2\n" + 
				"1\n" + 
				"1\n" + 
				"4\n" + 
				"[[0,1,1],[0,2,5],[1,2,1],[2,3,1]]\n" + 
				"0\n" + 
				"3\n" + 
				"1\n" + 
				"3\n" + 
				"[[0,1,1],[1,2,1],[2,0,1]]\n" + 
				"0\n" + 
				"1\n" + 
				"3\n" + 
				"4\n" + 
				"[[0,1,100],[0,2,300],[0,3,500],[1,2,100],[2,3,100]]\n" + 
				"0\n" + 
				"3\n" + 
				"1";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=5) {
			int n = Integer.parseInt(s[i]);
			int[][] flights = parser.parseMatrix(s[i+1]);
			int src = Integer.parseInt(s[i+2]);
			int dst = Integer.parseInt(s[i+3]);
			int K = Integer.parseInt(s[i+4]);
			int ans = sol.findCheapestPrice(n, flights, src, dst, K);
			System.out.println(ans);
		}
		
		
	}
	
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // --> time O(Kn^2), space complexity can reduce to O(n).
		
        int[][] cost = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);
        for(int i = 0; i < n; i++) cost[i][i] = 0;
        for(int i = 0; i < flights.length; i++) cost[flights[i][0]][flights[i][1]] = flights[i][2];
        
        // Like Bellman Ford's or Floyd Warshall's ?
        // Let dp[k][v] denotes the shortest path from s to v using at most k stops.
        int[][] dp = new int[K+1][n];
        
        // basic case
        for(int i = 0; i < n; i++)
        	dp[0][i] = cost[src][i] == Integer.MAX_VALUE? Integer.MAX_VALUE: cost[src][i];
        for(int i = 1; i <= K; i++)
        	Arrays.fill(dp[i], Integer.MAX_VALUE);
        
        // fill up table
        for(int k = 1; k <= K; k++){
            for(int v = 0; v < n; v++){
                dp[k][v] = dp[k-1][v];
                for(int w = 0; w < n; w++){
                    // w v not connected or no path from s to w using k-1 stops
                    if(cost[w][v] == Integer.MAX_VALUE || dp[k-1][w] == Integer.MAX_VALUE)
                    	continue;
                    if(dp[k][v] > dp[k-1][w] + cost[w][v])
                    	dp[k][v] = dp[k-1][w] + cost[w][v];
                }
            }
        }
        
        // It is like Bellman Ford!
        return dp[K][dst] == Integer.MAX_VALUE ? -1: dp[K][dst];
    }

}
