package DP;

public class No72EditDistance {

	public static void main(String[] args) {
		No72EditDistance sol = new No72EditDistance();
		String t = "\"horse\"\n" + 
				"\"ros\"\n" + 
				"\"intention\"\n" + 
				"\"execution\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int ans = sol.minDistance(s[i], s[i+1]);
			System.out.println(ans);
		}
	}

	private int minDistance(String A, String B) {
		// time = O(nm), where n = A.length(), m = B.length();
		
		// dp[i][j] denotes the min steps to convert from A[0:i-1] to B[0:j-1]
		// A[i-1] == B[j-1] no action: dp[i][j] = dp[i-1][j-1]
		// else min steps either from insert, remove, replace
		//      dp[i][j] = MIN(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
		
        int n = A.length(); int m = B.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) dp[i][0] = dp[i-1][0] + 1;
        for(int j = 1; j <= m; j++) dp[0][j] = dp[0][j-1] + 1;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(A.charAt(i-1) == B.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1; // insert, remove
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + 1); // replace
                }
            }
        }
        return dp[n][m];
    }
}
