package DP;

import Util.Parser;

public class ExtraMinParitionDiff {

	public static void main(String[] args) {
		ExtraMinParitionDiff sol = new ExtraMinParitionDiff();
		Parser parser = new Parser();
		String t = "[1,2,3,4,5]\n"
				+ "[4,7,8,9,10]\n"
				+ "[1,3,4,5,5,6]\n"
				+ "[5,33,8,2,43,73,78,11,34,46,19,93]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.minPartitionDiff(A);
			System.out.println(ans);
		}
	}

	private int minPartitionDiff(int[] A) {
		// --> O(nk), where n = A.length, k = sum(A)/2
		
		// Please remember this classic partition DP problem!
		// 背
		
		// Let dp[i][j] denotes the max sum <= j composed by A[0] to A[i-1]
		// dp[i][j] = max(dp[i-1][j], dp[i-1][j-A[i-1]] + A[i-1])
		//            not include A[i-1] vs. include A[i-1]
		int sum = 0;
		for(int e: A) sum += e;
		int K = sum/2;
		int[] dp = new int[K+1]; // 0 to K
		for(int i = 1; i <= A.length; i++) {
			for(int j = K; j >= A[i-1]; j--) {
				dp[j] = Math.max(dp[j], dp[j-A[i-1]]+A[i-1]);
			}
		}
		// System.out.println(dp[K]);
		return Math.abs(sum - 2*dp[K]);
	}

}
