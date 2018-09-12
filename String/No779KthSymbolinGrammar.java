package String;

public class No779KthSymbolinGrammar {

	public static void main(String[] args) {
		No779KthSymbolinGrammar sol = new No779KthSymbolinGrammar();
		String t = "1\n" + 
				"1\n" + 
				"2\n" + 
				"2\n" + 
				"4\n" + 
				"5\n" + 
				"5\n" + 
				"11\n" + 
				"30\n" + 
				"434991989";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int N = Integer.parseInt(s[i]);
			int K = Integer.parseInt(s[i+1]);
			
		}
	}
	
	public int kthGrammar(int N, int K) {
		// --> O(logK+N) 
		
		// The fastest recursive solution from Leetcode.
		
		// Intuition:
		// 0
		// 01
		// 0110
		// 01101001
		// 0110100110010110
		//
		// f(N, K) depends on f(N-1, K/2) for K is odd and f(N-1, (K+1)/2) for K is odd
		// eg. 2 -> 3,4; 3 -> 5,6; 7 -> 13, 14.
		
		// First line only has one number 0
		// If K is even, find the appended number at correct position of previous row.
		// If K is odd, just find the correct position from previous row

        if (N == 1) return 0;
        if (K%2 == 0) return kthGrammar(N - 1, K / 2) == 0 ? 1 : 0;
        return kthGrammar(N-1, (K + 1) / 2);
    }
	

	public int kthGrammar0(int N, int K) {
		// --> O(logK+N)
		
		// Math way, not good enough
		// for each new row, it makes complement digits and appends to the end,
		// so I count how many time it converts and which power of 2  I used with to sbtract to 0.
		
        int d = (1<<N);
        int convert = -1;
        while(K > 0){
            convert++;
            while(d > K){
                d >>= 1;
                N--;
            }
            K -= d;
        }
        return (N+convert)%2 == 0? 0 : 1;
    }
}
