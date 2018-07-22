package ArrayMatrix;

public class No840MagicSquaresInGrid {

	public static void main(String[] args) {
		int[][][] input = new int[][][] {
			{{4,3,8,4}, {9,5,1,9}, {2,7,6,2}},
			{{1,8,6}, {10,5,0}, {4,2,9}}
		};
		for(int i = 0; i < input.length; i++) {
			int ans = numMagicSquaresInside(input[i]);
			System.out.println(ans);
		}
	}
	
	public static int numMagicSquaresInside(int[][] grid) {
		// --> O(nm), where n = grid.length, m = grid[0].length
		
		// brute force
		// boolean checks
		// 1. center = 5
		// 2. 1 to 9 no repeated digit around 5
		// 3. sum of opposite position = 10
		// 4. sum of lines = 15
		// if we pass boolean checks above, counter++
		
		// A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9
		// such that each row, column, and both diagonals all have the same sum.
		// Given an grid of integers, how many 3 x 3 "magic square" are there?
		// Input constraint: 
		// 1 <= grid.length <= 10
		// 1 <= grid[0].length <= 10
		// 0 <= grid[i][j] <= 15

        if(grid.length < 3 || grid[0].length < 3) return 0;
        int ret = 0;
        int[] di = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
        int[] dj = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
        for(int i = 0; i + 2 < grid.length; i++){
            for(int j = 0; j +2 < grid.length; j++){
                int ci = i + 1, cj = j + 1;
                boolean center5 = grid[ci][cj] == 5;
                if(!center5) continue;
                boolean around = hasGoodCircleAround(grid, ci, di, cj, dj);
                if(!around) continue;
                boolean oppos = sum10At(grid, ci, di, cj, dj, 0) && sum10At(grid, ci, di, cj, dj, 1) &&
                       sum10At(grid, ci, di, cj, dj, 2) && sum10At(grid, ci, di, cj, dj, 3);
                if(!oppos) continue;
                boolean lines = sum15At(grid, ci, di, cj, dj, 0) && sum15At(grid, ci, di, cj, dj, 2) &&
                        sum15At(grid, ci, di, cj, dj, 4) && sum15At(grid, ci, di, cj, dj, 6);
                if(!lines) continue;
                ret++;
            }
            
        }
        return ret;
    }
    
    public static boolean sum15At(int[][] grid, int i, int[] di, int j, int[] dj, int k){
        int end = k + 2 == 8 ? 0: k + 2;
        return grid[i + di[k]][j + dj[k]] + grid[i + di[k+1]][j + dj[k+1]] +grid[i + di[end]][j + dj[end]] == 15;
    }
    public static boolean sum10At(int[][] grid, int i, int[] di, int j, int[] dj, int k){
        return grid[i + di[k]][j + dj[k]] + grid[i + di[k+4]][j + dj[k+4]] == 10;
    }
    public static boolean hasGoodCircleAround(int[][] grid, int i, int[] di, int j, int[] dj){
        boolean[] nums = new boolean[10];
        for(int k = 0; k < di.length; k++){
            int cur = grid[i + di[k]][j + dj[k]];
            if(cur < 1 || 9 < cur || cur == 5) return false;
            if(nums[cur]) return false;
            else nums[cur] = true;
        }
        return true;
    }
}
