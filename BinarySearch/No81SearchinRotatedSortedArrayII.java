package BinarySearch;

import Util.Parser;

public class No81SearchinRotatedSortedArrayII {

	public static void main(String[] args) {
		No81SearchinRotatedSortedArrayII sol = new No81SearchinRotatedSortedArrayII();
		Parser parser = new Parser();
		String t = "[2,5,6,0,0,1,2]\n" + 
				"0\n" + 
				"[2,5,6,0,0,1,2]\n" + 
				"3\n" + 
				"[1,1,1,1,1,3,1,1,1]\n" + 
				"3\n" + 
				"[1,1]\n" + 
				"0\n" + 
				"[3,1]\n" + 
				"1\n" + 
				"[1,3]\n" + 
				"0\n" + 
				"[0,0,1,1,2,0]\n" + 
				"2";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			boolean ans = sol.search(A, T);
			System.out.println(ans);
		}
	}

    public boolean search(int[] A, int T) {
        // --> time = O(logn), where n = A.length
        
        // Think about no duplicate problem
        // And then what will be influenced if A has duplicate numbers?
        // 
        // In the old problem, if the first section is incresing order,
        // then the second one must have the minimal value. 
        // In constrast, it is similar.
        //
        // A[i] < A[m] as the non-duplicated problem.
        // But what if the first section is not in increasing order?
        // What does this imply? Either A[i] == A[m] or A[i] > A[m]
        // A[i] > A[m] will make this as the old problem.
        // A[i] == A[m], this is the point! (eg. [i:m] => [0,1,2,2,0], [0,0,0], [2,2,0,1,2])
        // we don't know the property of the 1st section.
        //
        // Let check 2nd one to see more information.
        // A[m] < A[j] => increasing, A[m] > A[j] has minimum.
        // but A[m] == A[j]?
        // All cases of A[i] == A[m] == A[j] we cannot divide the problem
        // Knowing which side is the increasing order and the other has minimum.
        // So we shrink down by i++ and j--
        // Try to find a case A[i] != A[m] or A[m] != A[j]
        
        if(A.length == 0) return false;
        int i = 0, j = A.length - 1;
        while(j - i > 1){ // i j are adjacent!
            int m = i + (j-i)/2;
            
            if(A[m] == T) return true;
            if(A[i] == A[m] && A[m] == A[j]){
                i++; j--;
            }else if(A[i] <= A[m]){
                if(A[i] <= T && T < A[m]) j = m;
                else i = m;
            }else{
                if(A[m] < T && T <= A[j]) i = m;
                else j = m;
            }
        }
        // if while() using j-i > 1, we have to take both A[i] and A[j] into consideration!
        return A[i] == T || A[j] == T; 
    }
}
