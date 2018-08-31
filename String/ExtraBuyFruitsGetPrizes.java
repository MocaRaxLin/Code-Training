package String;

import java.util.Arrays;

import Util.Parser;

public class ExtraBuyFruitsGetPrizes {

	public static void main(String[] args) {
		ExtraBuyFruitsGetPrizes sol = new ExtraBuyFruitsGetPrizes();
		Parser parser = new Parser();
		String t = "[[a,a],[b,any,b]]\n" + 
				"[o,a,a,b,o,b]\n" + 
				"[[a,a],[b,any,b]]\n" + 
				"[b,o,b,a,a]\n" + 
				"[[a,a],[b,any,b]]\n" + 
				"[a,b,a,b,o,b]\n" + 
				"[[a,a],[a,a,b]]\n" + 
				"[a,a,a,b]\n" +
				"[[a,a],[b,any,b]]\n" + 
				"[o,a,o,a,a,b,a,o,b,a,b]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String[][] codeList = parser.parseMatrixStr(s[i]);
			String[] shoppingCart = parser.parseArrayStr(s[i+1]);
			int ans = sol.getPrize(codeList, shoppingCart);
			System.out.println(ans);
		}
	}

	private int getPrize(String[][] A, String[] B) {
		if(A == null || B == null) return 0;
		else if(A.length == 0 && B.length == 0) return 1; // always satisfy
		else if(A.length == 0 || B.length == 0) return 0; // either one is empty is not satisfied
		
		int i = 0, j = 0; //i for A, j for starting index of B
		while(j < B.length && i < A.length) {
			// int start = hasPatternAt(B, j, A[i]);
			int start = KMPhasPatternAt(B, j, A[i]);
			if(start == -1) return 0;
			//System.out.println(Arrays.toString(Arrays.copyOfRange(B, start, start+A[i].length)));
			j = start + A[i].length;
			i++;
		}
		return i == A.length? 1: 0;
	}

	private int KMPhasPatternAt(String[] fruits, int start, String[] p) {
		
		// longest common prefix
		//System.out.println("pattern: " + Arrays.toString(p));
		int[] lcp = new int[p.length];
		int i = 1, j = 0;
		while(i < p.length) {
			if(p[j].equals("any") || p[i].equals(p[j])) lcp[i++] = ++j; // store length of LCP
			else if(j == 0) lcp[i++] = 0;
			else j = lcp[j - 1];
		}
		//System.out.println("LCP of pattern: " + Arrays.toString(lcp));

		
		i = start;
		j = 0;
		while(i < fruits.length) {
			if(p[j].equals("any") || fruits[i].equals(p[j])) {
				i++;
				j++;
				if(j == p.length) return i - p.length;
			}else if(j == 0) i++;
			else j = lcp[j-1];
		}
		return -1;
	}

	// naive pattern matching
	private int hasPatternAt(String[] fruits, int start, String[] p) {
		for(int i = start; i < fruits.length; i++) {
			int idx = i;
			int j = 0;
			for(; j < p.length; j++) {
				if(p[j].equals("any") || p[j].equals(fruits[idx])) idx++;
				else break;
			}
			if(j == p.length) return i;
		}
		return -1; // pattern not found
	}

}
