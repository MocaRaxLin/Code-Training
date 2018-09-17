package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No31NextPermutation {

	public static void main(String[] args) {
		No31NextPermutation sol = new No31NextPermutation();
		Parser parser = new Parser();
		String t = "[1,2,3]\n" + 
				"[1,2,4,6,3]\n" + 
				"[2,4,7,9,1,4,3,2,1]\n" + 
				"[5,4,3,2,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			sol.nextPermutation(A);
			System.out.println(Arrays.toString(A));
		}
	}
	
	public void nextPermutation(int[] A) {
		// --> O(n), where n = A.length
		
		// Scan from back, find the first descending index at A[i-1] < A[i].
		// Scan from i to end, find the lowest A[idx] which is greater then A[i-1].
		// Swap A[idx] and A[i-1], and reverse A[i:end]
		
        if(A.length < 2) return;
        int i = A.length - 1;
        while(i > 0){
            if(A[i-1] < A[i]) break;
            i--;
        }
        
        if(i > 0){
            int idx = i;
            while(idx < A.length){
                if(A[i-1] >= A[idx]) break;
                idx++;
            }
            idx--;
            swap(A, i-1, idx);
        }
        
        reverse(A, i, A.length-1);
    }
    
	// nice reverse funciton
    private void reverse(int[] A, int i, int j){
        while(i < j) swap(A, i++, j--);
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
