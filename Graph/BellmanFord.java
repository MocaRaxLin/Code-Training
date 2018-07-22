package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BellmanFord {

	public static void main(String[] args) {
		BellmanFord sol = new BellmanFord();
		
		// Input constraints
		int nodes = 9;
		int[][] edges = new int[][]{
			// from, to, cost
			{0,1,4},
			{0,7,8},
			{1,7,11},
			{1,2,8},
			{2,8,2},
			{7,8,7},
			{7,6,1},
			{8,6,6},
			{6,5,2},
			{2,5,4},
			{2,3,7},
			{3,5,14},
			{3,4,9},
			{5,4,10}
		};
		int source = 0;
		int[] distance = sol.BellmanFordAt(nodes, edges, source);
		System.out.println("Bellman Ford's from node "+ source+ ":");
		for(int i = 0; i < nodes; i++) {
			System.out.println("to Node " + i + " cost "+ distance[i]);
		}
	}

	private int[] BellmanFordAt(int nodes, int[][] edges, int source) {
		// time --> O(nm), space --> O(n^2), where n = nodes and m = edges.length.
		// But we can reduce space to O(n) because we only use k-1 as dependency.
		
		// NOTE:
		// If we iterate k times distances still updating,
		// then we know there is a loop to keep reducing shortest distance.
		// That means there is a negative-cost cycle in the graph!
		
		// Intuition:
		// [s]---------P1: (k-1) edges-------->[v]
		//  |                                   ^
		//  |                                   |
		//  \ ----P2: (k-1) edges-->[w]--cost_w-/
		// Compare P1 and P2, find the shortest path.
		// Let dp[k][v] denotes the distance of shortest path from s to v using at most k edges.
		// k should count up to n-1.
		
		// Recursive function:
		// dp[k][v] = Min(dp[k-1][v], dp[k-1][w] + cost_wv), for (w,v) in edges
		
		// Build map
		List<List<Integer>> parents = new ArrayList<List<Integer>>();
		for(int i = 0; i < nodes; i++) parents.add(new LinkedList<>());
		int[][] cost = new int[nodes][nodes];
		for(int[] e: edges) {
			cost[e[0]][e[1]] = e[2];
			cost[e[1]][e[0]] = e[2];
			parents.get(e[0]).add(e[1]); // e0 <- e1
			parents.get(e[1]).add(e[0]); // e0 -> e1
		}
		
		
		int[] dp = new int[nodes];
		
		//basic cases -> O(n)
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[source] = 0;
		
		// fill up table -> O(nm)
		// 2nd for loop indicates go through all edges |E| = m
		for(int k = 1; k < nodes; k++) {
			int[] dpK = new int[nodes];
			Arrays.fill(dpK, Integer.MAX_VALUE);
			for(int v = 0; v < nodes; v++) {
				Iterator<Integer> it = parents.get(v).iterator();
				while(it.hasNext()) {
					int w = it.next();
					if(dp[w] != Integer.MAX_VALUE) {
						dpK[v] = Math.min(dp[w] + cost[w][v], dp[v]);
					}
					
				}
			}
			dp = dpK;
		}
		return dp;
		
	}

}
