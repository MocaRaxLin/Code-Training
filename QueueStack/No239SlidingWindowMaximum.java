package QueueStack;

import java.util.Arrays;

import Util.Parser;

public class No239SlidingWindowMaximum {

	public static void main(String[] args) {
		No239SlidingWindowMaximum sol = new No239SlidingWindowMaximum();
		Parser parser = new Parser();
		String t = "[1,3,-1,-3,5,3,6,7]\n" + 
				"3\n" + 
				"[2,1,3,4,6,3,8,9,10,12,56]\n" + 
				"4";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int[] ans = sol.maxSlidingWindow(A, k);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] maxSlidingWindow(int[] A, int k) {
        // --> O(n), where n = A.length
        
        // Intuition:
        // 1st thought will be using a max heap size of k
        // peek max for each index i and remove A[i-k+1], add A[i+1] for index i+1
        // This leads to nlogk time.
        
        // Thanks to:
        // https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
        // It provide O(n) solution.
        // In queue, we store indices. Keep indices in range k, pop useless indices then push new index to the end.
        
        // Think deeper :)
        
        int n = A.length; if(n == 0) return new int[0];
        
        int[] Q = new int[n];
        int h = 0, r = 0;
        
        int[] ret = new int[n-k+1];
        int idx = 0;
        
        int i = 0;
        while(i < n){
            // remove numbers out of range k
            while(r-h>0 && Q[h] < i - k + 1) h++;
            // remove smaller numbers in k range as they are useless
            while(r-h>0 && A[Q[r-1]] < A[i]) r--;
            // q contains index... r contains content
            Q[r++] = i;
            if(i >= k - 1) ret[idx++] = A[Q[h]];
            i++;
        }
        return ret;
    }
}
