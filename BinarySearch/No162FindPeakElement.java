package BinarySearch;

import Util.Parser;

public class No162FindPeakElement {

	public static void main(String[] args) {
		No162FindPeakElement sol = new No162FindPeakElement();
		Parser parser = new Parser();
		String t = "[1,2,3,1]\n" + 
				"[3]\n" + 
				"[2,4]\n" + 
				"[5,3]\n" + 
				"[2,4,3]\n" + 
				"[1,2,3,1]\n" + 
				"[1,2,1,3,5,6,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.findPeakElement(A);
			System.out.println(ans);
		}
	}

	public int findPeakElement(int[] A) {
		// --> time = O(logn), where n = A.length
		
        if(A.length == 1) return 0;
        int i = 0, j = A.length - 1;
        
        while(i <= j){ // I need overlap i = j to count m
            int m = i + (j-i)/2;
            
            if(m == 0){
                if(A[m] > A[m+1]) return m;
                else i = m + 1;
            }else if(m == A.length - 1){
                if(A[m-1] < A[m]) return m;
                else j = m - 1;
            }else{
                //peak
                if(A[m-1] < A[m] && A[m] > A[m+1]) return m;
                // decreasing
                else if(A[m-1] > A[m] && A[m] > A[m+1]) j = m - 1;
                // ascending or valley
                else i = m + 1;
            }
        }
        return -1;
        
    }
}
