package BinarySearch;

import Util.Parser;

public class No33SearchinRotatedSortedArray {

	public static void main(String[] args) {
		No33SearchinRotatedSortedArray sol = new No33SearchinRotatedSortedArray();
		Parser parser = new Parser();
		String t = "[4,5,6,7,0,1,2]\n" + 
				"0\n" + 
				"[1,2]\n" + 
				"1\n" + 
				"[1,2]\n" + 
				"2\n" + 
				"[0]\n" + 
				"0\n" + 
				"[4,5,6,7,0,1,2]\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			int ans = sol.search(A, T);
			System.out.println(ans);
		}
	}

    public int search(int[] A, int T) {
        // --> time = O(logn)
        // section 1 , section 2
        //
        // case 1:
        //     section1 (increasing order)
        //         1) T in [i,m-1]
        //         2) T in [m+1,j]
        //     section2 (has minimum)
        //         1) T in [i,m-1]
        //         2) T in [m+1,j]
        //     
        // case 2:
        //     section1 (has minimum)
        //         1) T in [i,m-1]
        //         2) T in [m+1,j]
        //     section2 (increasing order)
        //         1) T in [i,m-1]
        //         2) T in [m+1,j]
        
        if(A.length == 0) return -1;
        int i = 0, j = A.length - 1;
        while(i < j){
            int m = i + (j-i)/2;
            if(A[m] == T) return m;
            if(A[i] <= A[m]){
                if(A[i] <= T && T < A[m]) j = m-1;
                else i = m+1;
            }else{
                if(A[m] < T && T <= A[j]) i = m+1;
                else j = m-1;
            }
        }
        return A[i] == T? i: -1;
    }
}
