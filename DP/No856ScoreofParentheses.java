package DP;

public class No856ScoreofParentheses {

	public static void main(String[] args) {
		No856ScoreofParentheses sol = new No856ScoreofParentheses();
		String t = "()\n"
				+ "(()(()))\n"
				+ "()()";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.scoreOfParentheses(s[i]);
			System.out.println(ans);
		}
	}

    public int scoreOfParentheses(String S) {
    	// --> O(n^3), where n = S.length()
    	
    	// Intuition:
    	// Let dp[i][j] denotes the score of parentheses S[i:j]
    	// case 1: ( + A + ) :
    	//     dp[i+1][j-1] has score, S[i] = '(' and S[j] = ')'
    	//     dp[i][j] = 2 * dp[i+1][j-1]
    	// case 2: A + B:
    	//     There is a break point in S[i:j] such that
    	//     both dp[i][k-1] and dp[k][j] score
    	//     dp[i][j] = dp[i][k-1] + dp[k][j]
    	
        int n = S.length();
        char[] ca = S.toCharArray();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n-1; i++)
            dp[i][i+1] = ca[i] == '(' && ca[i+1] == ')' ? 1 : 0;
        
        for (int l = 2; l < n; l++){
            for (int i = 0; i + l < n; i++){
                int j = i + l;
                if (dp[i+1][j-1] != 0 && ca[i] == '(' && ca[j] == ')'){
                    dp[i][j] = 2 * dp[i+1][j-1];
                } else {
                    for(int k = i+2; k < j; k++){
                        if(dp[i][k-1] != 0 && dp[k][j] != 0){
                            dp[i][j] = dp[i][k-1] + dp[k][j];
                            break;
                        }
                    }
                }
            }
        }
        
        return dp[0][n-1];
    }
    
    // Ps. We can improve it to O(n) try it!
}
