package DP;

import Util.Parser;

public class ExtraMinSlice {

	public static void main(String[] args) {
		ExtraMinSlice sol = new ExtraMinSlice();
		Parser parser = new Parser();
		String t = "[[1,2,3],[5,4,6],[8,9,7]]\n"
				+ "[[5,4,22,6,1]]\n"
				+ "[[5],[4],[1]]\n"
				+ "[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] A = parser.parseMatrix(s[i]);
			int ans = sol.sumOfMinSlice(A);
			System.out.println(ans);
		}
	}

	private int sumOfMinSlice(int[][] A) {
		if(A.length == 0) return 0;
		
		for(int i = 1; i < A.length; i++) {
			for(int j = 0; j < A[i].length; j++) {
				int addon = A[i-1][j];
				if(0 < j) addon = Math.min(addon, A[i-1][j-1]);
				if(j < A[i].length - 1) addon = Math.min(addon, A[i-1][j+1]);
				A[i][j] += addon;
			}
		}
		
		int minSlice = Integer.MAX_VALUE;
		int lastRow = A.length - 1;
		for(int i = 0; i < A[0].length; i++) minSlice = Math.min(minSlice, A[lastRow][i]);
		return minSlice;
	}

}
