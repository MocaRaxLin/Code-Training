package DP;

import java.util.Arrays;

import Util.Parser;

public class No764LargestPlusSign {

	public static void main(String[] args) {
		No764LargestPlusSign sol = new No764LargestPlusSign();
		Parser parser = new Parser();
		
		String testcase = "5\n" + 
				"[[4,2]]\n" + 
				"2\n" + 
				"[]\n" + 
				"1\n" + 
				"[[0,0]]";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int N = Integer.parseInt(s[i]);
			int[][] mines = parser.parseMatrix(s[i+1]);
			int ans = sol.orderOfLargestPlusSign1(N, mines);
			System.out.println(ans);
		}
	}
	
	private int orderOfLargestPlusSign1(int N, int[][] mines) {
		// 67 ms !?
        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++)  Arrays.fill(grid[i], N); // O(N^2) make a large number
        for (int[] m : mines) grid[m[0]][m[1]] = 0; // O(N^2) same
        
        // Optimal Part!
        // Nice Design
        // [i,j] for left, [i,k] for right, [j,i] for up, [k,i] for down
        // L: iterate j with l and go to the next row i + 1
        // R: iterate k with r and go to the next row i + 1
        // U: iterate j with u and to to the next column i + 1
        // D: iterate k with d and to to the next column i + 1
        //
        // only three index variables:
        // i = 0 to N-1
        // j = 0 to N-1
        // k = N-1 to 0
        for (int i = 0; i < N; i++) {
            for (int j = 0, k = N - 1, l = 0, r = 0, u = 0, d = 0; j < N; j++, k--) {
                grid[i][j] = Math.min(grid[i][j], l = (grid[i][j] == 0 ? 0 : l + 1));
                grid[i][k] = Math.min(grid[i][k], r = (grid[i][k] == 0 ? 0 : r + 1));
                grid[j][i] = Math.min(grid[j][i], u = (grid[j][i] == 0 ? 0 : u + 1));
                grid[k][i] = Math.min(grid[k][i], d = (grid[k][i] == 0 ? 0 : d + 1));
            }
        }
        
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res = Math.max(res, grid[i][j]);// O(N^2) but only one comparison
            }
        }
        return res;
	}

	public int orderOfLargestPlusSign(int N, int[][] mines) {
		// --> time O(N^2), space O(N^2)
		// 191ms
		
		// Intuition:
		// Brute force is O(N^3).
		// This method is we pick a center in A and extend to up, left, right, down.
		// But we can pre processing to get the longest consecutive 0 in each directions (non-mine place)
		// to reduce time complexity by O(n)
		// Finally we calculate the minimum length in each direction at position (i,j),
		// and find the max-order plus sign in the whole matrix A.
        int[][] A = new int[N][N];
        for(int i = 0; i < mines.length; i++){
            A[mines[i][0]][mines[i][1]] = 1;
        }
        int[][] U = new int[N][N];
        int[][] L = new int[N][N];
        for(int i = 0; i < N; i++) U[0][i] = A[0][i] == 0? 1: 0;
        for(int i = 0; i < N; i++) L[i][0] = A[i][0] == 0? 1: 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i > 0) U[i][j] = A[i][j] == 0? U[i-1][j] + 1: 0;
                if(j > 0) L[i][j] = A[i][j] == 0? L[i][j-1] + 1: 0;
            }
        }
        int[][] D = new int[N][N];
        int[][] R = new int[N][N];
        for(int i = 0; i < N; i++) D[N-1][i] = A[N-1][i] == 0? 1: 0;
        for(int i = 0; i < N; i++) R[i][N-1] = A[i][N-1] == 0? 1: 0;
        for(int i = N - 1; i >= 0; i--){
            for(int j = N - 1; j >= 0; j--){
                if(i < N - 1) D[i][j] = A[i][j] == 0? D[i+1][j] + 1: 0;
                if(j < N - 1) R[i][j] = A[i][j] == 0? R[i][j+1] + 1: 0;
            }
        }
        
        int ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int orderAt = Integer.MAX_VALUE;
                orderAt = Math.min(orderAt, U[i][j]);
                orderAt = Math.min(orderAt, L[i][j]);
                orderAt = Math.min(orderAt, D[i][j]);
                orderAt = Math.min(orderAt, R[i][j]);
                ret = Math.max(ret, orderAt);
            }
        }
        return ret;
    }
}
