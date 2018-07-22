package BinarySearch;

import Util.Parser;

public class No300LongestIncreasingSubsequence {

	public static void main(String[] args) {
		No300LongestIncreasingSubsequence sol = new No300LongestIncreasingSubsequence();
		Parser parser = new Parser();
		String t = "[10,9,2,5,3,7,101,18]\n" + 
				"[]\n" + 
				"[2,2]\n" + 
				"[0,8,4,12,2]\n" + 
				"[3,5,3,4,6,8,9,12,0,5,9,8,1,7,6,4]\n" + 
				"[2,3,4,6,2,3,4,1,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.lengthOfLIS(A);
			System.out.println(ans);
		}
	}

	public int lengthOfLIS(int[] A) {
        // --> time = O(nlogn), where n = A.length
        
        // Let dp[i] denotes the length of LIS end at A[i].
        // dp[i] = MAX(dp[j]) + 1 for A[j] < A[i] and j < i
        // This is a O(n^2) method
        // Can we improve it to O(nlogn)?
        
        // Yes!
        // Building LIS from an empty array []
        // Insert element with binary search
        
        // eg. A = [0, 8, 4, 12, 2]
        // dp: [0] -> dp: [0, 8] -> dp: [0, 4] ->
        // dp: [0, 4, 12] -> dp: [0 , 2, 12]
        
        // Drawback:
        // Maybe this is not the correct LIS,
        // but it must have the same length.
        
        int[] dp = new int[A.length];
        int len = 0;
        for(int i = 0; i < A.length; i++){
            int idx = binarySearch(dp, 0, len, A[i]);
            dp[idx] = A[i];
            if(idx == len) len++;
        }
        return len;
    }
    public int binarySearch(int[] A, int from, int to, int key){
        // --> time = O(logn)
        
        // Important Case Thinking:
        //
        // Remember this easy test case
        // -> A = [2,4] key = 1,2,3,4,5
        // A[i] = 2, A[j] = 4, A[m] = A[i + (j-i)/2] = 2
        //
        // We want 2 comparison based on i
        // case1: key = 1, key < A[m]
        // we want j = m
        // case2: key = 2, key = A[m]
        // we want j = m
        // case3: key = 3, A[m] < key
        // we want i = m + 1
        // case4: key = 4, A[m] < key
        // we want i = m + 1
        // case5: key = 5, key < A[m]
        // ha ha this is out of boundary
        
        if(to == 0 || to > 0 && A[to-1] < key) return to;
        int i = from, j = to-1;
        while(i < j){
            int m = i + (j-i)/2;
            if(key <= A[m]) j = m;
            else i = m + 1;
        }
        return i;
    }
}
