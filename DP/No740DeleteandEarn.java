package DP;

import Util.Parser;

public class No740DeleteandEarn {

	public static void main(String[] args) {
		No740DeleteandEarn sol = new No740DeleteandEarn();
		Parser parser = new Parser();
		
		// Input Constraint
		// -> The length of nums is at most 20000.
		// -> Each element nums[i] is an integer in the range [1, 10000].
		String testcase = "[3, 4, 2]\n" + 
				"[2,2,3,3,3,4]\n" + 
				"[1]\n" + 
				"[10000]\n" + 
				"[4,6,3,5,7,6,5,2,3,45,4,24,6,24,62,46,4,7,7,34,75,257,1,9,90]\n" + 
				"[]";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] nums = parser.parseArray(s[i]);
			int ans = sol.deleteAndEarn(nums);
			System.out.println(ans);
		}
	}
	
	public int deleteAndEarn(int[] nums) {
		// --> time = O(N + W), space = O(W),can be reduce to O(1)
		// because we only use dp[i-1] and dp[i-2]
		// where N = nums.length, W = MAX(nums[i])
		
		// Intuition:
		// Collect all numbers, calculate summation and store into array A
		// eg. [2,2,3,3,3,4] -> A[2] = 4, A[3] = 9, A[4] = 4.
		// DP from 1 to max number
		// Let dp[i] denotes the maximum points we get after we consider taking  number i.
		// Pick: dp[i] = A[i] + dp[i-2]
		// Not pick: dp[i] = dp[i-1]
		
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int max = 0;
        int[] A = new int[10001];
        for(int i = 0; i < nums.length; i++){
            A[nums[i]] += nums[i];
            max = Math.max(max, nums[i]);
        }
        int[] dp = new int[max+1];
        dp[0] = 0;
        dp[1] = A[1];
        for(int i = 2; i < dp.length; i++){
            dp[i] = Math.max(dp[i-2] + A[i], dp[i-1]);
        }
        return dp[max];
    }
}
