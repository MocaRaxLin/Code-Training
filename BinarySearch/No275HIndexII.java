package BinarySearch;

import Util.Parser;

public class No275HIndexII {

	public static void main(String[] args) {
		No275HIndexII sol = new No275HIndexII();
		Parser parser = new Parser();
		String t = "[0,1,3,5,6]\n" + 
				"[]\n" + 
				"[0,1,4,5,6]\n" + 
				"[0,4,4,5,6]\n" + 
				"[0]\n" + 
				"[0,0]";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.hIndex(A);
			System.out.println(ans);
		}
	}
	
    public int hIndex(int[] A) {
    	// --> O(logn), where n = A.length
    	
    	// Using binary search in sorted array A
    	// if A[m] == h: we got h
    	// if A[m] > h: expected h in [i, m-1]
    	// if A[m] < h: expected h in [m+1, j]
    	
    	// Finally when should we terminate our loop?
    	// j - i > 1 ? -> want to deal with A[i:i+1]
    	// i < j ?     -> want to deal with A[i]
    	// i <= j ?    -> want to deal with A[i-1:i]
    	//
    	// Think about edge cases
    	// 1. h = 0 citations
    	// 2. h = n citations
    	//                 A[m] h   A[m] h
    	// eg. A = [0,0] -> 0 < 2 -> 0 < 1 -> i = 2, j = 1
    	//     we want h = 0 = n - i, so let's us i <= j.
    	// eg. A = [2,2] -> 2 == 2 -> h = 2
    	// eg. A = [3,3] -> 3 > 2 -> j = 0 - 1 = -1, i still = 0
    	//     we want h = 2 -> n - i
    	
        int n = A.length;
        int i = 0, j = n - 1, h = 0;
        while(i <= j){
            int m = i + (j-i) / 2;
            h = n - m;
            if(A[m] == h) return h;
            else if(A[m] > h) j = m - 1;
            else i = m + 1;
        }
        return n-i;
    }
    
    // it also works
    public int hIndex0(int[] A) {
        int n = A.length;
        int i = 0, j = n, h = 0;
        while(i < j){ // be careful when A.length < 2
            int m = i + (j-i) / 2;
            h = n - m;
            if(A[m] < h) i = m + 1;
            else j = m;
        }
        return n-i;
    }
}
