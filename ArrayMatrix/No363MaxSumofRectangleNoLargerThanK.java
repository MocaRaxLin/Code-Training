package ArrayMatrix;

import java.util.TreeSet;

import Util.Parser;

public class No363MaxSumofRectangleNoLargerThanK {

	public static void main(String[] args) {
		No363MaxSumofRectangleNoLargerThanK sol = new No363MaxSumofRectangleNoLargerThanK();
		Parser parser = new Parser();
		String t = "[[1,0,1],[0,-2,3]]\n" + 
				"2\n" + 
				"[[3,4,-1,1,0],[1,-1,-3,2,0],[5,-9,7,2,-7]]\n" + 
				"4\n" + 
				"[[]]\n" + 
				"9\n" + 
				"[[1,-1],[2,2],[5,-7],[3,-1],[0,3]]\n" + 
				"5\n" + 
				"[[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]\n" + 
				"-1\n" + 
				"[[2,2,-1]]\n" + 
				"3\n" + 
				"[[2,2,-1]]\n" + 
				"0\n" +
				"[[5,-4,-3,4],[-3,-4,4,5],[5,1,5,-4]]\n" + 
				"10";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] matrix = parser.parseMatrix(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.maxSumSubmatrix(matrix, k);
			System.out.println(ans);
		}
	}

	public int maxSumSubmatrix(int[][] matrix, int k) {
		// --> time = O(nlogn*m^2),
		// where n = number of column, m = number of row
		
		// Tip:
		// When we do matrix processing, think of row by row computing.
		
		// Follow up:
		// if m >> n, we move left to right based on column
		// -> O(mlogm*n^2)
		// or we just as usual move top to bottom row based.
		
		// using nlogn getMaxSumInSubarray() + Kadane's
		// 27 / 27 test cases passed.
		// Runtime: 37 ms
		
		// using nlogn getMaxSumInSubarray()
		// 27 / 27 test cases passed.
		// Runtime: 150 ms
		
		// using n^2 getMaxSumInSubarray()
		// 27 / 27 test cases passed.
    	// Runtime: 181 ms
		
		int m = matrix.length; if(m == 0) return 0;
        int n = matrix[0].length; if(n == 0) return 0;
        
        int iterator = Math.min(m, n);
        int dpIterator = Math.max(m, n);
        
        int ret = Integer.MIN_VALUE; //maximal sum <= k
        for(int t = 0; t < iterator; t++){ // top, left
            int[] dp = new int[dpIterator];
            for(int b = t; b < iterator; b++){ // bottom, right
                for(int i = 0; i < dpIterator; i++) dp[i] += m < n ? matrix[b][i]: matrix[i][b];
                int tmp = getMaxSumInSubarray(dp, k); // O(?)
                if(tmp == k) return k;
                ret = Math.max(ret, tmp);
            }
        }
        
        return ret;
    }
	public int getMaxSumInSubarray(int[] A, int k){
		// --> time O(nlogn)
        
        // Sum(A[i:j]) = preSum[j+1] - preSum[i]
        // I want the maximal sum <= k; that is,
        // to find the i and "i <= j" such that MAX(preSum[j+1] - preSum[i]) <= k
        // preSum[j+1] - k <= preSum[i]
        // So we want to find the smallest preSum[i] which is greater than preSum[j+1] - k
        // Use TreeSet.ceiling(key) -> find the least element that >= key
		// ps. TreeSet.floor(key) -> the greastest element that <= key
		
        // eg. A = [2,2,-1], k = 0, preSum[0] = 0, rbt = [0]
        // j = 0:
        // preSum[1] = A[0] + preSum[0] = 2
        // ceilKey = 2 - 0 = 2, rbt = [0]
        // preSum_i = null
        //
        // j = 1:
        // preSum[2] = A[1] + preSum[1] = 4
        // ceilKey = 4 - 0 = 4, rbt = [0, 2]
        // preSum_i = null
        //
        // j = 2:
        // preSum[3] = A[2] + preSum[2] = 3
        // ceilKey = 3 - 0 = 3, rbt = [0, 2, 4]
        // preSum_i = 4
        // maxSum = 3 - 4 = -1
        
		// To speed up:
		// Using Kadane's Algorithm to check maximal sum of subarray > k ?
		// Just take O(n) time, so it is a fast and good preprocess.
		// If kadane's sum > k, then use red-black binary search tree
		// else (kadane's sum <= k) just return kadane's sum,
		// because we cannot make any greater sum than this result.
        
        // O(n)
        int curSum = 0;
        int kadaneMaxSum = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
        	// give up the previous sum if it is < 0
        	curSum = Math.max(curSum + A[i], A[i]);
        	kadaneMaxSum = Math.max(kadaneMaxSum, curSum);
        }
        if(kadaneMaxSum <= k) return kadaneMaxSum;
        
        // O(nlogn)
        int[] preSum = new int[A.length+1];
        int maxSum = Integer.MIN_VALUE;
        // Instance of Red-Black Binary Search Tree => TreeSet
        TreeSet<Integer> rbt = new TreeSet<Integer>();
        rbt.add(preSum[0]);
        for(int j = 0; j < A.length; j++){
        	preSum[j+1] = A[j] + preSum[j];
            Integer preSum_i = rbt.ceiling(preSum[j+1] - k); // O(logn)
            if(preSum_i != null) maxSum = Math.max(maxSum, preSum[j+1] - preSum_i);
            rbt.add(preSum[j+1]);
        }
        
        return maxSum;
	}
	
    public int getMaxSumInSubarray0(int[] A, int k){
    	// --> time O(n^2), where n = A.length
    	
        int[] preSum = new int[A.length+1];
        for(int i = 0; i < A.length; i++) preSum[i+1] = A[i] + preSum[i];
        // preSum 0  1  2   3    4  ... A.length:    n
        //        -  0 01 012 0123      sum(A): 0~(n-1)
        // I want A[1:3], then I compute by preSum[4] - preSum[1]
        
        // find maximum in sums of all subarrays A[i:j]
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0; i < A.length; i++){
            for(int j = i; j < A.length; j++){
                // I want A[i:j]
                int subSum = preSum[j+1] - preSum[i];
                if(subSum == k) return k;
                maxSum = subSum < k && subSum > maxSum ? subSum: maxSum;
            }
        }
        return maxSum;
    }
}
