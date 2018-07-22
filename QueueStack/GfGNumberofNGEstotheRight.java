package QueueStack;

import java.util.Arrays;

import Util.Parser;

public class GfGNumberofNGEstotheRight {

	public static void main(String[] args) {
		GfGNumberofNGEstotheRight sol = new GfGNumberofNGEstotheRight();
		// Next Greater Element
		Parser parser = new Parser();
		String t = "[3, 4, 2, 7, 5, 8, 10, 6]\n"
				+ "2\n"
				+ "0\n"
				+ "5\n"
				+ "[3, 4, 2, 7, 5, 8, 10, 6]\n"
				+ "3\n"
				+ "3\n"
				+ "6\n"
				+ "1";
		t = t.replaceAll(" ","");
		String[] s = t.split("\n");
		int i = 0;
		while(i < s.length) {
			int[] A = parser.parseArray(s[i++]);
			int[] noNGE = sol.findNoNGE(A);
			int q = Integer.parseInt(s[i++]);
			for(int j = 0; j < q; j++) {
				int idx = Integer.parseInt(s[i++]);
				int ans = noNGE[idx];
				System.out.println(ans);
			}
			System.out.println();
		}
	}

	public int[] findNoNGE(int[] A) {
		// --> time = O(n), space = O(n), where n = A.length
		
		// 1. Find the Next Greater Number,
		//    store their indices to NGE
		// 2. Compute the length of NGE starting from each index,
		//    accumulate from end to front,
		//    store those lengthes into LEN_NGE(len).
		
		int[] stack = new int[A.length];
		int size = 0;
		int[] NGE = new int[A.length];
		for(int i = 0; i < A.length; i++) {
			while(size > 0 && A[stack[size-1]] < A[i])
				NGE[stack[--size]] = i;
			stack[size++] = i;
		}
		while(size > 0) NGE[stack[--size]] = -1;
		
		System.out.println("NGE = " + Arrays.toString(NGE));
		int[] len = new int[NGE.length];
		for(int i = len.length-1; i >= 0; i--) {
			if(NGE[i] == -1) len[i] = 0;
			else len[i] = len[NGE[i]] + 1;
		}
		System.out.println("LEN_NGE = " + Arrays.toString(len));
		return len;
	}

}
