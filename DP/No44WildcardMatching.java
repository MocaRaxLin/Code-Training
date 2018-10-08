package DP;

import java.util.Arrays;

public class No44WildcardMatching {

	public static void main(String[] args) {
		No44WildcardMatching sol = new No44WildcardMatching();
		String t = "\"aa\"\n" + 
				"\"a\"\n" + 
				"\"aa\"\n" + 
				"\"*\"\n" + 
				"\"cb\"\n" + 
				"\"?a\"\n" + 
				"\"adceb\"\n" + 
				"\"*a*b\"\n" + 
				"\"acdcb\"\n" + 
				"\"a*c?b\"\n" + 
				"\"abdca\"\n" + 
				"\"?*?ca*\"" + 
				"\"\"\n" + 
				"\"\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0 ; i <s.length; i+=2) {
			boolean ans = sol.isMatch(s[i], s[i+1]);
			System.out.println(ans);
		}
	}

	public boolean isMatch(String s, String p) {
        // --> time = O(nm), space O(n), where n = p.length(), m = s.length()
        
        // Intuition:
        // like LCS
        // Make dp[i][j] denotes p[0:i-1] match s[0:j-1] or not
        
        // dp[i][j]
        // if p[i-1] = s[j-1] and dp[i-1][j-1] = True -> dp[i][j] = True
        // if p[i-1] = '?' and dp[i-1][j-1] = True -> dp[i][j] = True
        // if p[i-1] = '*' either dp[i][j-1], dp[i-1][j-1], dp[i-1][j] = True -> dp[i][j] = True
        //                 1. append a char  2. 1st char  3. '*' is redundent
        
        
        int n = p.length();
        int m = s.length();
        boolean[][] dp = new boolean[2][n+1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++) if(p.charAt(i-1) == '*') dp[0][i] = dp[0][i-1];
        
        for(int j = 1; j <= m; j++){
            for(int i = 1; i <= n; i++){
                dp[1][i] = false;
                if(p.charAt(i-1) == '?' || p.charAt(i-1) == s.charAt(j-1)) dp[1][i] = dp[0][i-1];
                else if(p.charAt(i-1) == '*') dp[1][i] = dp[1][i-1] || dp[0][i-1] || dp[0][i];
            }
            /*
            System.out.println("--------------------");
            System.out.println(Arrays.toString(dp[0]));
            System.out.println(Arrays.toString(dp[1]));
            */
            dp[0] = Arrays.copyOfRange(dp[1], 0, n+1);
        }
        return dp[0][n];
    }
	
	public boolean isMatch0(String s, String p) {
        // --> time = O(nm), where n = p.length(), m = s.length()
        
        // Intuition:
        // like LCS
        // Make dp[i][j] denotes p[0:i-1] match s[0:j-1] or not
        
        // dp[i][j]
        // if p[i-1] = s[j-1] and dp[i-1][j-1] = True -> dp[i][j] = True
        // if p[i-1] = '?' and dp[i-1][j-1] = True -> dp[i][j] = True
        // if p[i-1] = '*' either dp[i][j-1], dp[i-1][j-1], dp[i-1][j] = True -> dp[i][j] = True
        //                 1. append a char  2. 1st char  3. '*' is redundent
        
        
        int n = p.length();
        int m = s.length();
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++) if(p.charAt(i-1) == '*') dp[i][0] = dp[i-1][0];
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(p.charAt(i-1) == '?' || p.charAt(i-1) == s.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
                else if(p.charAt(i-1) == '*') dp[i][j] = dp[i-1][j-1] || dp[i-1][j] || dp[i][j-1];
            }
        }
        return dp[n][m];
    }
}
