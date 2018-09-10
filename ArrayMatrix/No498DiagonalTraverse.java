package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No498DiagonalTraverse {

	public static void main(String[] args) {
		No498DiagonalTraverse sol = new No498DiagonalTraverse();
		Parser parser = new Parser();
		String t = "[[1,2,3],[4,5,6],[7,8,9]]";
		String[] s = t.split("\n");
		for(int i  = 0 ; i < s.length; i++) {
			int[][] matrix = parser.parseMatrix(s[i]);
			int[] ans = sol.findDiagonalOrder(matrix);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] findDiagonalOrder(int[][] matrix) {
        // --> O(n*m), where (n, m) is the size of matrix
        
        // Just simulate
        
        int n = matrix.length; if(n == 0) return new int[0];
        int m = matrix[0].length; if(m == 0) return new int[0];
        int i = 0, j = 0, idx = 0;
        int[] ret = new int[n*m];
        boolean up = true;
        while(i < n && j < m){
            ret[idx++] = matrix[i][j];
            if(up){
                if(inBound(i-1, n, j+1, m)){
                    i = i-1; j = j+1;
                }else if(inBound(i, n, j+1, m)){ //try right
                    j = j+1; up = !up;
                }else if(inBound(i+1, n, j, m)){ // try bot
                    i = i+1; up = !up;
                }else{
                    i++; j++; // hit end;
                }
            }else{
                if(inBound(i+1, n, j-1, m)){
                    i = i+1; j = j-1;
                }else if(inBound(i+1, n, j, m)){ // try bot
                    i = i+1; up = !up;
                }else if(inBound(i, n, j+1, m)){ // try right
                    j = j+1; up = !up;
                }else{
                    i++; j++; // hit end;
                }
            }
        }
        return ret;
    }
    
    private boolean inBound(int i, int n, int j, int m){
        return 0 <= i && i < n && 0 <= j && j < m;
    }
}
