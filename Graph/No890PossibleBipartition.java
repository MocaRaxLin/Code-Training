package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class No890PossibleBipartition {

	public static void main(String[] args) {
		No890PossibleBipartition sol = new No890PossibleBipartition();
		Parser parser = new Parser();
		String t = "4\n" + 
				"[[1,2],[1,3],[2,4]]\n" + 
				"3\n" + 
				"[[1,2],[1,3],[2,3]]\n" + 
				"5\n" + 
				"[[1,2],[2,3],[3,4],[4,5],[1,5]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int N = Integer.parseInt(s[i]);
			int[][] dislikes = parser.parseMatrix(s[i+1]);
			boolean ans = sol.possibleBipartition(N, dislikes);
			System.out.println(ans);
		}
	}

    public boolean possibleBipartition(int N, int[][] dislikes) {
    	// --> O(V+E), V = N-1, E = dislike.length * 2.
    	
    	// Intuition:
    	// Same as No 785. Is Graph Bipartite?
    	// Use DFS to set color for each node, if we meet conflict return false.
    	// Otherwise we set colors successfully and return true.
    	
    	// Careful:
    	// Use hash set here is dangerous,
    	// because if there is a pair (p1, p2) which both p1 and p2 haven't in set A or B.
    	// Let put them randomly A:{p3, p4, p1}, B:{p5, p2},
    	// then the next pair is (p4, p1) ... G_G
    	// It would be valid if p1 in B and p2 in A.
    	
    	
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        for(int i = 0; i <= N; i++) adj.add(new LinkedList<Integer>());
        for(int[] e: dislikes){
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        int[] token = new int[N+1]; // 1 or -1 for color, 0 means haven't seen.
        for(int i = 0; i <= N; i++){
            if(token[i] != 0) continue;
            if(!DFS(adj, token, i, 1)) return false;
        }
        return true;
    }
    
    private boolean DFS(List<List<Integer>> adj, int[] token, int u, int color){
        if(token[u] == color) return true; // u has been met
        if(token[u] + color == 0) return false; // (1,-1) (-1,1) conflict
        token[u] = color;
        Iterator<Integer> i = adj.get(u).iterator();
        while(i.hasNext()){
            if(!DFS(adj, token, i.next(), -color)) return false;
        }
        return true;
    }
}
