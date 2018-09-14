package DP;

import Util.Parser;
import Util.Show;

public class No54201Matrix {

	public static void main(String[] args) {
		No54201Matrix sol = new No54201Matrix();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[0,0,0],[0,1,0],[0,0,0]]\n" + 
				"[[0,0,0],[0,1,0],[1,1,1]]\n" + 
				"[[0,0,0,0,0,0,0],[0,1,1,1,1,1,0],[0,1,1,1,1,1,0],[0,1,1,1,1,1,0],[0,1,1,1,1,1,0],[0,1,1,1,1,1,0],[0,0,0,0,0,0,0]]\n" + 
				"[[1, 0, 1, 1, 0, 0, 1, 0, 0, 1], [0, 1, 1, 0, 1, 0, 1, 0, 1, 1], [0, 0, 1, 0, 1, 0, 0, 1, 0, 0], [1, 0, 1, 0, 1, 1, 1, 1, 1, 1], [0, 1, 0, 1, 1, 0, 0, 0, 0, 1], [0, 0, 1, 0, 1, 1, 1, 0, 1, 0], [0, 1, 0, 1, 0, 1, 0, 0, 1, 1], [1, 0, 0, 0, 1, 1, 1, 1, 0, 1], [1, 1, 1, 1, 1, 1, 1, 0, 1, 0], [1, 1, 1, 1, 0, 1, 0,0,1,1]]\n" + 
				"[[0]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] matrix = parser.parseMatrix(s[i]);
			int[][] ans = sol.updateMatrix(matrix);
			show.showMatrix(ans);
		}
	}

	public int[][] updateMatrix(int[][] matrix) {
        // --> O(nm), (n,m) is size of matrix.
        
        // Thanks to:
        // https://leetcode.com/problems/01-matrix/discuss/101051/Simple-Java-solution-beat-99-(use-DP)
        
        // Intuition:
        // Another complicated dp + DFS, so hold on and analyze more!
        //
        // This is a 2 sweap DP solution. 
        // get optimal answer from top left, then bottom right.
        // Becareful the comparison, dp[i][j] stores a possible answer already.
        // Thus we use tmp = MIN(bot, right) + 1, and then MIN(dp[i][j], tmp) :)
        
        int[][] dp = new int[0][0];
        int n = matrix.length; if(n == 0) return dp;
        int m = matrix[0].length; if(m == 0) return dp;
        dp = new int[n][m];
        int LIMIT = n*m;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] != 0){
                    int top = i > 0? dp[i-1][j]: LIMIT;
                    int left = j > 0? dp[i][j-1]: LIMIT;
                    dp[i][j] = Math.min(top, left) + 1;
                }
            }
        }
        
        for(int i = n-1; i >= 0; i--){
            for(int j = m-1; j >= 0; j--){
                if(matrix[i][j] != 0){
                    int bot = i < n-1? dp[i+1][j]: LIMIT;
                    int right = j < m-1? dp[i][j+1]: LIMIT;
                    int tmp = Math.min(bot, right) + 1;
                    dp[i][j] = Math.min(dp[i][j], tmp);
                }
            }
        }
        
        return dp;
    }
}
