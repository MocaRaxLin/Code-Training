package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No75SortColors {

	public static void main(String[] args) {
		No75SortColors sol = new No75SortColors();
		Parser parser = new Parser();
		String t = "[2,0,2,1,1,0]\n" + 
				"[0]\n" + 
				"[1]\n" + 
				"[2]\n" + 
				"[0,1,2,0,1,2]\n" + 
				"[2,1,0,1,2]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			sol.sortColors(A);
			System.out.println(Arrays.toString(A));
		}
	}

	public void sortColors(int[] A) {
        // --> time = O(n), where n = A.length.
        
        // 1-pass in-place solution.
        // let i, j, k keep record of next position to put 0, 1, 2.
        // The whole process will be like part of qicksort in O(n) seperating
        
        if(A.length < 2) return;
        int i = 0, j = 0, k = 0;
        while(k < A.length){
            if(A[k] == 0){
                swap(A, j, k);
                swap(A, i, j);
                i++; j++;
            }else if(A[k] == 1){
                swap(A, j, k);
                j++;
            }
            k++;
        }
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i]; A[i] = A[j]; A[j] = tmp;
    }
}
