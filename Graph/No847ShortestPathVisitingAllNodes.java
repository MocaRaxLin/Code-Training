package Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import Util.Parser;

public class No847ShortestPathVisitingAllNodes {

	public static void main(String[] args) {
		No847ShortestPathVisitingAllNodes sol = new No847ShortestPathVisitingAllNodes();
		Parser parser = new Parser();
		String t = "[[1,2,3],[0],[0],[0]]\n" + 
				"[[1],[0,2,4],[1,3,4],[2],[1,2]]\n" + 
				"[[1,3],[0,2,3],[1,3],[0,1,2]]\n" + 
				"[[1,2,3],[0,4,5],[0,6,7],[0,8,9],[1],[1],[2],[2],[3],[3]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] graph = parser.parseMatrix(s[i]);
			
		}
	}
	
	
	public int shortestPathLength(int[][] graph) {
        
        // Thanks to:
        // https://leetcode.com/problems/shortest-path-visiting-all-nodes/discuss/135686/Java-DP-Solution
        //
        // dp[0][3(00000...00011)] = 2 means the shortest path end at node 0 through 0, 1 is 2
        // dp[1][7(00000...00111)] = 3 means the shortest path end at node 1 through 0, 1, 2 is 3.
		// dp[v][mask] = min(dp[u][mask - v] + 1) for u is parent of v w.r.t BFS.
		//
		// In the end, we find min in dp[i][111...111] for i = 0 to n-1
	
        int n = graph.length; if(n == 0) return 0;
        int[][] dp = new int[n][(1<<n)]; // since n <= 12, we can do this
        Queue<int[]> q = new LinkedList<int[]>();
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            int mask = (1<<i);
            dp[i][mask] = 0;
            q.offer(new int[]{i, mask}); // node, pathed node
        }
        
        while(q.size() > 0){
            int[] state = q.poll();
            int u = state[0], mask = state[1];
            for(int v: graph[u]){
                int nextMask = mask | (1<<v);
                if(dp[u][mask] + 1 < dp[v][nextMask]){
                    dp[v][nextMask] = dp[u][mask] + 1;
                    q.offer(new int[]{v, nextMask});
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        int last = (1<<n) - 1;
        for(int i = 0; i < n; i++){
            min = Math.min(min, dp[i][last]);
        }
        return min;
    }

}
