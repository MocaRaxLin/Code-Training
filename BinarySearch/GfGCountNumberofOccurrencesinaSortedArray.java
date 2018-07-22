package BinarySearch;

import Util.Parser;

public class GfGCountNumberofOccurrencesinaSortedArray {

	public static void main(String[] args) {
		GfGCountNumberofOccurrencesinaSortedArray sol = new GfGCountNumberofOccurrencesinaSortedArray();
		Parser parser = new Parser();
		String t = "[1,2,2,3,3,3]\n"
				+ "3\n"
				+ "[1,3,4,4,7,7,7]\n"
				+ "5\n"
				+ "[1,1,1]\n"
				+ "1";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int T = Integer.parseInt(s[i+1]);
			int ans = sol.occurenceInSortedArray(A, T);
			System.out.println(ans);
		}
	}

	private int occurenceInSortedArray(int[] A, int T) {
		if(A.length == 0 || T < A[0] || A[A.length-1] < T) return -1;
		
		// find rightmost T
		// if A[m] <= T -> l in [m, j]
		// else T < A[m] -> l in [i, m-1]
		int i = 0, j = A.length-1;
		while(j - i > 1) {
			int m = i + (j-i)/2;
			if(A[m] <= T) i = m;
			else j = m-1;
		}
		if(j < A.length && A[j] != T) return -1; // T is not in A
		int r = j;
		
		// find leftmost T
		// if T <= A[m] -> r in [i, m]
		// else A[m] < T -> r in [m+1, j]
		i = 0;
		j = A.length-1;
		while(j - i > 1) {
			int m = i + (j-i)/2;
			if(T <= A[m]) j = m;
			else i = m+1;
		}
		int l = i; // T must in A
		System.out.println(l +"," + r);
		return r - l + 1;
	}

}
