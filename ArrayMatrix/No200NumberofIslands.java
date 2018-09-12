package ArrayMatrix;

import Util.Parser;

public class No200NumberofIslands {

	public static void main(String[] args) {
		No200NumberofIslands sol = new No200NumberofIslands();
		Parser parser = new Parser();
		String t = "[[\"1\",\"1\",\"1\",\"1\",\"0\"],[\"1\",\"1\",\"0\",\"1\",\"0\"],[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"0\",\"0\",\"0\",\"0\",\"0\"]]\n" + 
				"[[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"1\",\"1\",\"0\",\"0\",\"0\"],[\"0\",\"0\",\"1\",\"0\",\"0\"],[\"0\",\"0\",\"0\",\"1\",\"1\"]]\n" + 
				"[[\"1\"]]\n" + 
				"[[\"1\"],[\"1\"]]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] grid = parser.parseMatrix(s[i]);
			int ans = sol.numIslands(grid);
			System.out.println(ans);
		}
	}

	public int numIslands(int[][] grid) {
        int n = grid.length; if(n == 0) return 0;
        int m = grid[0].length; if(m == 0) return 0;
        int count = 0;
        boolean[][] seen = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 && !seen[i][j]){
                    dfs(grid, i, j, n, m, seen);
                    count++;
                }
            }
        }
        return count;
    }
    
    private void dfs(int[][] grid, int i, int j, int n, int m, boolean[][] seen){
        if(i < 0 || n == i || j < 0 || m == j || grid[i][j] == 0|| seen[i][j]) return;
        seen[i][j] = true;
        dfs(grid, i+1, j, n, m, seen);
        dfs(grid, i, j+1, n, m, seen);
        dfs(grid, i-1, j, n, m, seen);
        dfs(grid, i, j-1, n, m, seen);
    }
}
