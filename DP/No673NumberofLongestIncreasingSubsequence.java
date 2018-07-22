package DP;

import java.util.Arrays;

import Util.Parser;

public class No673NumberofLongestIncreasingSubsequence {

	public static void main(String[] args) {
		No673NumberofLongestIncreasingSubsequence sol = new No673NumberofLongestIncreasingSubsequence();
		Parser parser = new Parser();
		
		String t = "[1,3,5,4,7]\n" + 
				"[2,2,2,2,2]\n" + 
				"[1,3,7,4,1,5]\n" + 
				"[0]\n" + 
				"[]\n" + 
				"[-3,4,999999,0]\n" + 
				"[1,2,4,3,5,4,7,2]";
		String[] s = t.split("\n");
		for(String i: s) {
			int[] nums = parser.parseArray(i);
			int ans = sol.findNumberOfLIS(nums);
			System.out.println(ans);
		}
 	}

    public int findNumberOfLIS(int[] nums) {
    	// --> time = O(n^2), space = O(n), where n = nums.length
    	
    	// Intuition:
    	// We know how to find out the length of
    	// longest Increasing Subsequence (LIS) by using DP.
    	// Let dp[i] denotes the length of LIS end at A[i] and using A[i]
    	// dp[i] = Max(dp[j]) + 1, for j = [0:i-1] and A[j] < A[i].
    	//
    	// But can we extend the problem to find out how many?
    	// Yes!
    	
    	// We do more checks while we update dp[i].
    	//
    	// When we update dp[i], we take its previous elements in the sequence into consideration.
    	// eg.A = [1,3,5,4,7], its dp[1,2,3,3,?]
    	//    When we update dp[4] in A[4] = 7, we check every dp[j] for j = [0:i-1] and A[j] < A[i].
    	//    Here we got dp[3] = 3, dp[4] = 3, so dp[5] = 3 + 1 = 4.
    	//
    	// In the meanwhile, we have to find out how many subsequence of such length.
    	// In the above example we have 2 (dp[3] = 3, dp[4] = 3) with respect to dp[4].
    	// Therefore, we need to know how many subsequence of length dp[i] end at A[i].
    	// This is another DP!
    	
    	// Let count[i] denotes how many subsequence of length dp[i] end at A[i].
    	// count[i] = Sum(count[j]), for j = [0:i-1], A[j] < A[i] and "dp[j] is maximum".
    	// Combine dp and count.
    	
    	
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];
        Arrays.fill(count, 1);
        for(int i = 0 ; i < n; i++){
            int maxLen = 0;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]);
                    if(dp[j] > maxLen){
                        maxLen = dp[j];
                        count[i] = count[j];
                    }else if(dp[j] == maxLen){
                        count[i]+=count[j];
                    }
                }
            }
            dp[i]++;
        }
        /*
        for(int i = 0; i < n; i++) System.out.print(dp[i] +" ");
        System.out.println();
        for(int i = 0; i < n; i++) System.out.print(count[i] +" ");
        System.out.println();
        System.out.println();
        */
        
        int ret = 0;
        int longest = 0;
        for(int i = 0; i < n; i++) longest = Math.max(longest, dp[i]);
        for(int i = 0; i < n; i++)
            if(dp[i] == longest) ret+=count[i];
        return ret;
    }
}
