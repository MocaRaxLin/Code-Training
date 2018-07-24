package Graph;

import java.util.Arrays;

import Util.Parser;

public class No684RedundantConnection {

	public static void main(String[] args) {
		No684RedundantConnection sol = new No684RedundantConnection();
		Parser parser = new Parser();
		String t = "[[1,2],[1,3],[2,3]]\n" + 
				"[[3,4],[1,2],[2,4],[3,5],[2,5]]\n" + 
				"[[2,3],[5,2],[1,5],[4,2],[4,1]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] edges = parser.parseMatrix(s[i]);
			int[] ans = sol.findRedundantConnection(edges);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] findRedundantConnection(int[][] edges) {
        // --> time = O(EV), where E is the number of edges and V is the number of vertices.
        
        // Intution:
        // Special case in Kruskal's algorithm of each edge having cost of 1.
        // To find the edge making cycle, we should think about sets representing as MST.
		// That is Union Find
        
        // source: http://www.csie.ntnu.edu.tw/~u91029/SpanningTree.html#2
        
        // eg. [[3,4],[1,2],[2,4],[3,5],[2,5]]
        
        int N = 0;
        for(int i = 0; i < edges.length; i++){
            N = Math.max(N, edges[i][0]);
            N = Math.max(N, edges[i][1]);
        }
        
        int[] p = new int[N+1]; // parent of node x is p[x];
        for(int i = 0; i < p.length; i++) p[i] = i; // each node is belong to its own set.
        
        for(int[] e: edges){
            int pA = setOf(p, e[0]);
            int pB = setOf(p, e[1]);
            //System.out.println("p["+e[0]+"]: "+pA +", p["+e[1]+"]: "+ pB);
            if(pA == pB){
                //System.out.println();
                return e;
            }else{
                union(p, e[0], pB, e[1]);
            }
        }
        //System.out.println();
        return new int[]{-1, -1};
    }
    
    public void union(int[] p, int u, int ancestorV, int v){
        p[ancestorV] = u;
        if(p[u] == 0) p[u] = u;
    }
    
    public int setOf(int[] p, int x){
        return p[x] == x ? p[x]: setOf(p, p[x]);
    }
}
