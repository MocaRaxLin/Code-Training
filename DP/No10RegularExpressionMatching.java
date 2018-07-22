package DP;

public class No10RegularExpressionMatching {

	public static void main(String[] args) {
		No10RegularExpressionMatching sol = new No10RegularExpressionMatching();
		String[] inputs = new String[] {
				"aa", "a",
				"aa", "a*",
				"ab", ".*",
				"aab", "c*a*b",
				"mississippi", "mis*is*p*.",
				"a", ".*..a*"
		};
		for(int i = 0; i < inputs.length; i+=2) {
			boolean ans = sol.isMatch(inputs[i], inputs[i+1]);
			System.out.println(ans);
		}
	}
	
	
	public boolean isMatch(String s, String p) {
        // --> time O(nm), where n = s.length(), m = p.length().
		// --> space O(nm), dynamic programming table
		
        // Intuition:
        // let dp[i][j] denotes if s[0:i-1] matches p[0:j-1].
		//
		// Basic cases
		// 1. s = "" always matches p = "", so dp[0][0] = 0
		// 2. A not empty string s will never matches p = "", so dp[k][0] = false, where 1 <= k <= n.
		// 3. s = "" matches p when satisfy all constraint below
		//     1) p is even length
		//     2) p[j-1] is '*'
        //     3) "" matches p[0:j-3]
		//
		// Three cases from p's perspective:
		// 1. p[j-1] == '.':
		//    If s[0:i-2] matches p[0:j-s] then dp[i][j] is true.
		//
		// 2. p[j-1] == '*':
		//    dp[i][j] is true when
		//    1) do not use _*: s[0:i-1] matches p[0:j-3]
		//    2) use it and test the last char: s[0:i-2] matches p[0:j-1] and s[i-1] matches p[j-2]
		//                                                     ==> p[j-2] == '.' or p[j-2] = s[i-1]
		// 3. p[j-1] is a usual alphabet:
		//    If s[0:i-2] matches p[0:j-s] and p[j-1] == s[i-1] then dp[i][j] is true.
        
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        
        //basic cases
        dp[0][0] = true;
        //for(int i = 1; i <= n; i++) dp[i][0] = false;
        for(int j = 2; j <= m; j+=2) dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-2];
        
        //fill up table
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(p.charAt(j-1) == '.')
                    dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(j-1) == '*')
                    dp[i][j] = dp[i][j-2] || dp[i-1][j] && (p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1) );
                else
                    dp[i][j] = dp[i-1][j-1] && p.charAt(j-1) == s.charAt(i-1);
            }
        }
        
        return dp[n][m];
    }
}
