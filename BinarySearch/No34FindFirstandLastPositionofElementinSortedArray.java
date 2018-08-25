package BinarySearch;

import java.util.Arrays;

import Util.Parser;

public class No34FindFirstandLastPositionofElementinSortedArray {

	public static void main(String[] args) {
		No34FindFirstandLastPositionofElementinSortedArray sol = new No34FindFirstandLastPositionofElementinSortedArray();
		Parser parser = new Parser();
		String test = "[5,7,7,8,8]\n" + 
				"8\n" + 
				"[1,7]\n" + 
				"3\n" + 
				"[0,0,0,0]\n" + 
				"0\n" + 
				"[0,1,1,1,1,1,1,1,1,2]\n" + 
				"1\n" + 
				"[1]\n" + 
				"0\n" + 
				"[2,2]\n" + 
				"3";
		String[] s = test.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int t = Integer.parseInt(s[i+1]);
			int[] ans = sol.searchRange(A, t);
			System.out.println(Arrays.toString(ans));
		}
	}

    public int[] searchRange(int[] A, int t) {
    	
    	// --> O(nlogn), where n = A.length
    	
    	// Find first, find last using binary search
    	
    	// Careful:
    	// -> j start at A.length
    	// -> m = i + (j-i)/2
    	// -> i will stand at j eventually, using while(i<j)...i=m+1 or j=m structure.
    	//
    	// -> find first: exclude A[m] by i = m+1, when A[m] < t
    	// -> i may reach A.length i.e. not found
    	//
    	// -> find last: exclude A[m] by i = m+1, when A[m] <= t
    	// -> A[i] will be 1st element > t, so A[i-1] must be t,
    	//    but similarly i-1 may < 0 i.e. not found
    	
    	
        if(A.length == 0) return new int[]{-1, -1};
        int i = 0, j = A.length;
        while(i < j){
            int m = i + (j-i)/2;
            if(A[m] < t) i = m + 1;
            else j = m;
        }
        int left = i < A.length && A[i] == t ? i: -1;
        
        i = 0;
        j = A.length;
        while(i < j){
            int m = i + (j-i)/2;
            if(A[m] <= t) i = m + 1;
            else j = m;
        }
        int right = i > 0 && A[i-1] == t? i - 1: -1;
        return new int[]{left, right};
    }
}
