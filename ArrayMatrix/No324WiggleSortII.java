package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No324WiggleSortII {

	public static void main(String[] args) {
		No324WiggleSortII sol = new No324WiggleSortII();
		Parser parser = new Parser();
		String t = "[1,5,1,1,6,4]\n" + 
				"[1,3,2,2,3,1]\n" + 
				"[9,6,4,6,7,8,8]\n" + 
				"[13,6,5,5,2,4]\n" + 
				"[1,2,2,2,4,4,5]\n" + 
				"[1,5,1,1,6,4]\n" + 
				"[2,3,3,2,2,2,1,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			sol.wiggleSort(A);
			System.out.println(Arrays.toString(A));
		}
	}
	
	public void wiggleSort(int[] A) {
        if(A.length < 2) return;
        // or you can just sort, something it will be faster
        int n = A.length;
        int m = quickSelect(A, 0, n-1, n/2);
        // Arrays.sort(A);
        // int m = n/2;
        
        // It is hard to understand, so I copy from to pass testcases.
        // https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
        int left = 0, i = 0, right = n - 1;
        int median = A[m];
        while (i <= right) {
            if (A[newIndex(i,n)] > median) swap(A, newIndex(left++,n), newIndex(i++,n));
            else if (A[newIndex(i,n)] < median) swap(A, newIndex(right--,n), newIndex(i,n));
            else i++;
        }
        
    }
    
    private int newIndex(int index, int n) {
        return (1 + 2*index) % (n | 1);
    }
    
    private int quickSelect(int[] A, int s, int e, int t){
        int i = s, j = s;
        while(j < e){
            if(A[j] < A[e]) swap(A, i++, j);
            j++;
        }
        swap(A, i, e);
        if(i == t) return i;
        else if(i < t) return quickSelect(A, i+1, e, t);
        else return quickSelect(A, s, i-1, t);
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i]; A[i] = A[j]; A[j] = tmp;
    }

}
