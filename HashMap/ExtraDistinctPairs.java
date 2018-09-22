package HashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Util.Parser;
import Util.Show;

public class ExtraDistinctPairs {

	public static void main(String[] args) {
		ExtraDistinctPairs sol = new ExtraDistinctPairs();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[1,2,3,6,7,8,9,1]\n" + 
				"10\n" + 
				"[-1,0,1,2,-2,2,-1]\n" + 
				"0\n" + 
				"[0,0]\n" + 
				"0";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int target = Integer.parseInt(s[i+1]);
			int[][] ans = sol.getPairs(A, target);
			show.showMatrix(ans);
		}
	}

	private int[][] getPairs(int[] A, int target) {
		int n = A.length; if(n < 2) return new int[0][0];
		
		int[][] ret = new int[n][];
		int size = 0;
		Set<Integer> set = new HashSet<Integer>();
		Set<Long> seenCode = new HashSet<Long>();
		for(int i = 0; i < n; i++) {
			int key = target - A[i];
			if(set.contains(key)) {
				int low = Math.min(key, A[i]);
				int high = Math.max(key, A[i]);
				long code = ((long) low << 32) + high;
				if(!seenCode.contains(code)) {
					ret[size++] = new int[] {low, high};
					seenCode.add(code);
				}
			}
			set.add(A[i]);
		}
		
		return Arrays.copyOfRange(ret, 0, size);
	}

}
