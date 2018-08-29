package HashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Util.Show;

public class ExtraSubStringsOfLenKWithDistinctChar {

	public static void main(String[] args) {
		ExtraSubStringsOfLenKWithDistinctChar sol = new ExtraSubStringsOfLenKWithDistinctChar();
		Show show = new Show();
		String t = "barfoothefoobarman\n"
				+ "4\n"
				+ "ababcdabcd\n"
				+ "4\n"
				+ "\n"
				+ "0\n"
				+ "abcabcabc\n"
				+ "3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int k = Integer.parseInt(s[i+1]);
			Set<String> ans = sol.substringOfLenKWithDistinctChar(s[i], k);
			show.showSetStr(ans);
		}
	}

	private Set<String> substringOfLenKWithDistinctChar(String s, int k) {
		Set<String> ret = new HashSet<String>();
		if(s.length() == 0) return ret;
		
		Set<Character> set = new HashSet<Character>();
		char[] ca = s.toCharArray();
		
		StringBuilder sb = new StringBuilder();
		int i = 0, j = 0;
		while(j < ca.length) {
			while(sb.length() == k || set.contains(ca[j]) ) {
				sb.deleteCharAt(0);
				set.remove(ca[i++]);
			}
			sb.append(ca[j]);
			set.add(ca[j++]);
			if(sb.length() == k) ret.add(sb.toString());
		}
		return ret;
	}

}
