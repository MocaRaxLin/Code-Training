package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.Parser;

public class ExtraWhosTheClosest {

	public static void main(String[] args) {
		ExtraWhosTheClosest sol = new ExtraWhosTheClosest();
		Parser parser = new Parser();
		String t = "hackerrank\n"
				+ "[4,5,0,9]\n"
				+ "babab\n"
				+ "[0,2,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] query = parser.parseArray(s[i+1]);
			int[] ans = sol.closestIdx(s[i], query);
			System.out.println(Arrays.toString(ans));
		}
	}
	
	private int[] closestIdx(String s, int[] query) {
		// --> O(n+qlogn), where n = s.length(), q = query.length
		
		// Intuition:
		// Store indeices of each different char, 
		// and use binary search to get left and right indices of the same char.
		// Compare the distance to query q, return the index with smaller distance to q.
		
		int[] ret = new int[query.length];
		if(s.length() == 0) {
			Arrays.fill(ret, -1);
			return ret;
		}
		
		Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(!map.containsKey(c)) map.put(c, new ArrayList<Integer>());
			map.get(c).add(i);
		}
		
		for(int idx = 0; idx < query.length; idx++) {
			int q = query[idx];
			char c = s.charAt(q);
			List<Integer> list = map.get(c);
			int i = 0, j = list.size(), m = -1;
			while(i < j) {
				m = i + (j-i)/2;
				int idxM = list.get(m);
				if(idxM < q) i = m+1;
				else if(q < idxM) j = m;
				else break;
			}
			//System.out.println("idx of m: " + list.get(m));
			if(0 < m && m < list.size() - 1) {
				int h = list.get(m-1), t = list.get(m+1);
				ret[idx] = t - q < q - h? t: h;
			}else if(m == 0) ret[idx] = m + 1 < list.size()? list.get(m+1): -1;
			else ret[idx] = m - 1 >= 0? list.get(m-1): -1;
		}
		return ret;
	}
}





