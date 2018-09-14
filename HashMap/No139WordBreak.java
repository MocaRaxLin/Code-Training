package HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Util.Parser;

public class No139WordBreak {

	public static void main(String[] args) {
		No139WordBreak sol = new No139WordBreak();
		Parser parser = new Parser();
		String t = "\"leetcode\"\n" + 
				"[\"leet\",\"code\"]\n" + 
				"\"applepenapple\"\n" + 
				"[\"apple\", \"pen\"]\n" + 
				"\"catsandog\"\n" + 
				"[\"cats\", \"dog\", \"sand\", \"and\", \"cat\"]\n" + 
				"\"catsandog\"\n" + 
				"[\"cats\", \"dog\", \"sand\", \"and\", \"cat\",\"san\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String S = s[i];
			List<String> wordDict = parser.parseListStr(s[i+1], false);
			boolean ans = sol.wordBreak(S, wordDict);
			System.out.println(ans);
		}
	}
	
	public boolean wordBreak(String s, List<String> wordDict) {
        // --> O(NMN), M = wordDict.size(), N = s.length();
        
        // Intuition:
        // Forward extending
        // It is faster than backward collection in most cases.
        
        
        if(wordDict.size() == 0) return false;
        
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 0; i < s.length(); i++){
            if(!dp[i]) continue;
            for(String word: wordDict){
                int len = word.length();
                if(i+len <= s.length() && word.equals(s.substring(i, i+len)))   // string comparison linear time.
                    dp[i+len] = true;
            }
        }
        return dp[s.length()];
    }

	public boolean wordBreakBack(String s, List<String> wordDict) {
        // --> O(M + N^2), M = wordDict.size(), N = s.length();
		
		// Intuition:
		// Backward collection.
		// N^2 solution to check s[i:j-1] can be extended or not.
		// dp[i] check if s[0:i-1] is an end of some combinations?
		// Yes then extend to dp[j] = true by including word s[i:j-1]
        
        if(wordDict.size() == 0) return false;
        Set<String> set = new HashSet<String>();
        Iterator<String> it = wordDict.iterator();
        while(it.hasNext()) set.add(it.next());
        
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int j = 0; j <= s.length(); j++){
            for(int i = 0; i < j; i++){
                if(dp[i] && set.contains(s.substring(i, j))){ // substring extraction takes linear time.
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
