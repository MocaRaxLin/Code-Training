package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class ExtraPickFruits {

	public static void main(String[] args) {
		ExtraPickFruits sol = new ExtraPickFruits();
		Parser parser = new Parser();
		String t = "[]\n" + 
				"[1,2,1,3,4,3,5,1,2]\n" + 
				"[1,2,1,2,1,2,1]\n" + 
				"[1,1,2,2,1,3,3,3,3,3,4,4,5]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int[] ans = sol.sumOfFruitAtMostTwoKinds(A);
			System.out.println(Arrays.toString(ans));
		}
	}

	private int[] sumOfFruitAtMostTwoKinds(int[] A) {
		int i = 0, j = 0;
		int start = 0, maxLen = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		while(j < A.length) {
			int freq = 0;
			if(map.containsKey(A[j])) freq = map.get(A[j]);
			map.put(A[j++], ++freq);
			
			while(map.size() == 3) {
				int amount = map.get(A[i]);
				if(amount == 1) map.remove(A[i]);
				else map.put(A[i], --amount);
				i++;
			}
			
			if(j - i > maxLen) {
				maxLen = j - i;
				start = i;
			}
		}
		return Arrays.copyOfRange(A, start, start + maxLen);
	}

}
