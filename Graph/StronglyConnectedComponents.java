package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class StronglyConnectedComponents {

	public static void main(String[] args) {
		StronglyConnectedComponents sol = new StronglyConnectedComponents();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[0,1],[1,2],[2,0]]\n"
				+ "3\n"
				+ "[[1,0],[0,2],[2,1],[0,3],[3,4]]\n"
				+ "5\n"
				+ "[[0,1],[3,0],[1,3],[1,2],[2,3]]\n"
				+ "4\n"
				+ "[[7,5],[5,6],[7,4],[5,4],[6,7],[7,0],[0,1],[1,0],[0,3],[0,2],[1,2],[3,2],[4,3],[3,4]]\n"
				+ "8\n"
				+ "[[0,3],[3,2],[2,1],[1,0],[4,2],[5,4],[4,6],[6,5],[6,7],[8,7]]\n"
				+ "9\n";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] E = parser.parseMatrix(s[i]);
			int V = Integer.parseInt(s[i+1]);
			List<List<Integer>> ans1 = sol.Kosaraju(V, E);
			System.out.print("Kosaraju's SCC: ");
			show.showListListInt(ans1);
		}
		
	}

	public List<List<Integer>> Kosaraju(int V, int[][] E) {
		// --> time = O(V+E) in linear time.
		
		// Build graph of adjacency lists
		List<List<Integer>> adj = new ArrayList<List<Integer>>();
		for(int i = 0; i < V; i++) adj.add(new LinkedList<Integer>());
		for(int[] e: E) adj.get(e[0]).add(e[1]);
		
		int[] stack = new int[V+1];// size store at index 0 default is 1.
		stack[0] = 1;
		boolean[] seen = new boolean[V];
		for(int i = 0; i < V; i++) {
			if(seen[i]) continue;
			postorderDFS(adj, i, seen, stack);
		}
		//System.out.println(Arrays.toString(stack));
		
		// Build reversed graph
		List<List<Integer>> reverseGraph = new ArrayList<List<Integer>>();
		for(int i = 0; i < V; i++) reverseGraph.add(new LinkedList<Integer>());
		for(int[] e: E) reverseGraph.get(e[1]).add(e[0]);
		
		// Collect SCC
		List<List<Integer>> SCC = new LinkedList<List<Integer>>();
		seen = new boolean[V];
		while(stack[0] > 1) { // stack size > 0
			int root = stack[--stack[0]]; // stack pop
			if(seen[root]) continue;
			List<Integer> component = new LinkedList<Integer>();
			findPathDFS(reverseGraph, root, seen, component);
			SCC.add(component);
		}
		return SCC;
	}

	private void findPathDFS(List<List<Integer>> reverseGraph, int root, boolean[] seen, List<Integer> component) {
		if(seen[root]) return;
		component.add(root);
		seen[root] = true;
		Iterator<Integer> it = reverseGraph.get(root).iterator();
		while(it.hasNext()) findPathDFS(reverseGraph, it.next(), seen, component);
	}

	private void postorderDFS(List<List<Integer>> adj, int node, boolean[] seen, int[] stack) {
		if(seen[node]) return;
		seen[node] = true;
		Iterator<Integer> it = adj.get(node).iterator();
		while(it.hasNext()) postorderDFS(adj, it.next(), seen, stack);
		stack[stack[0]++] = node;
	}

}
