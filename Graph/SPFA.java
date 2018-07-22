package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SPFA {

	public static void main(String[] args) {
		SPFA sol = new SPFA();
		
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
		int[] distance = sol.SPFAAt(nodes, edges, source);
		System.out.println("SFPA from node "+ source+ ":");
		for(int i = 0; i < nodes; i++) {
			System.out.println("to Node " + i + " cost "+ distance[i]);
		}
	}

	private int[] SPFAAt(int nodes, int[][] edges, int source) {
		// Improvement of Bellman-Ford,
		// but still slower than Dijkstra's for special test cases.
		
		// Build map
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		for(int i = 0; i < nodes; i++) adj.add(new LinkedList<Integer>());
		int[][] cost = new int[nodes][nodes]; // if cost[u][v] = 0, it means (u,v) does not exist
		for(int[] e: edges) {
			adj.get(e[0]).add(e[1]);
			adj.get(e[1]).add(e[0]);
			cost[e[0]][e[1]] = e[2];
			cost[e[1]][e[0]] = e[2]; // undirected edges
		}
		
		int[] dis = new int[nodes];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[source] = 0;
		
		
		// Because we update when we find a shorter distance,
		// the expanded nodes may have shorter distance too.
		// Therefore whenever we update distance of a ndoe we put this node into queue for next expand.
		
		// --> time = O(kE), where k <= V
		// note: Bellman-Ford's takes O(VE)
		//       Djikstra's takes O(E+VlogV), O((E+V)logV).
		//
		// There are some test cases make SPFA slower than Djikstra's
		// i.e. kE > E+VlogV for k < V is possible.
		//      kE = O(V^3), E+VlogV = O(V^2)
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(source);
		while(q.size() > 0) {
			int u = q.poll();
			Iterator<Integer> it = adj.get(u).iterator();
			while(it.hasNext()) {
				int v = it.next();
				if(dis[v] > dis[u] + cost[u][v]) {
					dis[v] = dis[u] + cost[u][v];
					q.offer(v);
				}
			}
		}
		
		return dis;
	}

}
