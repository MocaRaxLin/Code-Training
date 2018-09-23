package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No910SmallestRangeII {

	public static void main(String[] args) {
		No910SmallestRangeII sol = new No910SmallestRangeII();
		Parser parser = new Parser();
		String t = "[1]\n" + 
				"0\n" + 
				"[0,10]\n" + 
				"2\n" + 
				"[1,3,6]\n" + 
				"3\n" + 
				"[0,7,4,-1,3]\n" + 
				"4\n" + 
				"[1,2,3,4]\n" + 
				"5\n" + 
				"[7,8,8]\n" + 
				"5";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int K = Integer.parseInt(s[i+1]);
			int ans = sol.smallestRangeII(A, K);
			System.out.println(ans);
		}
	}

	public int smallestRangeII(int[] A, int K) {
        // --> O(n), where n = A.length;
        
        // A[0] + K, A[i] + K, A[i+1] - K, A[A.length - 1] - K
        // are the only relevant values for calculating the answer
        // low: A[0] + K vs A[i+1] - K
        // high: A[i] + K vs A[n-1] - K
		// find out the i makes minimal high - low.
        
        int n = A.length; if(n < 2) return 0;
        Arrays.sort(A);
        
        int ret = A[n-1] - A[0]; // default answer is all elements +- K
        for(int i = 0; i < n-1; i++){
            int low = Math.min(A[0]+K, A[i+1]-K);
            int high = Math.max(A[i]+K, A[n-1]-K);
            ret = Math.min(ret, high-low);
        }
        return ret;
    }
}
