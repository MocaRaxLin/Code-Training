package BinarySearch;


import Util.Parser;

public class No74Searcha2DMatrix {

	public static void main(String[] args) {
		No74Searcha2DMatrix sol = new No74Searcha2DMatrix();
		Parser parser = new Parser();
		String t = "[[1,3,5,7],[10,11,16,20],[23,30,34,50]]\n" + 
				"3\n" + 
				"[[1,2],[3,4]]\n" + 
				"5\n" + 
				"[[1,3]]\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0; i <s.length; i+=2) {
			int[][] matrix = parser.parseMatrix(s[i]);
			int target = Integer.parseInt(s[i+1]);
			boolean ans = sol.searchMatrix(matrix, target);
			System.out.println(ans);
		}
	}

    public boolean searchMatrix(int[][] matrix, int target) {
        // --> O(logN + logM)
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        if(n == 0) return false;
        int i = 0, j = m*n;  // becareful the edge case eg.[1,3]
        int r = 0, c = 0;
        while(j-i > 1){
            int mid = i + (j-i)/2;
            r = mid/n;
            c = mid%n;
            if(matrix[r][c] <= target) i = mid;
            else j = mid;
        }
        r = i/n;
        c = i%n;
        return matrix[r][c] == target;
    }
}
