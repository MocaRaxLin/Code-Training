package Graph;

import java.util.Arrays;

public class ExtraMinDisToClosestBuildingInParkingLot {

	public static void main(String[] args) {
		ExtraMinDisToClosestBuildingInParkingLot sol = new ExtraMinDisToClosestBuildingInParkingLot();
		
		String t = "2\n"
				+ "3\n"
				+ "2\n"
				+ "3\n"
				+ "3\n"
				+ "1\n"
				+ "5\n"
				+ "1\n"
				+ "1\n"
				+ "1\n"
				+ "5\n"
				+ "3\n"
				+ "6\n"
				+ "5\n"
				+ "1";
		
		/*
		String t = "2\n"
				+ "3\n"
				+ "2";
				*/
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int W = Integer.parseInt(s[i]);
			int H = Integer.parseInt(s[i+1]);
			int N = Integer.parseInt(s[i+2]);
			int ans = sol.findMinDistance(W, H, N);
			System.out.println(ans);
		}
	}

	int W, H, N, Range, ret;
	private int findMinDistance(int w, int h, int n) {
		W = w; H = h; N = n; Range = w*h; ret = Integer.MAX_VALUE;
		// System.out.println("W, H, N: "+ W + ", " + H + ", " + N);
		int[] pos = new int[N];
		backtracking(pos, 0);
		return ret;
	}
	
	private void backtracking(int[] pos, int p) {
		if(p == N) {
			int[][] grid = new int[H][W];
			label(grid, pos);
			
			// System.out.println(Arrays.toString(pos));
			// for(int i = 0; i < H; i++)  System.out.println(Arrays.toString(grid[i]));
			// System.out.println();
			
			int dis = findLargestDistance(grid);
			// System.out.println(dis);
			ret = Math.min(ret, dis);
			return;
		}
		int start = p == 0? 0: pos[p-1]+1;
		for(int i = start; i < Range; i++) {
			pos[p++] = i;
			backtracking(pos, p);
			p--;
		}
	}
	
	private void label(int[][] grid, int[] pos) {
		for(int i = 0; i < H; i++)
			Arrays.fill(grid[i], Integer.MAX_VALUE);
		for(int i = 0; i < N; i++) 
			manhattanDis(grid, pos[i]/W, pos[i]%W);
	}
	
	private void manhattanDis(int[][] grid, int i, int j) {
		// System.out.println("i: "+i+", j: "+ j);
		for(int r = 0; r < H; r++)
			for(int c = 0; c < W; c++)
				grid[r][c] = Math.min(grid[r][c], Math.abs(r-i) + Math.abs(c-j));
	}
	
	private int findLargestDistance(int[][] grid) {
		int ret = 0;
		for(int i = 0; i < H; i++)
			for(int j = 0; j < W; j++)
				ret = Math.max(ret, grid[i][j]);
		return ret;
		
	}
	

}
