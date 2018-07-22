package Graph;

import java.util.Arrays;

import Util.Parser;

public class No685RedundantConnectionII {

	public static void main(String[] args) {
		No685RedundantConnectionII sol = new No685RedundantConnectionII();
		Parser parser = new Parser();
		String t = "[[1,2],[1,3],[2,3]]\n" + 
				"[[2,1],[3,1],[4,2],[1,4]]\n" + 
				"[[4,2],[1,5],[5,2],[5,3],[2,4]]\n" + 
				"[[1,2],[2,3],[3,4],[4,1],[1,5]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] edges = parser.parseMatrix(s[i]);
			int[] ans = sol.findRedundantDirectedConnection(edges);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] findRedundantDirectedConnection(int[][] edges) {
        // --> time = O(EV), where E is the number of edges and V is the number of vertices
        // E + V + EV
        
        // source:
        // https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C++Java-Union-Find-with-explanation-O(n)
        
        // case 1: There is a ndoe has 2 parents.
		// eg. [[2,1], [3,1], [4,2], [1,4]]
        // case 2: There is a node v has 2 child nodes, one of them makes a circle back to v.
		// eg. [[1,2], [2,3], [3,1], [1,4]]
        
        // Union Find
        // 1) Check whether there is a node having two parents.
        //    If so, store them as candidates A and B, and set the second edge invalid.
        // 2) Perform normal union find.
        //    If the tree is now valid -> simply return candidate B.
        // .  Else (we found a circle)
        //        if candidates not existing (for case 2) -> return current edge.
        //        else remove candidate A instead of B.
        
        int[] A = {-1, -1};
        int[] B = {-1, -1};
        int[] p = new int[edges.length + 1];
        // find out 2 edges share the same parent
        for(int[] e: edges){
            if(p[e[1]] == 0){
                p[e[1]] = e[0];
            }else{
                A = new int[]{p[e[1]], e[1]};
                B = new int[]{e[0], e[1]};
                e[1] = 0; // mark candidate edge B.
            }
        }
        // if A and B are still {-1, -1} then this is case 2.
        
        // set up set roots
        for(int i = 0; i < p.length; i++) p[i] = i;
        
        // Union find
        for(int[] e: edges){
            if(e[1] == 0) continue;
            int pU = setOf(p, e[0]);
            if(pU == e[1]){
                if(A[0] == -1) return e;
                else return A;
            }
            else p[e[1]] = e[0];
        }
        return B;
    }
    
    public int setOf(int[] p, int x){
        return p[x] == x? x: setOf(p, p[x]);
    }
}
