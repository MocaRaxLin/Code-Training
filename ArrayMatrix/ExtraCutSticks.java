package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class ExtraCutSticks {

	public static void main(String[] args) {
		ExtraCutSticks sol = new ExtraCutSticks();
		Parser parser = new Parser();
		String t = "[3,5,3,1,5,7,9,3,9,3]\n" + 
				"[1,8]\n" + 
				"[3]\n" + 
				"[1]\n" +
				"[1,1,3,4,7,16]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int[] ans = sol.noRestStick(A);
			System.out.println(Arrays.toString(ans));
		}
	}

	private int[] noRestStick(int[] A) {
		Arrays.sort(A);
		System.out.println(Arrays.toString(A));
		int[] ret = new int[A.length];
		int N = A.length;
		ret[0] = N;
		for(int i = 1; i < A.length; i++) {
			if(A[i-1] < A[i]) N = A.length - i;
			ret[i] = N;
		}
		return ret;
	}

}
