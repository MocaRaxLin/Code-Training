package HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Util.Parser;
import Util.Show;

public class No890FindandReplacePattern {

	public static void main(String[] args) {
		No890FindandReplacePattern sol = new No890FindandReplacePattern();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[\"abc\",\"deq\",\"mee\",\"aqq\",\"dkd\",\"ccc\"]\n" + 
				"\"abb\"\n" + 
				"[\"AAb\",\"$$%\"]\n" + 
				"\"aac\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String[] words = parser.parseArrayStr(s[i]);
			String pattern = s[i+1];
			List<String> ans = sol.findAndReplacePattern(words, pattern);
			show.showList(ans, true);
		}
	}

	public List<String> findAndReplacePattern(String[] words, String pattern) {
        // --> O(nm), where n = words.length, m = pattern.length()
        
        // check each word one by one.
        //
        // for each word w:
        // 1. is same length?
        // 2. for each char pa[i]:
        //    pa[i] has mapping?
        //      yes: map[pa[i]] == wa[i]? safe: return false
        //       no: wa[i] is used? return false: record map and seen set
        // 3. iterate safely -> return true
        
        List<String> ret = new LinkedList<String>();
        for(String w: words){
            if(isMatch(w, pattern)) ret.add(w);
        }
        return ret;
    }
    
    private boolean isMatch(String w, String p){
        if(w.length() != p.length()) return false;
        Set<Character> seen = new HashSet<Character>();
        Map<Character, Character> map = new HashMap<Character, Character>();
        char[] wa = w.toCharArray();
        char[] pa = p.toCharArray();
        for(int i = 0; i < pa.length; i++){
            if(map.containsKey(pa[i])){
                if(map.get(pa[i]) != wa[i]) return false;
            }else{
                if(seen.contains(wa[i])) return false;
                else{
                    map.put(pa[i], wa[i]);
                    seen.add(wa[i]);
                }
            }
        }
        return true;
    }
}
