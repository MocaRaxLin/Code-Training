package BinarySearch;

import java.util.Random;

public class No880RandomPickwithWeight {
	// no test case for this problem
	// please practice on leetcode directly.
	// https://leetcode.com/problems/random-pick-with-weight/description/
	
	Random r;
    int[] A;
    int bound;
    public No880RandomPickwithWeight(int[] w) {
    	// -> O(n), n = w.length
        r = new Random();
        A = new int[w.length];
        A[0] = w[0];
        for(int i = 1; i < w.length; i++) A[i] = A[i-1] + w[i];
        bound = A[A.length-1];
    }
    
    public int pickIndex() {
    	// -> O(logn), n = A.length
        int i = 0, j = A.length;
        int key = r.nextInt(bound);
        while(i < j){
            int m = i + (j-i)/2;
            if(A[m] > key) j = m;
            else i = m + 1;
        }
        return i;
    }
    
    // A[m] > key means index we want is in [i:m]
    // key >= A[m] ... m can't be the index
    
    // eg. w = [2,3,6,1] -> A = [2,5,11,12]
    // A implies 0 0 1 1 1 2 2 2 2 2 2 3
    //               2     5           11 12
    // if key = 7, m = 2 -> A[2] = 11 > 7
    // -> index 3 start at 11
    // then 2 is a possible index we want, so j = m.
    //
    // m = 1 -> A[1] = 5 < 7
    // -> index 2 start at 5, so index 1 is not possible.

}
