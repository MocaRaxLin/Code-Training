package DP;

public class No678ValidParenthesisString {

	public static void main(String[] args) {
		No678ValidParenthesisString sol = new No678ValidParenthesisString();
		
		String[] S = new String[] {"", "()", "(*)", "(*))", "(*()"};
		for(String s: S) {
			boolean ans = sol.checkValidString(s);
			System.out.println(ans);
		}
	}
	
	public boolean checkValidString(String s) {
		// --> time = O(N), space = O(1), where N = s.length()
		// Greedy
		
		// Balance Concept:
		// When we scan the whole string, if c[i] is '(' balance++, c[i] = ')' balance--.
		// eg. testS = "(()(()))" -> balance = 1, 2, 1, 2, 3, 2, 1, 0.
		// In the scan process, if balance < 0 then testS is invalid.
		// Finally balance is 0, so testS is valid.
		
		// Now we add asterisk '*'.
		// whenever we meet '*', balance split into 3 possible value.
		// balance += 1, -=1, or still the same.
		// eg. testS = "(*))" -> balance = [1], [0, 1, 2], [0, 1], [0] -> true
		// eg. testS = "*()*" -> balance = [0, 1], [1, 2], [0, 1], [0, 1, 2] -> true
		// eg. testS = "*)(" -> balance = [0, 1], [0], [1] -> false
		//
		// Balance array is always consecutive we can prove it,
		// the only 2 values we need to keep are lower bound L and higher bound H.
		// eg. testS = "(*))" -> [L,H] = [1,1], [0,2], [0,1], [0,0]
		// eg. testS = "*()*" -> [L,H] = [0,1], [1,2], [0,1], [0,2]
		// eg. testS = "*)(" -> [L,H] = [0,1], [0,0], [1,1]
		//
		// So in the scan process:
		//    '('  '*'  ')'
		// L   +1   -1   -1
		// H   +1   +1   -1
		//
		// Also, if H < 0 which means there is no change balance >= 0, the string s must be invalid.
		// In the end, we have to check if L == 0,
		// because that statement guarantees balance array cover value 0.
		// In other words, string s is probably valid.
		
        if(s.length() == 0) return true;
        char[] cs = s.toCharArray();
        int low = 0, high = 0;
        for(char c: cs){
            low += c == '(' ? 1 : -1;
            high += c ==')' ? -1 : 1;
            if(high < 0) return false;
            if(low == -1) low = 0;
        }
        return low == 0;
    }
	
	static public boolean checkValidString0(String s) {
		// --> time = O(N^3), space = O(N^2), where N = s.length()
		
		// To determine a string s is valid, there are two cases we have to check
		// 1. onion case:
		//    -> '(' + validString +')', '(' + validString +'*', '*' + validString +')'
		//    -> if you want to check '*' + validString +'*' is fine, but not needed.
		// 2. section case: 
		//    -> validString1 + validString2
		// let dp[i, j] means whether s[i, j] is a valid string
		// dp[i, j] = dp[i+1, j-1] && 
		// ( (dp[i] = '(' && dp[j] = ')') || (dp[i] = '*' && dp[j] = ')') || (dp[i] = '(' && dp[j] = '*') )
		// or
		// dp[i, j] = dp[i, k] && dp[k+1][j], where k = i ~ j-1
		// Find a True in this range, otherwise dp[i][j] is False;
		
		// Note:
		//
		// DON'T try to use solution of exponential time complexity
		//
		// How do we know this is a good time to use DP?
		// Ans: Our answer of the problem depends on
		//      the answer of the same problem with smaller size of input.
		
        if(s.length() == 0) return true;
        char[] c = s.toCharArray();
        boolean[][] dp = new boolean[c.length][c.length];
        
        //basic case
        for(int b = 0; b < c.length; b++)
            if(c[b] == '*') dp[b][b] = true;
        
        //fill up table diagonally
        for(int l = 1; l < c.length; l++){
            for(int i = 0; i + l < c.length; i++){
                int j = i + l;
                
                boolean inner = i + 1 <= j - 1 ? dp[i + 1][j - 1]: true;
                boolean onion = (c[i] == '(' && c[j] == ')') ||
                		(c[i] == '*' && c[j] == ')')  ||
                		(c[i] == '(' && c[j] == '*'); 
                dp[i][j] = inner && onion;
                if(dp[i][j]) continue; //once we get true, we go to the next section
                for(int k = i; k < j; k++){
                    dp[i][j] = dp[i][k] && dp[k+1][j];
                    if(dp[i][j]) break; //same as above, but careful we should BREAK this time
                }
                
            }
        }
        return dp[0][c.length - 1];
	}
}
