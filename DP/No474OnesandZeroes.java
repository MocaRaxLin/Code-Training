package DP;

import Util.Parser;

public class No474OnesandZeroes {

	public static void main(String[] args) {
		No474OnesandZeroes sol = new No474OnesandZeroes();
		Parser parser = new Parser();
		String t = "[\"10\",\"0001\",\"111001\",\"1\",\"0\"]\n" + 
				"5\n" + 
				"3\n" + 
				"[\"01\"]\n" + 
				"1\n" + 
				"2\n" + 
				"[]\n" + 
				"0\n" + 
				"0\n" + 
				"[\"10\",\"0\",\"1\"] \n" + 
				"1\n" + 
				"1\n" + 
				"[\"10\",\"0001\",\"111001\",\"1\",\"0\"]\n" + 
				"4\n" + 
				"3\n" + 
				"[\"10\",\"0001\",\"111001\"]\n" + 
				"4\n" + 
				"3\n" + 
				"[\"10\",\"0001\"]\n" + 
				"4\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i+=3) {
			String[] strs = parser.parseArrayStr(s[i]);
			int m = Integer.parseInt(s[i+1]);
			int n = Integer.parseInt(s[i+2]);
			int ans = sol.findMaxForm(strs, m, n);
			System.out.println(ans);
		}
	}

	public int findMaxForm(String[] strs, int m, int n) {
		// --> time = O(s*m*n), where s = strs.length
		// --> space = O(nm)
		
		// Inutition:
		// convert string into a 2d array representing number of 0s and 1s.
		// strs =      ["10", "0001","111001",  "1",   "0"]
		// A = [[0,0], [1,1],  [3,1],  [2,4], [0,1], [1,0]]
		// idx =  0     1       2       3      4      5
		
		// Let dp[z][o][i] denotes the maximum number of elements we can pick
		// given z 0s, o 1s, and considering A[0:i].
		//
		// dp[z][o][i] = Math.max(dp[z][o][i-1], dp[z-h][o-k][i-1] + 1)
		// where h = A[i][0], k = A[i][1]
		
		// Please reduce space complexity.
		// It also improve time complexity!!
		
		
        int[][] A = new int[strs.length + 1][2];
        convertTo(strs, A);
        int[][] dp = new int[m+1][n+1];
        
        //dp[z][o][0] = 0; for z = [0:m], o = [0:n].
        for(int i = 1; i <= strs.length; i++){
            //System.out.println(Arrays.toString(A[i]));
            for(int z = m; z >= 0; z--){
                for(int o = n; o >= 0; o--){
                    // not take A[i]
                    //dp[z][o][i] = dp[z][o][i-1];
                    int h = A[i][0];
                    int k = A[i][1];
                    // take A[i]
                    if(z - h >= 0 && o - k >= 0)
                        dp[z][o] = Math.max(dp[z][o], dp[z-h][o-k] + 1);
                }
            }
        }
        return  dp[m][n];
    }
    
    public void convertTo(String[] strs, int[][] A){
        for(int i = 0; i < strs.length; i++){
            for(int j = 0; j < strs[i].length(); j++){
                if(strs[i].charAt(j) == '0') A[i+1][0]++;
                else A[i+1][1]++;
            }
        }
    }
}
