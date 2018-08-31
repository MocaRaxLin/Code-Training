package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class ExtraShipBackFro {

	public static void main(String[] args) {
		ExtraShipBackFro sol = new ExtraShipBackFro();
		Parser parser = new Parser();
		String t = "[-1,3,1,3,2,5]\n" + 
				"[8,8,0,6,-4,7]\n" + 
				"9\n" + 
				"[3,9,8,8,1]\n" + 
				"[0,5,2,10]\n" + 
				"8\n" + 
				"[0,3]\n" + 
				"[0,0]\n" + 
				"1\n" + 
				"[1]\n" + 
				"[1]\n" + 
				"0\n" + 
				"[1,3,6]\n" + 
				"[2,6]\n" + 
				"8\n" +
				"[1,1,1,2,2,2,2,3,3,3,4,7]\n" +
				"[1,1,1,2,2,2,3,3,5,5,6,7,7,7]\n" +
				"13";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int T = Integer.parseInt(s[i+2]);
			int ans = sol.getOptimalDis(A, B, T);
			System.out.println(ans);
		}
	}

	private int getOptimalDis(int[] A, int[] B, int T) {
		if(A.length == 0 || B.length == 0) return Integer.MIN_VALUE;
		Arrays.sort(A);
		Arrays.sort(B);
		
		// it seems only one 2 pointer iteration needed.
		
		int maxDis = Integer.MIN_VALUE;
		int i = 0, j = B.length -1;
		while(i < A.length && j >= 0) {
			if(A[i] + B[j] > T) j--;
			else if(A[i] + B[j] == T) return T;
			else {
				maxDis = A[i] + B[j] > maxDis ? A[i] + B[j]: maxDis;
				i++;
			}
		}
		
		
		return maxDis;
	}

}
