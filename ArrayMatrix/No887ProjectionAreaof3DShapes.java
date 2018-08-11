package ArrayMatrix;

import Util.Parser;

public class No887ProjectionAreaof3DShapes {

	public static void main(String[] args) {
		No887ProjectionAreaof3DShapes sol = new No887ProjectionAreaof3DShapes();
		Parser parser = new Parser();
		String t = "[[2]]\n" + 
				"[[1,2],[3,4]]\n" + 
				"[[1,1,1],[1,0,1],[1,1,1]]\n" + 
				"[[1,0],[0,2]]\n" + 
				"[[2,2,2],[2,1,2],[2,2,2]]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] grid = parser.parseMatrix(s[i]);
			int ans = sol.projectionArea(grid);
			System.out.println(ans);
		}
	}

    public int projectionArea(int[][] grid) {
        // --> O(nm)
        
        int n = grid.length; if(n == 0) return 0;
        int m = grid[0].length; if(m == 0) return 0;
        
        int sumXY = 0;
        int[] maxCol = new int[m]; // collect the highest tower
        int[] maxRow = new int[n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] > 0) sumXY++;
                if(grid[i][j] > maxRow[i]) maxRow[i] = grid[i][j];
                if(grid[i][j] > maxCol[j]) maxCol[j] = grid[i][j];
            }
        }
        
        int sumXZ = 0, sumYZ = 0;
        for(int i = 0; i < n; i++) sumXZ += maxRow[i];
        for(int i = 0; i < m; i++) sumYZ += maxCol[i];
        
        return sumXY + sumXZ + sumYZ;
    }
}
