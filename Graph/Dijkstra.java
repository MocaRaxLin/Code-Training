package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;



public class Dijkstra {

	public static void main(String[] args) {
		Dijkstra sol = new Dijkstra();
		
		int nodes = 9;
		// -> cost of edges always > 0
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
		int[] distance = sol.DijkstraAt(nodes, edges, source);
		System.out.println("Dijkstra's from node "+ source+ ":");
		for(int i = 0; i < nodes; i++) {
			System.out.println("to Node " + i + " cost "+ distance[i]);
		}
	}
	
	public int[] DijkstraAt(int nodes, int[][] edges, int source) {
		// time --> O(m+nlogn) using Fibonacci heap,
		// space --> O(n),
		// where n = nodes, m = edges.length, m <= n*(n-1) = O(n^2)
		
		// Dijkstra is a greedy method.
		// Like Prim's
		// Start from 2 sets.
		// One is for vertices in MST, the other is for vertices not included yet.
		// Include source to MST and set distance of source equal to 0.
		// Then for each step, we pick a vertex from not-included set
		// and has minimal distance to "SOURCE" not MST.
		// Stop when all vertices are included.
		
		// pre processing -> O(n+m)
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
		
		// init distances
		int[] dis = new int[nodes];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[source] = 0;
		
		// init MST set
		boolean[] inMST = new boolean[nodes];
		int sizeMST = 0;
		PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] i, int[] j) {return i[1] - j[1]; } // [vertex, distance]
		});
		for(int i = 0; i < nodes; i++) minHeap.offer(new int[] {i, dis[i]});
		
		
		// update distance label
		// Time complexity of Dijkstra is O(|E| |decrease-key(Q)| + |V| |extract-min(Q)|)
		// source: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
		// source: https://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
		//
		// The max number of extract-min from queue: n
		// The max number of decrease-key operations: m, it is O(1) for F-heap
		// 
		// -> O(m + nlogn) if we use Fibonacci heap.
		// -> O((n+m)logn) if we use mininal heap.
		
		while(sizeMST < nodes) {
			int[] tmp = minHeap.poll(); // extract-min
			int v = tmp[0];
			// skip visited vertices, because we have visited them with shorter path.
			if(inMST[v]) {
				continue;
			}else {
				inMST[v] = true;
				sizeMST++;
			}
			
			Iterator<Integer> innerIt = adj.get(v).iterator();
			while(innerIt.hasNext()) {
				int next = innerIt.next();
				// minHeap.remove(new int[] {next, dis[next]});
				dis[next] = Math.min(dis[next], dis[v] + cost[v][next]);
				minHeap.offer(new int[] {next, dis[next]}); //sorry we can hardly implement decrease key
			}
		}
		
		return dis;
	}

}



