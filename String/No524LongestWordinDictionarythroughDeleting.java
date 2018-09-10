package String;

import java.util.Iterator;
import java.util.List;

import Util.Parser;

public class No524LongestWordinDictionarythroughDeleting {

	public static void main(String[] args) {
		No524LongestWordinDictionarythroughDeleting sol = new No524LongestWordinDictionarythroughDeleting();
		Parser parser = new Parser();
		String t = "\"abpcplea\"\n" + 
				"[\"ale\",\"apple\",\"monkey\",\"plea\"]\n" + 
				"\"abpcplea\"\n" + 
				"[\"a\",\"b\",\"c\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			List<String> d = parser.parseListStr(s[i+1], true);
			String ans = sol.findLongestWord(s[i], d);
			System.out.println(ans);
		}
	}

	public String findLongestWord(String s, List<String> d) {
		// --> O(n), where n = d.size()
		
		// Intuition:
		// Just check one by one.
		// Recall lexicographical order with String.compareTo(String)
        String ret = "";
        char[] ca = s.toCharArray();
        Iterator<String> it = d.iterator();
        while(it.hasNext()){
        	String w = it.next();
            if(ret.length() < w.length() || (ret.length() == w.length() && w.compareTo(ret) < 0)){
                if(formByDelete(ca, w.toCharArray())) ret = w;
            }
        }
        return ret;
    }
    
    private boolean formByDelete(char[] a, char[] b){
        int idx = 0;
        for(int i = 0; i < a.length; i++){
            if(idx == b.length) return true;
            if(a[i] == b[idx]) idx++;
        }
        return idx == b.length;
    }
}
