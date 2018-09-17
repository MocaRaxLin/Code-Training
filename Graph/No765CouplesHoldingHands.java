package Graph;

import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class No765CouplesHoldingHands {

	public static void main(String[] args) {
		No765CouplesHoldingHands sol = new No765CouplesHoldingHands();
		Parser parser = new Parser();
		String t = "[0,2,1,3]\n" + 
				"[6,5,9,7,2,3,11,1,10,4,8,0]\n" + 
				"[2,3,1,0,5,4]\n" + 
				"[0,2,3,4,1,8,7,6,5,9]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] row = parser.parseArray(s[i]);
			int ans = sol.minSwapsCouples(row);
			System.out.println(ans);
		}
		
	}

	public int minSwapsCouples(int[] row) {
        // --> O(E), where E = row.length/2
        
        // Intuition:
        // Think of graph. Set (0,1) as node 0, (2,3) as node 1...
        // (2k,2k+1) as node k
        // 2 people sit together like (p,q), then we build an edge for node a = p/2 and b = q/2
        // If a = b, it means they are all set, don't build the edge.
        // 
        // The min swaps times is the number of acyclic edge that connect all nodes with edges.
        // (Ignore those set couples)
        
        // eg. [0,2,3,4,1,8,7,6,5,9]
        // (0) - (1) - (2) - (4)   (3)
        //   \---------------/
        // then we return 3, because 3 acyclic edge in (0) - (1) - (2) - (4)
        
        int N = row.length; if(N == 2) return 0;
        int n = N/2;
        
        // build graph
        List[] adj = new List[n]; // new way to build graph :)
        for(int i = 0; i < n; i++) adj[i] = new LinkedList<Integer>();
        for(int i = 0; i < row.length; i+=2){
            int a = row[i]/2;
            int b = row[i+1]/2;
            if(a == b) continue;
            adj[a].add(b);
            adj[b].add(a);
        }
        
        /*
        for(int i = 0; i < adj.length; i++)
            System.out.print(" ,(" + i+"):" + Arrays.toString(adj[i].toArray()));
        System.out.println();
        */
        
        // count the acyclic edges by dfs in a tree
        count = 0;
        boolean[] seen = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!seen[i]) dfs(adj, i, seen);
        }
        return count;
    }
    
    int count;
    private void dfs(List<Integer>[] adj, int u, boolean[] seen){
        seen[u] = true;
        for(Integer v: adj[u]){
            if(seen[v]) continue;
            count++;
            dfs(adj, v, seen);
        }
    }
}
