package ArrayMatrix;

import java.util.Arrays;
import java.util.Comparator;

import Util.Parser;

public class No354RussianDollEnvelopes {

	public static void main(String[] args) {
		No354RussianDollEnvelopes sol = new No354RussianDollEnvelopes();
		Parser parser = new Parser();
		String t = "[[5,4],[6,4],[6,7],[2,3]]\n" + 
				"[]\n" + 
				"[[2,7],[1,7],[5,1],[5,7],[2,6],[4,1]]\n" +
				"[[4,3],[4,2],[6,4],[2,1],[5,1],[3,4]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] A = parser.parseMatrix(s[i]);
			int ans = sol.maxEnvelopes(A);
			System.out.println(ans);
		}
	}

	public int maxEnvelopes(int[][] A) {
        // --> O(nlogn) = nlogn + nlogn, where n = A.length
        
        // 1.
        // Sort envelopes in increasing order by width,
        // and decreaaing order by height if having the same width
        // 2.
        // Find the longest increasing subsequence by height.
        
        // 85 test cases passed.
        // Runtime: 20 ms
        
        // Slow:
        // Arrays.sort(A, (i, j) -> (i[0] == j[0] ? j[1] - i[1]: i[0] - j[0]));
        
        // Original way to write this should be the following code
        // Fast:
        Arrays.sort(A, new Comparator<int[]>(){
            public int compare(int[] i, int[] j){
                if(i[0] == j[0]) return j[1] - i[1];
                else return i[0] - j[0];
            }
        });
        
        
        int len = 0;
        int[] dp = new int[A.length];
        for(int i = 0; i < A.length; i++){
            int idx = binarySearch(dp, 0, len, A[i][1]);
            dp[idx] = A[i][1];
            if(idx == len) len++;
        }
        return len;
    }
    
    public int binarySearch(int[] A, int from, int to, int key){
        // --> time = O(logn)
        // Key function in Leetcode 300. Longest Increasing Subsequence
        if(to == 0 || to > 0 && A[to-1] < key) return to;
        int i = from, j = to-1;
        while(i < j){
            int m = i + (j-i)/2;
            if(key <= A[m]) j = m;
            else i = m + 1;
        }
        return i;
    }

	public int maxEnvelopes0(int[][] A) {
        // --> O(n^2) = nlogn + n^2
        // First idea using sort + DP
        
        // 85 test cases passed.
        // Runtime: 551 ms
        Arrays.sort(A, (i, j) -> (i[0] - j[0]));
        
        // Let dp[i] denotes the length of longest Russian doll end at index i.
        // dp[i] = MAX(dp[j]) + 1, for j < i and w[j] < w[i] && h[j] < h[i].
        // basic case dp[i] = 1
        
        int ret = 0;
        int[] dp = new int[A.length];
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < i; j++){
                if(A[j][0] < A[i][0] && A[j][1] < A[i][1])
                    dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i]++;
            ret = Math.max(ret, dp[i]);
        }
        return ret;
    }
}
