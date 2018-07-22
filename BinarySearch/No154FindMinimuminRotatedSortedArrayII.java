package BinarySearch;

import Util.Parser;

public class No154FindMinimuminRotatedSortedArrayII {

	public static void main(String[] args) {
		No154FindMinimuminRotatedSortedArrayII sol = new No154FindMinimuminRotatedSortedArrayII();
		Parser parser = new Parser();
		String t = "[1,3,5]\n" + 
				"[2,2,2,0,1]\n" + 
				"[1,1]\n" + 
				"[0]\n" + 
				"[1,5]\n" + 
				"[5,1,3]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.findMin(A);
			System.out.println(ans);
		}
	}

    public int findMin(int[] A) {
        // --> time = O(n), average = O(logn)
        
        // Consider 9 cases and classify them
    	// Combination of
        // A[i] < A[m], A[i] = A[m], A[i] > A[m]
        // A[j] < A[m], A[j] = A[m], A[j] > A[m]
        
        int i = 0, j = A.length - 1;
        while(j-i>1){
            int m = i + (j-i)/2;
            if(A[i] == A[m] && A[m] == A[j]) i++;
            else if( A[i] <= A[m] && A[m] > A[j]) i = m;
            else j = m;
        }
        return Math.min(A[i], A[j]);
    }
}

