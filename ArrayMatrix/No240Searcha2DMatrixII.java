package ArrayMatrix;

import Util.Parser;

public class No240Searcha2DMatrixII {

	public static void main(String[] args) {
		No240Searcha2DMatrixII sol = new No240Searcha2DMatrixII();
		Parser parser = new Parser();
		String t = "[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]\n" + 
				"5\n" + 
				"[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]\n" + 
				"20";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] matrix = parser.parseMatrix(s[i]);
			int target = Integer.parseInt(s[i+1]);
			boolean ans = sol.searchMatrix(matrix, target);
			System.out.println(ans);
		}
	}

	public boolean searchMatrix(int[][] matrix, int target) {
		// --> O(n+m), where (n,m) is the size of matrix.
		
		// Intuition:
		// You may want to use devide and conquer to handle this question,
		// but calm down first!
		
		// Thanks to:
		// https://leetcode.com/problems/search-a-2d-matrix-ii/discuss/66140/My-concise-O(m+n)-Java-solution
		//
		// We can search from top-right corner and
		// when ever matrix[i][j] < target,
		// row i from 0 to j won't be target so we check the next row (i++).
		// on the other hand, when ever matrix[i][j] > target,
		// column j from i to n-1 won't be target so we check the previous column (j--).
		
        int n = matrix.length; if(n == 0) return false;
        int m = matrix[0].length; if(m == 0) return false;
        
        int i = 0, j = m-1; //init at top right corner
        while(0 <= j && i < n){
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }
}
