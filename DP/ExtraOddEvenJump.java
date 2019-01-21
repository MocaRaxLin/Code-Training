package DP;

import java.util.Arrays;

import Util.Parser;

public class ExtraOddEvenJump {

	public static void main(String[] args) {
		ExtraOddEvenJump sol = new ExtraOddEvenJump();
		Parser p = new Parser();
		String t = "[10,13,12,14,15]\n"
				+ "[10,11,14,11,10]\n"
				+ "[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = p.parseArray(s[i]);
			int ans = sol.numOfStartPoints(A);
			System.out.println(ans);
		}

	}

	private int numOfStartPoints(int[] A) {
		System.out.println(Arrays.toString(A));
		if(A.length == 0) return 0;
		boolean[][] dp = new boolean[2][A.length];
		dp[0][A.length-1] = true;
		dp[1][A.length-1] = true;
		int ret = 1;
		for(int i = A.length-2; i >= 0; i--) {
			int t1 = Integer.MAX_VALUE;
			int t2 = Integer.MAX_VALUE;
			for(int j = i + 1; j < A.length; j++) {
				if(A[i] < A[j] && A[j] - A[i] < t1) {
					dp[0][i] = dp[1][j];
					t1 = A[j] - A[i];
				}
				if(A[i] > A[j] && A[i] - A[j] < t2) {
					dp[1][i] = dp[0][j];
					t2 = A[i] - A[j];
				}
			}
			if(dp[0][i]) ret++;
		}
		System.out.println(Arrays.toString(dp[0]));
		System.out.println(Arrays.toString(dp[1]));
		return ret;
	}

}
