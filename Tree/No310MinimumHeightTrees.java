package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No310MinimumHeightTrees {

	public static void main(String[] args) {
		No310MinimumHeightTrees sol = new No310MinimumHeightTrees();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "4\n" + 
				"[[1,0],[1,2],[1,3]]\n" + 
				"6\n" + 
				"[[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int n = Integer.parseInt(s[i]);
			int[][] edges = parser.parseMatrix(s[i+1]);
			List<Integer> ans = sol.findMinHeightTrees(n, edges);
			show.showListInt(ans, true);
		}
	}
	
	// Thanks to:
    // https://github.com/lydxlx1/LeetCode/blob/master/src/_310_1.java
    
    List<List<Integer>> adj;
    int[] heights1;
    int[] heights2;
    int[] dp;
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // --> O(2E), where E = edges.length
        
        List<Integer> ret = new LinkedList<Integer>(); 
        if(n == 0) return ret;
        if(n == 1){ ret.add(0); return ret; }
        
        // build graph using adjecent lists
        adj = new ArrayList<List<Integer>>();
        for(int v = 0; v < n; v++) adj.add(new LinkedList<Integer>());
        for(int[] e: edges){
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        // 1st and 2nd heights rooted at node 0
        heights1 = new int[n];
        heights2 = new int[n];
        getHeight12(0, -1);
        // System.out.println(Arrays.toString(heights1));
        // System.out.println(Arrays.toString(heights2));
        
        // Let dp[v] denotes height of MHT rooted at node v
        dp = new int[n];
        setMHT(0, -1, 0);
        // System.out.println(Arrays.toString(dp));
        
        int minHeight = Integer.MAX_VALUE;
        for(int v = 0; v < dp.length; v++){
            if(dp[v] < minHeight){
                ret = new LinkedList<Integer>();
                minHeight = dp[v];
            }
            if(dp[v] <= minHeight) ret.add(v);
        }
        return ret;
    }
    
    private void setMHT(int u, int p, int pHeight){
        dp[u] = Math.max(heights1[u], pHeight);
        for(int v : adj.get(u)){
            if(v == p) continue;
            // from parent's height
            // from sibiling's height1 or 2, depends on node v provide contribute to height of u or not.
            // if h1[u] = h2[v] + 1 -> use h2[u]
            // else -> use h1[u]
            int sHeight = heights1[u] == heights1[v] + 1? heights2[u]: heights1[u];
            int nextPh = Math.max(pHeight, sHeight) + 1;
            setMHT(v, u, nextPh);
        }
    }
    
    private void getHeight12(int u, int p){
        for(int v: adj.get(u)){ // another way to iterate list
            if(v == p) continue;
            getHeight12(v, u);
            int tmp = heights1[v] + 1;
            if(tmp > heights1[u]){
                heights2[u] = heights1[u];
                heights1[u] = tmp;
            }else if(tmp > heights2[u]){
                heights2[u] = tmp;
            }
        }
    }

}
