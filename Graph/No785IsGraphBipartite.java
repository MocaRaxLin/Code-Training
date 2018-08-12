package Graph;

import Util.Parser;

public class No785IsGraphBipartite {

	public static void main(String[] args) {
		No785IsGraphBipartite sol = new No785IsGraphBipartite();
		Parser parser = new Parser();
		String t = "[[1,3],[0,2],[1,3],[0,2]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] adj = parser.parseMatrix(s[i]);
			boolean ans = sol.isBipartite(adj);
			System.out.println(ans);
		}
	}
	
    public boolean isBipartite(int[][] adj) {
        // O(V+E), because of DFS
        
        // Intuition:
        // Use DFS to set color for each node, if we meet conflict return false.
    	// Otherwise we set colors successfully and return true.
        
        int V = adj.length;
        // 1 or -1 for color, 0 means haven't seen.
        int[] token = new int[V];
        for(int i = 0; i < V; i++){
            if(token[i] != 0) continue;
            if(!DFS(adj, token, i, 1)) return false;
        }
        return true;
    }
    
    private boolean DFS(int[][] adj, int[] token, int u, int color){
        if(token[u] == color) return true;
        else if(token[u] + color == 0) return false; // (1,-1) (-1,1) conflict
        token[u] = color;
        for(int i = 0; i < adj[u].length; i++){
            if(!DFS(adj, token, adj[u][i], -color)) return false;
        }
        return true;
    }

}
