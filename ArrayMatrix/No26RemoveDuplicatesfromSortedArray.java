package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No26RemoveDuplicatesfromSortedArray {

	public static void main(String[] args) {
		No26RemoveDuplicatesfromSortedArray sol = new No26RemoveDuplicatesfromSortedArray();
		Parser parser = new Parser();
		String t = "[1,1,2]\n" + 
				"[0,0,1,1,1,2,2,3,3,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int size = sol.removeDuplicates(A);
			int[] ans = Arrays.copyOfRange(A, 0, size);
			System.out.println(Arrays.toString(ans));
		}
	}
	
    public int removeDuplicates(int[] A) {
        if(A.length == 0) return 0;
        int size = 1;
        int last = A[0];
        for(int i = 1; i < A.length; i++){
            if(last != A[i]){
                A[size++] = A[i];
                last = A[i];
            }
        }
        return size;
    }

}
