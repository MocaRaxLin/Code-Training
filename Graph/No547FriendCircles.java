package Graph;

import Util.Parser;

public class No547FriendCircles {

	public static void main(String[] args) {
		No547FriendCircles sol = new No547FriendCircles();
		Parser parser = new Parser();
		String t = "[[1,1,0],[1,1,0],[0,0,1]]\n" + 
				"[[1,1,0],[1,1,1],[0,1,1]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] M = parser.parseMatrix(s[i]);
			int ans = sol.findCircleNum(M);
			System.out.println(ans);
		}
	}

	public int findCircleNum(int[][] M) {
        int N = M.length; if(N == 0) return 0;
        boolean[] seen = new boolean[N];
        int count = 0;
        for(int i = 0; i < N; i++){
            if(!seen[i]){
                dfs(M, i, seen);
                count++;
            }
        }
        return count;
    }
    
    private void dfs(int[][] M, int u, boolean[] seen){
        if(seen[u]) return;
        seen[u] = true;
        for(int v = 0; v < M.length; v++){
            if(M[u][v] == 1) dfs(M, v, seen);
        }
    }
}
