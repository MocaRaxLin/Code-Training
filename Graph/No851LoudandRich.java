package Graph;

import java.util.Arrays;

import Util.Parser;
import Util.Show;

public class No851LoudandRich {

	public static void main(String[] args) {
		No851LoudandRich sol = new No851LoudandRich();
		Parser parser = new Parser();
		Show show = new Show();
		
		String t = "[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]\n" + 
				"[3,2,5,4,6,1,7,0]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] richer = parser.parseMatrix(s[i]);
			int[]quiet = parser.parseArray(s[i+1]);
			int[] ans = sol.loudAndRich(richer, quiet);
			show.showArray(ans);
		}
	}

    public int[] loudAndRich(int[][] richer, int[] quiet) {
    	// --> time = O(N^2)
    	
    	// Intuition:
    	// With the matrix richer, we can build a DAG easily.
    	// X -> Y if X is richer than Y
    	// If w, x, y are richer than z, we know w, x, y, are children of node z.
    	// Other example will be like:
    	// 4,5,6 -> 3 -> 7
    	//           \-> 1 -> 0
    	//         2---->1
    	// Now if we want to know the least quiet person who is richer than person 1,
    	// we got to check all his children subtree root of 2 and 3 to find out.
    	// Moreover if we want to know person 3, then check his children 4, 5, 6 similarly.
    	// 
    	// This is DP in Tree Structure!
    	
        int N = quiet.length;
        int[][] map = new int[N][N];
        for(int i = 0; i < richer.length; i++) map[richer[i][0]][richer[i][1]] = 1;
        
        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        // post order traversal + dp -> O(N^2)
        for(int i = 0; i < N; i++){
            if(dp[i] == -1) dp[i] = pot(map, dp, i, quiet);
        }
        return dp;
    }
    
    public static int pot(int[][] map, int[] dp, int idx, int[] quiet){
        if(dp[idx] != -1) return dp[idx];
        int p = idx;
        for(int i = 0; i < map.length; i++){ // O(N)
            if(map[i][idx] == 1){
                int t = pot(map, dp, i, quiet);
                if(quiet[p] > quiet[t]) p = t;
            }
        }
        dp[idx] = p;
        return dp[idx];
    }
}
