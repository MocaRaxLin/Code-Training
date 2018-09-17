package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No689MaximumSumof3NonOverlappingSubarrays {

	public static void main(String[] args) {
		No689MaximumSumof3NonOverlappingSubarrays sol = new No689MaximumSumof3NonOverlappingSubarrays();
		Parser parser = new Parser();
		String t = "[1,2,1,2,6,7,5,1]\n" + 
				"2";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int[] ans = sol.maxSumOfThreeSubarrays(A, k);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] maxSumOfThreeSubarrays(int[] A, int k) {
        // --> O(n), where n = A.length
        
        // Thanks to:
        // https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/discuss/108231/C++Java-DP-with-explanation-O(n)
		
        // find left positions, right positions.
        // Let posLeft[i] denotes the start index of max sum subarray of length k ending at or before index i.
        //     posRight[i]            start                                       starting at or after index i.
        // Check all possible middle start index
        
        int n = A.length;
        int[] preSum = new int[n+1]; // sum(i,j) = preSum[j+1] - preSum[i]
        for(int i = 1; i < preSum.length; i++) preSum[i] = preSum[i-1] + A[i-1];
        int[] ans = new int[3], posLeft = new int[n], posRight = new int[n];
        
        // find left pos, i is end
        for (int i = k, tot = preSum[k]-preSum[0]; i < n; i++) {
            if (preSum[i+1]-preSum[i-k+1] > tot) {
                posLeft[i] = i-k+1;
                tot = preSum[i+1]-preSum[i-k+1];
            } else posLeft[i] = posLeft[i-1];
        }
        
        // find right pos, i is start
        posRight[n-k] = n-k;
        for (int i = n - k - 1, tot = preSum[n]- preSum[n-k]; i >= 0; i--) {
            if (preSum[i+k]-preSum[i] > tot) {
                posRight[i] = i;
                tot = preSum[i+k]-preSum[i];
            } else posRight[i] = posRight[i+1];
        }
        
        
        // test all possible middle interval
        // [0~k-1], k ... n-2k,[n-2k+1, ... n-k-1], X[n-k, ... n] 
        int maxsum = 0;
        for (int i = k; i <= n-2*k; i++) {
            int l = posLeft[i-1], r = posRight[i+k];
            int tot = (preSum[i+k]-preSum[i]) + (preSum[l+k]-preSum[l]) + (preSum[r+k]-preSum[r]);
            if (tot > maxsum) {
                maxsum = tot;
                ans[0] = l; ans[1] = i; ans[2] = r;
            }
        }
        return ans;
    }
}
