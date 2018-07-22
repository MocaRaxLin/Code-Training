package DP;

public class No583DeleteOperationforTwoStrings {

	public static void main(String[] args) {
		No583DeleteOperationforTwoStrings sol = new No583DeleteOperationforTwoStrings();
		String a = "sea";
		String b = "eat";
//		String a = "ABAZDC";
//		String b = "BACBAD";
//		String a = "";
//		String b = "a";
		int ans = sol.minDistance(a, b);
		System.out.println(ans);

	}
	
	public int minDistance(String word1, String word2) {
        // --> O(nm), where n = word1.length() and m = word2.length()
        // this is N + M - 2 * Length of Longest Common Subsequence (LCS)
        
		// If we use brute force, then solution will be exponential time,
		// so we think about DP.
		//
		// Let dp[i,j] be the length of LCS(A[0, i-1], B[0, j-1])
		// Case 1:
		//     A[i-1] == B[j-1] => dp[i,j] = dp[i-1,j-1] + 1;
		// Case 2:
		//     A[i-1] != B[j-1]
		//     either A[i-1] or B[j-1] is in LCS,
		//     or both of them are NOT in LCS.
		//     Therefore we compare A[0, i-2] = xx...x, A[i-1] = y, and B[0, i-2] = xx...x, B[i-1] = z.
		//     1. xx...xy 2. xx...x  3. xx...x
		//        xx...x     xx...xz    xx...x
		//     those 3 cases are conclude in dp[i,j-1] and dp[i-1,j]
		//     => dp[i,j] = max(dp[i,j-1], dp[i-1,j])
				
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1]; // leave a space for ""
        
        // basic case, be careful enpty string ""
        for(int i = 0; i < m + 1; i++) dp[0][i] = 0;
        for(int i = 0; i < n + 1; i++) dp[i][0] = 0;
        
        // fill up table
        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[i].length; j++){ // careful this is j++ not i++
                if(word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        
        //Extra find the LCS
        char[] LCS = new char[dp[n][m]];
        int i = n, j = m;
        for(int h = LCS.length - 1; h >=0 ;h--) {
        	while(i-1 >= 0 && j-1 >= 0 && dp[i-1][j] != dp[i][j-1]) {
        		if(dp[i-1][j] == dp[i][j]) i--;
        		else j--;
        	}
        	// base on A
        	LCS[h] = word1.charAt(i-1);
        	i--;
        	j--;
        }
        System.out.println(LCS);
        
        return n + m - 2*dp[n][m];
    }
}
