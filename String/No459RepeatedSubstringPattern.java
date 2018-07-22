package String;

public class No459RepeatedSubstringPattern {

	public static void main(String[] args) {
		No459RepeatedSubstringPattern sol = new No459RepeatedSubstringPattern();
		
		String[] input = new String[] {
				"abab",
				"a",
				"r",
				"abcabd",
				"abcdabbd"
		};
		for(String s: input) {
			boolean ans = sol.repeatedSubstringPattern(s);
			System.out.println(ans);
		}
	}
	
	public boolean repeatedSubstringPattern(String s) {
		// --> O(n^2), where n = s.length();
		// Determine the substring repeating to form the whole string.
		// Let test substrings of lengths from 1 to n-1
		// for each substring repeatedly check the rest of string has the same pattern.
		// we only need to test the length i which has a constraint n % i == 0, 
		// because we've already known n = k * i, where k >= 2.
		
		// NOTE:
		// String concatenation is slow. No matter you use plus '+' or class StringBuilder.
		// String substring() is slow too. It takes linear time.
		
		// solution B --> 4n + n + 3n = 8n --> stable O(n):
		// String T = s + s;
        // T = T.substring(1, T.length() - 1);
        // return KMP(T, s) != -1;
		//
		// Source:
		// https://leetcode.com/problems/repeated-substring-pattern/discuss/94334/Easy-python-solution-with-explaination?page=3
		// Let's say T = S + S. "S is Repeated => From T[1:-1] we can find S" is obvious.
		// If from T[1:-1] we found S at index p-1, which is index p in T and S.
		// let s1 = S[:p], S can be represented as s1s2...sn, where si stands for substring rather than character.
		// then we know T[p:len(S) + p] = s2s3...sn-1sns1 = S = s1s2...sn-2sn-1sn.
		// So s1 = s2, s2 = s3, ..., sn-1 = sn, sn = s1,Which means S is Repeated.
		//
		// So why our O(n^2) solution is faster than solution B?
		// The answer is not all test cases are the worst case.
		//
		// In our O(n^2) solution, the lower bound is n
		// if the given string is not repeated string.
		// This explain the solution will be faster in some good cases.
		
        if(s.length() < 2) return false; // single and empty strings are invalid
        int N = s.length();
        for(int i = 1; i < N; i++){ //I cannot use whole string
            if(N%i == 0){
                // check s[i:N-1] with s[0:i-1]
                int idx = 0;
                int j = i;
                for(;j < N; j++){
                    if(s.charAt(idx) == s.charAt(j)) idx++;
                    else break;
                    if(idx == i) idx = 0;
                }
                if(j == N) return true;
            }
        }
        return false;
    }

}
