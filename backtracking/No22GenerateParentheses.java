package backtracking;

import java.util.LinkedList;
import java.util.List;

public class No22GenerateParentheses {

	public static void main(String[] args) {
		No22GenerateParentheses sol = new No22GenerateParentheses();
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			sol.generateParenthesis(n);
			// List<String> list = sol.generateParenthesis(n);
			// System.out.println(Arrays.toString(list.toArray()));
		}
	}

	public List<String> generateParenthesis(int n) {
		// -->O(2^n)
		
		// Intution:
		// if the question only ask how many combination of n-pair parenthesis, 
		// then we can use DP for -------------- of time.
		// eg. n = 3, fill up ______ 6 spaces using '(' and ')' to make up a valid parenthesis
		// n = 1 -> () => 1
		// n = 2 -> ()() + (()) => 2,
		// n = 3 -> ()()() + ()(()) + (())() + ((())) + (()()) => 5
		// n = 4 -> 1+3, 2+2, 3+1, (((())))... => 14
		//
		// This is Catalan Number
		// dp[n] = C[2n, n]/(n+1), where C[a,b] = a!/(b!b!)    // count in O(n)
		//       = SUM(dp[i]*dp[n-1-i]), for i = 0 ~ n-1.      // count in O(n^2)
		// 
		// Thanks to https://leetcode.com/problems/generate-parentheses/discuss/10369/Clean-Python-DP-Solution
		// To make n-pair parentheses, we make a pair first ().
		// Then put 0 pairs inside, n-1 pairs afterwards,
		// or put 1 pairs inside, n-2 pairs afterwards,
		// .
		// .
		// or put n-1 pairs inside, 0 pairs afterwards.
		
		// -> O(n^2)
		int[] dp = new int[n+1];
		dp[0] = 1;
		for(int j = 1; j <= n; j++) {
			for(int i = 0; i < j; i++) {
				dp[j] += dp[i]*dp[j-1-i];
			}
		}
		System.out.println("DP at n = " + dp[n]);
		
		// -> O(n)
		int catalan = 1;
		int a = 2*n;
		int b = n;
		for(int i = a; i > b; i--) {
			// to avoid int overflow
			catalan *= i;
			catalan /= a - i + 1;
		}
		catalan /= (n+1);
		System.out.println("Catalan at n = "+ catalan);
		
        List<String> ret = new LinkedList<String>();
        if(n == 0) return ret;
        char[] ca = new char[2*n];
        backtracking(ret, ca, 0, n, n);
        System.out.println(ret.size());
        return ret;
    }
    
    private void backtracking(List<String> list, char[] ca, int idx, int left, int right){
        if(idx == ca.length && valid(ca)){
            list.add(new String(ca));
            return;
        }
        if(left > 0){
            ca[idx] = '(';
            backtracking(list, ca, idx+1, left-1, right);
        }
        if(right > 0){
            ca[idx] = ')';
            backtracking(list, ca, idx+1, left, right-1);
        }
    }
    
    private boolean valid(char[] ca){
        int size = 0;
        for(int i = 0; i < ca.length; i++){
            if(ca[i] == '(') size++;
            else if(size > 0) size--;
            else return false; // no '(' to pop out
        }
        return size == 0; // if size > 0 we left so '(' in stack
    }
}
