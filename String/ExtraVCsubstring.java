package String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ExtraVCsubstring {

	public static void main(String[] args) {
		ExtraVCsubstring sol = new ExtraVCsubstring();
		String t = "qfvewufvqerqreqfwadcxcpmvpneg\n"
				+ "aba\n"
				+ "aab\n"
				+ "bac\n"
				+ "op\n"
				+ "abc\n"
				+ "acsz\n"
				+ "i\n"
				+ "\n"
				+ "adakcuvaba";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			sol.findSubstring(s[i]);
		}
	}

	private void findSubstring(String s) {
		// List<String> list = new ArrayList<String>();
		String[] ret = new String[] {"ĉ", ""}; // from highest, lowest
		for(int j = 1; j < s.length()+1; j++) {
			for(int i = 0; i < j; i++) {
				if(isVowel(s.charAt(i)) && !isVowel(s.charAt(j-1))) {
					String sub = s.substring(i, j);
					if(sub.compareTo(ret[0]) < 0) ret[0] = sub;
					if(sub.compareTo(ret[1]) > 0) ret[1] = sub;
					// list.add(s.substring(i, j));
				}
			}
		}
		// Collections.sort(list);
		/*
		Iterator<String> it = list.iterator();
		while(it.hasNext()) System.out.print(it.next()+", ");
		System.out.println();
		*/
		// String first = list.size() > 0? list.get(0): "";
		// String last = list.size() > 1? list.get(list.size()-1): first;
		// System.out.println(first + " - " + last);
		System.out.println((ret[0].equals("ĉ")? "": ret[0]) + " - " + ret[1]);
		
	}
	
	private boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

}
