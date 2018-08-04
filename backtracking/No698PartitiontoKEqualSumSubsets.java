package backtracking;

import java.util.Arrays;

import Util.Parser;

public class No698PartitiontoKEqualSumSubsets {

	public static void main(String[] args) {
		No698PartitiontoKEqualSumSubsets sol = new No698PartitiontoKEqualSumSubsets();
		Parser parser = new Parser();
		String t = "[4,3,2,3,5,2,1]\n" + 
				"4\n" + 
				"[2,2,10,5,2,7,2,2,13]\n" + 
				"3\n" + 
				"[1,2,3,4,3,1,4,2]\n" + 
				"5\n" + 
				"[6,5,4]\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			boolean ans = sol.canPartitionKSubsets(A, k);
			System.out.println(ans);
		}
	}

	public boolean canPartitionKSubsets(int[] A, int k) {
        // --> time = Ω(k*n), O(k*2^n)
        
		// Intuition:
		// Fill up buckets one by one
		// if there is a bucket we cannot fill to sum(A) / k, return false;
		// if we can fill up current bucket, take another one do it again.
		
        if(k == 1) return true;
        int N = A.length;
        int sumA = 0;
        for(int e: A) sumA += e;
        if(N < k || sumA % k != 0) return false;
        Arrays.sort(A);
        //System.out.println(Arrays.toString(A));
        int limit = sumA/k;
        if(A[N-1] > limit) return false;
        
        // fill up bucket one by one to limit
        boolean[] used = new boolean[N];
        // take the larger one first
        for(int i = N-1; i >= 0; i--)
            if(!used[i] && !fillUpBucket(A, i, used, limit)) return false;
        
        return true;
    }
    
    private boolean fillUpBucket(int[] A, int fromIdx, boolean[] used, int space){
        
        if(space == 0) return true;
        
        for(int i = fromIdx; i >= 0; i--){
            if(!used[i] && A[i] <= space){
                used[i] = true; // take A[i]
                if(fillUpBucket(A, i-1, used, space - A[i])) return true;
                used[i] = false; // put back A[i]
            }
        }
        return false;
    }
}
