package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import Util.Parser;

public class ExtraHackLandElection {

	public static void main(String[] args) {
		ExtraHackLandElection sol = new ExtraHackLandElection();
		Parser parser = new Parser();
		String t = "[\"Alex\", \"Michael\", \"Harry\", \"Dave\", \"Michael\", \"Victor\", \"Harry\", \"Alex\", \"Mary\", \"Mary\"]\n"
				+ "[ \"victor\", \"veronica\", \"ryan\", \"dave\", \"maria\", \"farah\", \"farah\", \"ryan\", \"veronica\"]\n"
				+ "[Joe,Joe,Mary,Mary]\n"
				+ "[d,a,c,b,d,c,d,b,c,d]\n"
				+ "[JJ,JJ,Ab,Ad,Ja,Ja]\n"
				+ "[f,br]\n"
				+ "[g,g]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[] votes = parser.parseArrayStr(s[i]);
			System.out.println(Arrays.toString(votes));
			
			String ans = sol.heighestVote(votes);
			System.out.println(ans);
		}
	}

	private String heighestVote(String[] votes) {
		// --> O(n), where n = votes.length
		
		// Intuition:
		// Count freqency of each different name
		// Pop up the name of highest freq name and of the last one in alphabetical order
		
		if(votes.length == 0) return "";
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String name: votes) {
			int freq = 0;
			if(map.containsKey(name)) freq = map.get(name);
			map.put(name, ++freq);
		}
		
		String ret = "";
		int heighestFreq = 0;
		for(Entry<String, Integer> e: map.entrySet()) {
			if(heighestFreq < e.getValue()) {
				ret = e.getKey();
				heighestFreq = e.getValue();
			}else if(heighestFreq == e.getValue() && ret.compareTo(e.getKey()) < 0) {
				// s1.compareTo(s2) < 0 means they are in s1-s2 order
				ret = e.getKey();
				heighestFreq = e.getValue();
			}
		}
		return ret;
	}
	
}
