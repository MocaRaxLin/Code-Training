package Graph;

import java.util.Arrays;

import Util.Show;

public class FloydWarshall {

	public static void main(String[] args) {
		FloydWarshall sol = new  FloydWarshall();
		Show show = new Show();
		
		int nodes = 5; // node 0 - 4
		int[][] edges = new int[][]{
			// from, cost, to
			{0,0,0},
			{1,0,1},
			{2,0,2},
			{3,0,3},
			{4,0,4},
			{0,1,1},
			{0,4,2},
			{1,8,2},
			{1,10,3},
			{3,1,1},
			{3,3,4},
			{3,6,0},
			{2,1,4},
			{2,6,3},
			{4,7,2},
			{4,2,3},
		};
		int source = 0;
		int[][] distance = sol.FloydWarshallforAll(nodes, edges, source);
		System.out.println("FloydWarshall All Pairs Shortest Paths:");
		show.showMatrix(distance);
	}

	private int[][] FloydWarshallforAll(int nodes, int[][] edges, int source) {
		// --> O(n^3), where n = nodes.
		// Using DP. This method is a little bit like Bellman Ford's
		// But it adds middle-node concept.
		// Also this is all pairs shortest pathes
		
		// Intuition:
		// Let dp[i,j,k] denotes the distance of shortest path from i to j
		// using only node 0 to node k.
		// dp[i,j,k] = Min(dp[i,j,k-1], dp[i,k,k-1] + dp[k,j,k-1])
		// Also the dependency of dp[i,j,k] only on the last state,
		// so we can reduce space to O(n^2).
		
		int[][] d = new int[nodes][nodes];
		for(int i = 0; i < nodes; i++) Arrays.fill(d[i], Integer.MAX_VALUE);
		for(int i = 0; i < nodes; i++) d[i][i] = 0;
		for(int i = 0; i < edges.length; i++) d[edges[i][0]][edges[i][2]] = edges[i][1];
		
		for(int k = 0; k < nodes; k++) {
			for(int i = 0; i < nodes; i++) {
				for(int j = 0; j < nodes; j++) {
					if(d[i][k] != Integer.MAX_VALUE && d[k][j] != Integer.MAX_VALUE) {
						d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
					}
				}
			}
		}
		return d;
	}

}
