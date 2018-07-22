package BinarySearch;

public class No441ArrangingCoins {

	public static void main(String[] args) {
		No441ArrangingCoins sol = new No441ArrangingCoins();
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8\n" + 
				"9\n" + 
				"10\n" + 
				"100\n" + 
				"500\n" + 
				"3245\n" + 
				"5000\n" + 
				"11111\n" + 
				"999999\n" + 
				"2147483647";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			int ans = sol.arrangeCoins(n);
			System.out.println(ans);
		}
		
	}

	public int arrangeCoins(int n) {
		// --> O(logn)
		
		// 1336 / 1336 test cases passed.
		// Runtime: 25 ms
		
		// Find the answer in range [1:n] using binary search
		// if m(m+1)/2 <= n then answer in [m:j]
		// else the answer in [i:m)
		
		// Important! When we compute m, we actually cannot reach j.
		// Thus when update j by m (j = m), it means the answer in [i,m-1]
		
		// Be careful here m(m+1) might > 2^31 - 1, so we use division.
		// 1. m is odd  -> (m+1)/2 <= n/m
		// 2. m is even ->  m/2 <= n/(m+1)
		
        if(n == 0) return 0;
        int i = 1, j = n;
        while(j - i > 1){
            int m = i + (j-i)/2;
            if(m % 2 == 0 && m/2 <= n/(m+1)) i = m;
            else if(m % 2 == 1 && (m+1)/2 <= n/m) i = m;
            else j = m;
        }
        return i;
    }
}
