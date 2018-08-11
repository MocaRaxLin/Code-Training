package Math;

public class No884DecodedStringatIndex {

	public static void main(String[] args) {
		No884DecodedStringatIndex sol = new No884DecodedStringatIndex();
		
		// Input Constraint:
		// - 2 <= S.length <= 100
		// - S will only contain lowercase letters and digits 2 through 9.
		// - S starts with a letter.
		// - 1 <= K <= 10^9
		// - The decoded string is guaranteed to have less than 2^63 letters.
		
		String t = "\"leet2code3\"\n" + 
				"10\n" + 
				"\"ha22\"\n" + 
				"5\n" + 
				"\"a2345678999999999999999\"\n" + 
				"23\n" + 
				"\"a2b3c4d5e6f7g8h9\"\n" + 
				"9\n" + 
				"\"y959q969u3hb22odq595\"\n" + 
				"222280369\n" + 
				"\"vzpp636m8y\"\n" + 
				"2920";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String S = s[i];
			int K = Integer.parseInt(s[i+1]);
			char ans = sol.decodeAtIndex(S, K);
			System.out.println(ans);
		}
	}
    public char decodeAtIndex(String S, int K) {
        // --> O(K)
        
        // Thanks to https://leetcode.com/problems/decoded-string-at-index/discuss/156747/C++Python-O(N)-Time-O(1)-Space
        
        // Don't try to use string builder to decode the string, 
    	// because it leads to Memory Limit Exceeded!
    	// (S is long, so decoded S is much longer!)
    	// Use your math instead.
    	
    	// Intuition:
    	// - Set up N and then move close to K
    	// - We only need to decode until string length >= K, we don't care the rest of string
    	//   so let's accumulate length N from 0 according to decoding rule
    	// - scan string S backwards from the previous stop point:
    	//   - If S[i] is a digit d, then divide N into d sessions and take new length as N' (N /= d),
    	//     also find position K' in the new session by K %= N'.
    	//   - Otherwise we meet a char, if N == K, then the char is found,
    	//     or K == 0, the wanted char is at the end of session, so this char we meet is what we want :)
        
        long N = 0;
        int i;
        for(i = 0; N < K; i++){
            char c = S.charAt(i);
            N = '0' <= c && c <= '9' ? N * (c - '0'): N + 1;
        }
        while(--i >= 0){ //iterate back to first char i.e. run until i = -1
            if(Character.isDigit(S.charAt(i))){
                int d = S.charAt(i) - '0';
                N /= d;
                K %= N;
            }else{
                // K == N: think about "leet2co"
                if(K == 0 || K == N) return S.charAt(i);
                else N--;
            }
        }
        
        return '-';
    }

}
