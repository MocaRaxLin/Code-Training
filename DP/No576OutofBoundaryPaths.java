package DP;

public class No576OutofBoundaryPaths {

	public static void main(String[] args) {
		No576OutofBoundaryPaths sol = new No576OutofBoundaryPaths();
		// Input constraints:
		// - Once you move the ball out of boundary, you cannot move it back.
		// - The length and height of the grid is in range [1,50].
		// - N is in range [0,50].
		String t = "2\n" + 
				"2\n" + 
				"2\n" + 
				"0\n" + 
				"0\n" + 
				"1\n" + 
				"3\n" + 
				"3\n" + 
				"0\n" + 
				"1\n" + 
				"3\n" + 
				"3\n" + 
				"3\n" + 
				"1\n" + 
				"0\n" + 
				"3\n" + 
				"8\n" + 
				"0\n" + 
				"2\n" + 
				"0\n" + 
				"8\n" + 
				"7\n" + 
				"16\n" + 
				"1\n" + 
				"5";
		String[] s = t.split("\n");
		for(int k = 0; k < s.length; k+=5) {
			int m = Integer.parseInt(s[k]);
			int n = Integer.parseInt(s[k+1]);
			int N = Integer.parseInt(s[k+2]);
			int i = Integer.parseInt(s[k+3]);
			int j = Integer.parseInt(s[k+4]);
			int ans = sol.findPaths(m, n, N, i, j);
			System.out.println(ans);
		}
		
	}
	
	public int findPaths(int m, int n, int N, int i, int j) {
		// --> time = O(Nmn)
		
		// Let dp[k][i][j] denotes the number of ways to reach position (i, j)
		// using exactly k steps.
		// dp[k][i][j] = dp[k-1][i−1][j] + dp[k-1][i+1][j]
		//             + dp[k-1][i][j−1] + dp[k-1][i][j+1]
		
		// Collect each dp[k][i][j] around border to count the number out of boundary.
		// for k + 1 step.
		// Thus, we only need to count till the N-1 step.
		
        if(N == 0) return 0;
        
        int[][] dp = new int[m][n];
        
        // delta row/column goes counter-clockwise
        int[] dr = new int[]{-1, 0, 1, 0};
        int[] dc = new int[]{0, -1, 0, 1};
        
        // basic case
        dp[i][j] = 1; // dp[0][i][j] = 1
        
        // boundary counter
        int ret = boundaryCount(0, 1, i, j, m, n);
        
        // fill up table from 1 to k-1
        for(int k = 1; k < N; k++){
            int[][] newDp = new int[m][n];
            for(int r = 0; r < m; r++){
                for(int c = 0; c < n; c++){

                    for(int d = 0; d < 4; d++){
                        if( r + dr[d] < 0 || m <= r + dr[d] ||  c + dc[d] < 0 || n <= c + dc[d]) continue;
                        newDp[r][c] += dp[r+dr[d]][c+dc[d]];
                        newDp[r][c] %= largePrime;
                    }
                    
                    ret = boundaryCount(ret, newDp[r][c], r, c, m, n);
                    //System.out.println(ret);
                }
                //System.out.println(Arrays.toString(newDp[r]));
            }
            dp = newDp;
        }
        
        return ret;
        
    }
    
    int largePrime = 1000000000 + 7;
    public int boundaryCount(int counter, int ac, int r, int c, int m, int n){
        int ret = counter;
        if(r == 0) ret = (ret + ac) % largePrime;
        if(r == m-1) ret = (ret + ac) % largePrime;
        if(c == 0) ret = (ret + ac) % largePrime;
        if(c == n-1) ret = (ret + ac) % largePrime;
        return ret;
    }
}
