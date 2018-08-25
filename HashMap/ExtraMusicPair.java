package HashMap;

import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class ExtraMusicPair {

	public static void main(String[] args) {
		ExtraMusicPair sol = new ExtraMusicPair();
		Parser parser = new Parser();
		String t = "[123,3,57,57,0,0,120,60,60]\n" + 
				"[0,0]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] secs = parser.parseArray(s[i]);
			int ans = sol.pairOfSong(secs);
			System.out.println(ans);
		}
	}

	private int pairOfSong(int[] secs) {
		if(secs.length == 0) return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for(int sec: secs) {
			int secIn60 = sec%60;
			int key = (60 - secIn60)%60;
			
			if(map.containsKey(key)) {
				count++;
				int freq = map.get(key) - 1;
				if(freq == 0) map.remove(key);
				else map.put(key, freq);
			}else{
				int freq = 0;
				if(map.containsKey(secIn60)) freq = map.get(secIn60);
				map.put(secIn60, ++freq);
			}
		}
		return count;
	}

}
