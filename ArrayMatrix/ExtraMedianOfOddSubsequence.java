package ArrayMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class ExtraMedianOfOddSubsequence {

	public static void main(String[] args) {
		ExtraMedianOfOddSubsequence sol = new ExtraMedianOfOddSubsequence();
		Parser parser = new Parser();
		String t = "[3,4,8,1,7,10,2]\n" + 
				"[1,2,3,4,5,6,7,8,9,10]\n" + 
				"[1,2,1,2,1,2,1,2]\n" + 
				"[5,6,1]\n" + 
				"[7,3,5,1,-4,3,2,-7]\n" + 
				"[1]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			List<Integer> ans = sol.getMedians(A);
			System.out.println(Arrays.toString(ans.toArray()));
		}
	}

	public List<Integer> getMedians(int[] A) {
		// --> O(nnlogn), where n = A.length
		// n -> l = 1 to n, nlogn -> for each l sort l elements, remove and binary insert the rest.
		
		List<Integer> ret = new LinkedList<Integer>();
		int n = A.length;
		for(int l = 1; l <= n; l+=2) {
			List<Integer> tmp = new ArrayList<Integer>();
			int i = 0, j = 0;
			for(; j < l; j++) tmp.add(A[j]);
			Collections.sort(tmp);
			
			// System.out.println("Length of "+ l);
			// System.out.println("start at " + i +", median is "+ tmp.get(l/2));
			
			ret.add(tmp.get(l/2));
			while(j < n) {
				tmp.remove(new Integer(A[i++])); // O(logN) or O(n)
				binaryInsert(tmp, A[j++]); // O(logN) or O(n)
				// System.out.println("start at " + i +", median is "+ tmp.get(l/2));
				ret.add(tmp.get(l/2));
			}
		}
		return ret;
	}

	private void binaryInsert(List<Integer> tmp, int t) {
		int i = 0, j = tmp.size();
		while(i < j) {
			int m = i + (j-i)/2;
			if(t < tmp.get(m)) j = m;
			else i = m+1;
		}
		if(i == tmp.size()) tmp.add(t);
		else tmp.add(i, t);
	}

}
