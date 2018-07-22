package DP;

public class No813LargestSumofAverages {

	public static void main(String[] args) {
		No813LargestSumofAverages sol = new No813LargestSumofAverages();
		
		// Input constraints
		// -> 1 <= A.length <= 100.
		// -> 1 <= A[i] <= 10000.
		// -> 1 <= K <= A.length.
		int[][] As = new int[][] {
			{9,1,2,3,9},
			{1,2,3,4},
			{1},
			{3,-2,5,20},
			{1,-1},
			{10000, 10000},
			{9,1,2,3,9},
			{4,3,2,1},
		};
		int[] Ks = new  int[] {3, 4, 1, 3, 2, 3, 4, 5};
		for(int i = 0; i < As.length; i++) {
			double ans = sol.largestSumOfAverages(As[i], Ks[i]);
			System.out.println(ans);
		}
	}
	
	public double largestSumOfAverages(int[] A, int K) {
		// time --> O(Kn^2), where n = A.length
		// space --> O(Kn), we can reduce it to O(n) if we reuse the array from k-1
		
		// Intuition:
		// largest -> Optimization -> DP
		// DP -> Find the subproblem!
		
		// Important!
		// Let dp[i,k] denotes the largest sum of averages in A[0:i] with k groups.
		// Why?
		// The reason is we can form k groups faster if we know answers of smaller groups set.
		// Also speak to average, it is common to use "preffixSum Array" to speed up calculation.
		// Therefore we define the subproblem like this. (Give it a shot!)
		
		// Think about how to form the last group?
		// dp[i,k] = MAX(dp[j][k-1] + average(A[j+1:i]), for j = X to i-1
		// Here is the point. X is not always 0, because sometimes it makes no sense.
		// Like dp[0,3]. What do you mean by cutting A[0] into 3 pieces and ask the average?
		// NO! So we must carefully examine X!
		
		// Given an example A = [9,1,2,3,9], K = 3
		// Basic case: dp[i][1] = preSum[i]/(i+1)
		// Let k start from 2 to either K or A.length the small one.
		// (A can only be separated A.length pieces)
		// k = 2
		// i = k-1 = 1, dp[1][2] = preSum[1],
		// because the best way to cut A[0:i] = A[0:1] into 2 is A[0]/1 + A[1]/1.
		//
		// i = k to a.length-1 = 2 to 4.
		//    idx=  0    1    2    3    4
		//   K  A=  9    1    2    3    9
		//   1      preSum[idx]/(idx+1)
		//   2      x    |    
		//
		// i = 2: dp[2][2] = Max(dp[j][1] + average(A[j+1:2]) ), for j = 0 to 1
		// i = 3: dp[3][2] = Max(dp[j][1] + average(A[j+1:3]) ), for j = 0 to 2
		// i = 4: dp[4][2] = Max(dp[j][1] + average(A[j+1:4]) ), for j = 0 to 3
		//
		// k = 3
		// i = k-1 = 2, dp[2][3] = preSum[2]
		// because the best way to cut A[0:i] = A[0:2] into 3 is A[0]/1 + A[1]/1 + A[2]/1.
		//
		// i = k to a.length-1 = 3 to 4.
		//    idx=  0    1    2    3    4
		//   K  A=  9    1    2    3    9
		//   1      preSum[idx]/(idx+1)
		//   2      x    |    -    -    -
		//   3      x    x    |
		//
		// i = 3: dp[3][2] = Max(dp[j][2] + average(A[j+1:3]) ), for j = 1 to 2
		// i = 4: dp[4][2] = Max(dp[j][2] + average(A[j+1:4]) ), for j = 1 to 3
		// Thus minimum of j which is X should be k - 2.
		
		
        // make the preffixSum array
        // eg. [9,1,2,3,9] -> [9,10,12,15,24]
        int[] preSum = new int[A.length];
        preSum[0] = A[0];
        for(int i = 1; i < preSum.length; i++) preSum[i] = preSum[i-1] + A[i];
        
        double[][] dp = new double[A.length][K+1];
        //basic cases for K = 1. ps. we ignore K = 0
        for(int i = 0; i < dp.length; i++) dp[i][1] = (double) preSum[i]/(i+1);
        
        int k;
        for(k = 2; k <= K && k <= A.length; k++){ // let k be up to either K or n
            dp[k-1][k] = preSum[k-1];
            for(int i = k; i < A.length; i++){
                dp[i][k] = Integer.MIN_VALUE;
                for(int j = k - 2; j < i; j++)
                    dp[i][k] = Math.max(dp[i][k], dp[j][k-1] + (double) (preSum[i] - preSum[j])/(i-j) );
            }
        }
        return dp[A.length - 1][k - 1];
    }
}
