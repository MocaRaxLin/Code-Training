package ArrayMatrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class No54SpiralMatrix {

	public static void main(String[] args) {
		No54SpiralMatrix sol = new No54SpiralMatrix();
		Parser parser = new Parser();
		String t = "[[1,2,3],[4,5,6],[7,8,9]]\n" + 
				"[[1, 2, 3, 4],[5, 6, 7, 8],[9,10,11,12]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] matrix = parser.parseMatrix(s[i]);
			List<Integer> ans = sol.spiralOrder(matrix);
			System.out.println(Arrays.toString(ans.toArray()));
		}
	}

	public List<Integer> spiralOrder(int[][] matrix) {
        // --> O(nm), (n,m) is the size of matrix
        // Just spiral
        List<Integer> ret = new LinkedList<Integer>();
        int n = matrix.length; if(n == 0) return ret;
        int m = matrix[0].length; if(m == 0) return ret;
        
        boolean[][] seen = new boolean[n][m];
        int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int d = 0; // right -> down -> left -> up -> right
        int i = 0, j = 0;
        while(!seen[i][j]){
            ret.add(matrix[i][j]);
            seen[i][j] = true;
            if(!inBoard(i+dir[d][0], j+dir[d][1], n, m) || seen[i+dir[d][0]][j+dir[d][1]]) d = (d+1)%4;
            if(!inBoard(i+dir[d][0], j+dir[d][1], n, m) || seen[i+dir[d][0]][j+dir[d][1]]) break;
            i += dir[d][0];
            j += dir[d][1];
        }
        return ret;
    }
    
    private boolean inBoard(int i, int j, int n, int m){
        return 0 <= i && 0 <=j && i < n && j < m;
    }
}
