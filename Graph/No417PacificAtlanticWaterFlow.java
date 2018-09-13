package Graph;

import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No417PacificAtlanticWaterFlow {

	public static void main(String[] args) {
		No417PacificAtlanticWaterFlow sol = new No417PacificAtlanticWaterFlow();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]\n" + 
				"[[10,10,10],[10,1,10],[10,10,10]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] matrix = parser.parseMatrix(s[i]);
			List<int[]> ans = sol.pacificAtlantic(matrix);
			show.showListIntArray(ans);
		}
	}

	public List<int[]> pacificAtlantic(int[][] matrix) {
        // --> O(nm), where (n,m) is the size of matrix
        
        // In the begining, I give too complicated solution using DP + DFS,
        // and I found I can only get solution after I collected solution in 4 adjcent cells.
        // However the contradiction is we will never get solution, 
        // because 2 neighbor cell will do recursion back and forth,
        // even setting a from cell will be much more complicated. 
        //
        // So DON'T be stupid!!!!!!!!!!!!!!!!!!!!!!
        // 
        // Once again, think CLEAR than code!
        // DON'T code blindly and BEAT around the BUSH.
        
        // Thanks to:
        // https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/90733/Java-BFS-and-DFS-from-Ocean
        
        // Intuition:
        // Floods come from ocean, and let water goes up
        // If 2 floods can reach same cells, this cell is what we want.
        
        List<int[]> ret = new LinkedList<int[]>();
        int n = matrix.length; if(n == 0) return ret;
        int m = matrix[0].length; if(m == 0) return ret;
        
        boolean[][] atlantic = new boolean[n][m];
        boolean[][] pacific = new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            if(!pacific[i][0]) dfs(matrix, i, 0, n, m, pacific);
            if(!atlantic[i][m-1]) dfs(matrix, i, m-1, n, m, atlantic);
        }
        for(int j = 0; j < m; j++){
            if(!pacific[0][j]) dfs(matrix, 0, j, n, m, pacific);
            if(!atlantic[n-1][j]) dfs(matrix, n-1, j, n, m, atlantic);
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(atlantic[i][j] && pacific[i][j]) ret.add(new int[]{i, j});
            }
        }
        
        return ret;
    }
    
    private void dfs(int[][] matrix, int i, int j, int n, int m, boolean[][] ocean){
        if(ocean[i][j]) return;
        ocean[i][j] = true;
        
        int h = matrix[i][j];
        if(i != 0 && h <= matrix[i-1][j]) dfs(matrix, i-1, j, n, m, ocean);
        if(j != 0 && h <= matrix[i][j-1]) dfs(matrix, i, j-1, n, m, ocean);
        if(i != n-1 && h <= matrix[i+1][j]) dfs(matrix, i+1, j, n, m, ocean);
        if(j != m-1 && h <= matrix[i][j+1]) dfs(matrix, i, j+1, n, m, ocean);
    }
}
