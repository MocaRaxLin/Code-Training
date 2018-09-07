package ArrayMatrix;

import java.util.Arrays;

public class No667BeautifulArrangementII {

	public static void main(String[] args) {
		No667BeautifulArrangementII sol = new No667BeautifulArrangementII();
		String t = "3\n" + 
				"2\n" + 
				"5\n" + 
				"3\n" + 
				"7\n" + 
				"4\n" + 
				"7\n" + 
				"6\n" + 
				"2\n" + 
				"1";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i+=2) {
			int n = Integer.parseInt(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int[] ans = sol.constructArray(n, k);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] constructArray(int n, int k) {
		// --> O(n)
		
		// Intuition:
		// To make distance difference, we take head and rear alternatively.
		// eg. 1 to N -> we arrage array A like this = [1, N, 2, N-2, 3, N-3, ...]
		// Until we get (k-1) differences, we fill up the rest of space by
		// increasing or decreasing numbers with absolute differnece 1.
		
        int i = 1, j = n;
        int[] A = new int[n];
        int idx = 0;
        boolean up = true;
        while(k-- > 1){ // leave the last k for distance = 1
            if(up){
                A[idx++] = i++;
                up = false;
            }else{
                A[idx++] = j--;
                up = true;
            }
        }
        while(idx < A.length){
            if(up) A[idx++] = i++;
            else A[idx++] = j--;
        }
        return A;
    }
}
