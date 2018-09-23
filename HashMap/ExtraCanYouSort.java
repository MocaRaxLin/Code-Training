package HashMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class ExtraCanYouSort {

	public static void main(String[] args) {
		ExtraCanYouSort sol = new ExtraCanYouSort();
		Parser parser = new Parser();
		String t = "[1,1,1,1,2,2,2,3,3,4]\n" + 
				"[-1,-1,2,2,3,3,3,4,-2,9]\n" + 
				"[1,6,4,2,2,2,2,4,6]\n" + 
				"[1]\n" + 
				"[0]\n" + 
				"[2,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			sol.sortByFreq(A);
			System.out.println(Arrays.toString(A));
		}
	}

	Map<Integer, Integer> map;
	private void sortByFreq(int[] A) {
		int n = A.length; if(n == 0) return;
		map = new HashMap<Integer, Integer>();
		for(int e: A) {
			int freq = map.getOrDefault(e, 0);
			map.put(e, ++freq);
		}
		
		Comparator<Integer> cmp = new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return map.get(a) == map.get(b)? a - b: map.get(a) - map.get(b);
			}
		};
		
		Integer[] AInts = new Integer[A.length];
		for(int i = 0; i < A.length; i++) AInts[i] = A[i];
		Arrays.sort(AInts, cmp);
		for(int i = 0; i < A.length; i++) A[i] = AInts[i];
		
	}

}
