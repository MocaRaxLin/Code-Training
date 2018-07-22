package DP;

public class No801MinimumSwapsToMakeSequencesIncreasing {

	public static void main(String[] args) {
		No801MinimumSwapsToMakeSequencesIncreasing sol = new No801MinimumSwapsToMakeSequencesIncreasing();
		
		// Input Constraint:
		// -> A, B are arrays with the same length,
		//    and that length will be in the range [1, 1000].
		// -> A[i], B[i] are integer values in the range [0, 2000]
		
		int[][] input = new int[][] {
			{1,3,5,4},{1,2,3,7},
			{1,3,5,4,9},{1,2,3,7,10},
			{0,3,5,8,9},{2,1,4,6,9},
			{3},{1},
			{1,4},{3,2},
			{4,3},{2,7}
		};
		for(int i = 0; i < input.length; i+=2) {
			int ans = sol.minSwap(input[i], input[i+1]);
			System.out.println(ans);
		}
	}
	
	public int minSwap(int[] A, int[] B) {
		// time --> O(N), space --> O(1), where N = A.length
		
		// Intuition:
		// Use array to find minimum value.
		// Also, the answer of minSwap from index 0 to i depends on answer from 0 to i-1.
		
		// NOTE:
		// Speak to comparison, it is common to use 4 variables to think.
		// eg a0, a1, b0, b1, where a in A, b in B.
		
		// nonSwap[i] denotes the minimum number of swap from 0 to i if we do not swap A[i] and B[i]
		// swap[i] denotes the minimum number of swap from 0 to i if we do swap A[i] and B[i]
		// Since we only use the last record, this space complexity can reduce to O(1).
		
		// A = [a0, a1]
		// B = [b0, b1]
		// if a0 < a1 and b0 < b1, then we allow both swap or not swap.
		// if a0 < b1 and b0 < a1, then we allow exactly one column swap.
		
        int n1 = 0, n2 = 0;
        int s1 = 1, s2 = 0;
        for(int i = 1; i < A.length; i++){
            n2 = Integer.MAX_VALUE;
            s2 = Integer.MAX_VALUE;
            if(A[i-1] < A[i] && B[i-1] < B[i]){
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if(A[i-1] < B[i] && B[i-1] < A[i]){
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n2, s2);
    }
}
