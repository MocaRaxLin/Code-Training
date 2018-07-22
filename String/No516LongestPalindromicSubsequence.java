package String;

public class No516LongestPalindromicSubsequence {

	public static void main(String[] args) {
		No516LongestPalindromicSubsequence sol = new No516LongestPalindromicSubsequence();
		String t = "bbbab\n" + 
				"\n" + 
				"ababaabab";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.longestPalindromeSubseq(s[i]);
			System.out.println(ans);
		}
	}

    public int longestPalindromeSubseq(String s) {
    	// --> time = O(n^2)
    	
    	// dp [i,j] denotes the longest palindromic subsequence of A[i:j]
    	// if A[i] == A[j], then dp[i,j] = dp[i+1,j-1] + 2.
    	// Otherwise, dp[i,j] = max(dp[i,j-1], dp[i+1,j])
    	
        if(s.length() == 0) return 0;
        char[] ac = s.toCharArray();
        
        int[][] dp = new int[ac.length][ac.length];
        for(int i = 0; i < ac.length; i++) dp[i][i] = 1;
        
        for(int k = 1; k < ac.length; k++){
            for(int i = 0; i + k < ac.length; i++){
                int j = i + k;
                if(ac[i] == ac[j]) dp[i][j] = dp[i+1][j-1] + 2; //dp[1][0] = 0
                else dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
            }
        }
        return dp[0][s.length()-1];
    }
}
