package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import Util.Parser;
import Util.Show;

public class ExtraHighestFreqencyWords {

	public static void main(String[] args) {
		ExtraHighestFreqencyWords sol = new ExtraHighestFreqencyWords();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "I am Jack and my father is Jimmy. I like wearing Jack and Jone's Jone.\n" + 
				"[]\n" + 
				"JoJo's JoJo have big BIG apple piE which is ranked S!\n" + 
				"[jOjO]\n" + 
				"a aa aaa AAA aAa TaaT TaT TAT aTa tt TtT\n" + 
				"[AAA]\n" +
				"    a.!b'ss, abyss?\n" +
				"[]\n" +
				"Jack and Jill went to the market to buy bread and cheese. Cheese is Jack's and Jill's favorite food.\n" +
				"[and,he,the,to,is,Jack,Jill]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String line = s[i];
			String[] excludeList = parser.parseArrayStr(s[i+1]);
			List<String> ans = sol.highestFreqWords(line, excludeList);
			show.showList(ans, true);
		}
	}

	private List<String> highestFreqWords(String line, String[] excludeList) {
		Set<String> excludedSet = new HashSet<String>();
		for(String s: excludeList) {
			s = s.toLowerCase();
			excludedSet.add(s);
		}
		
		String[] words = line.split("[\\s|.|!|?|'|,]+");
		//System.out.println(Arrays.toString(words));
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String s: words) {
			s = s.toLowerCase();
			if(s.length() == 0 || excludedSet.contains(s)) continue;
			int freq = 0;
			if(map.containsKey(s)) freq = map.get(s);
			map.put(s, ++freq);
		}
		
		List<String> ret = new LinkedList<String>();
		int highest = 0;
		for(Entry<String, Integer> e: map.entrySet()) {
			if(e.getValue() > highest) {
				ret = new LinkedList<String>();
				ret.add(e.getKey());
				highest = e.getValue();
			}else if(e.getValue() == highest) {
				ret.add(e.getKey());
			}
		}
		return ret;
	}

}
