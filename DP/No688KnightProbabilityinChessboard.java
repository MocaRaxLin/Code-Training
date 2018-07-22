package DP;

public class No688KnightProbabilityinChessboard {

	public static void main(String[] args) {
		No688KnightProbabilityinChessboard sol = new No688KnightProbabilityinChessboard();
		
		String testcase = "3\n" + 
				"2\n" + 
				"0\n" + 
				"0\n" + 
				"25\n" + 
				"100\n" + 
				"24\n" + 
				"24";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=4) {
			int N = Integer.parseInt(s[i]);
			int K = Integer.parseInt(s[i+1]);
			int r = Integer.parseInt(s[i+2]);
			int c = Integer.parseInt(s[i+3]);
			double ans = sol.knightProbability(N, K, r, c);
			System.out.println(ans);
		}
	}
	
	public double knightProbability(int N, int K, int r, int c) {
		// --> time = O(kN^2), space = O(N^2)
		
		// Intuition:
		// Sum up all possibilities of the knight steps in each position at the k-th step.
		//
		// Let dp[k][i][j] denotes the probability of the knight steps on the board
		// of position (i, j) at the k-th step.
		// Therefore, dp[k][i][j] = sumOf(dp[k-1][i+di][j+dj])/8 ,
		// where di and dj mean the 8 positions coming to (i, j)
		// 
		// basic case is when k = 0; that is the starting point.
		// Thus, dp[0][r][c] = 1, otherwise dp[0][u][v] = 0, where u != r or v != c
		
		// ps. We reduce space to O(N^2) here, because dp[k][i][j] only depends on dp[k-1][i][j].
		
        double[][] dp = new double[N][N];
        int[] di = new int[]{1,2,2,1,-1,-2,-2,-1};
        int[] dj = new int[]{2,1,-1,-2,-2,-1,1,2};
        
        //basic case
        dp[r][c] = 1.0;
        
        for(int k = 1; k <= K; k++){
            double[][] newDp = new double[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    for(int d = 0; d < 8; d++){
                        int ik = i + di[d];
                        int jk = j + dj[d];
                        if(ik < 0 || N <= ik || jk < 0 || N <= jk) continue; // plus 0
                        newDp[i][j] += dp[ik][jk];
                    }
                    newDp[i][j] /= 8;
                }
            }
            dp = newDp;
        }
        
        double ret = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                ret += dp[i][j];
            }
        }
        return ret;
    }

}
