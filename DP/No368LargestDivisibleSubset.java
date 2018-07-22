package DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class No368LargestDivisibleSubset {

	public static void main(String[] args) {
		
		No368LargestDivisibleSubset sol = new No368LargestDivisibleSubset();
		Parser parser = new Parser();
		Show show = new Show();
		
		String t = "[1,2,3]\n" + 
				"[]\n" + 
				"[2,5,6,7,3,64,6,7,7,6,512,256,4,7,7,2,2,8,32,4,4,6,8,8,14,4,169,196,64,24,1024]\n" + 
				"[2,3]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			List<Integer> ans = sol.largestDivisibleSubset(A);
			show.showListInt(ans, true);
		}
	}

    public List<Integer> largestDivisibleSubset(int[] A) {
        // --> O(n^2), where n = A.length
        
        if(A.length == 0) return new LinkedList<Integer>();
        Arrays.sort(A);
        
        // Let dp[i] means the length of LDS ending at A[i];
        //     prev[i] means the index of previous element 
        // dp[i] = MAX(dp[j]) + 1, for j < i and A[i] % A[j] = 0
        // Also update prev[i] when we find out a larger set.
        // dp[j] > dp[i] => dp[i] = dp[j]
        // Then we can trace from last to front.
        
        int[] dp = new int[A.length];
        int[] prev = new int[A.length];
        dp[0] = 1;
        Arrays.fill(prev, -1);
        for(int i = 1; i < A.length; i++){
            for(int j = 0; j < i; j++){
                if(A[i]%A[j] == 0 && dp[j] > dp[i]){
                    dp[i] = dp[j];
                    prev[i] = j;
                }
            }
            dp[i]++;
        }
        
        int len = 0, last = 0;
        for(int i = 0; i < A.length; i++){
            if(dp[i] > len){
                len = dp[i];
                last = i;
            }
        }
        
        List<Integer> ret = new LinkedList<Integer>();
        for(int i = last; i >= 0;){
            ret.add(0, A[i]);
            i = prev[i];
        }
        return ret;
    }
}
