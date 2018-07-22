package BinarySearch;

public class No367ValidPerfectSquare {

	public static void main(String[] args) {
		No367ValidPerfectSquare sol = new No367ValidPerfectSquare();
		String t = "1\n" + 
				"4\n" + 
				"9\n" + 
				"16\n" + 
				"25\n" + 
				"36\n" + 
				"49\n" + 
				"64\n" + 
				"81\n" + 
				"100\n" + 
				"23421\n" + 
				"104976\n" + 
				"2147483647";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int N = Integer.parseInt(s[i]);
			boolean ans = sol.isPerfectSquare(N);
			System.out.println(ans);
		}
	}

	public boolean isPerfectSquare(int N) {
        // --> time = O(logN)
        
        // 67 / 67 test cases passed.
        // Runtime: 0 ms
        
        // Test Methodology:
        // We can only test perfect numbers and
        // filter out the numbers which are not.
        
        // Edge cases checking is needed
        // N = 1, N = 2147483647 = 2^31 - 1
        
        if(N == 1) return true;
        int i = 1, j = N;
        while(i < j){
            int m = i + (j-i)/2;
            if(m < N/m) i = m + 1;
            else if(m > N/m) j = m - 1;
            else if(N % m == 0) return true;
            else return false;
        }
        // To deal with i = j = sqrt(N) we need the last check
        // eg. i = j = 5 = sqrt(25).
        return i == N/i && N % i == 0;
    }
}
