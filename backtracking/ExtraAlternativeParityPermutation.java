package backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class ExtraAlternativeParityPermutation {

	public static void main(String[] args) {
		ExtraAlternativeParityPermutation sol = new ExtraAlternativeParityPermutation();
		Show show = new Show();
		String t = "1\n" + 
				"2\n" + 
				"3\n" + 
				"4\n" + 
				"5\n" + 
				"6\n" + 
				"7\n" + 
				"8";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int n = Integer.parseInt(s[i]);
			List<int[]> ans = sol.alternativeParityPermutation(n);
			show.showListIntArray(ans);
		}
	}

	private List<int[]> alternativeParityPermutation(int n) {
		int[] A = new int[n];
		List<int[]> ret = new LinkedList<int[]>();
		int[] permutaion = new int[n];
		boolean[] used = new boolean[n+1];
		int d = n%2 == 0? 1: 2;
		for(int i = 1; i <= n; i+=d) {
			permutaion[0] = i;
			used[i] = true;
			boolean nextOdd = i%2 == 0;
			backtracking(permutaion, 1, nextOdd, n, used, ret);
			used[i] = false;
		}
		
		return ret;
	}

	private void backtracking(int[] permutaion, int idx, boolean isOdd, int n, boolean[] used, List<int[]> ret) {
		if(idx == n) {
			int[] ans = Arrays.copyOf(permutaion, n);
			ret.add(ans);
			return;
		}
		int start = isOdd? 1: 2;
		for(int i = start; i <= n; i+=2) {
			if(used[i]) continue;
			permutaion[idx] = i;
			used[i] = true;
			backtracking(permutaion, idx+1, !isOdd, n, used, ret);
			used[i] = false;
		}
		
	}

}
