package String;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import Util.Parser;

public class No792NumberofMatchingSubsequences {

	public static void main(String[] args) {
		No792NumberofMatchingSubsequences sol = new No792NumberofMatchingSubsequences();
		Parser parser = new Parser();
		String t = "\"abcde\"\n" + 
				"[\"a\",\"bb\",\"acd\",\"ace\"]\n" + 
				"\"aaaaaaaa\"\n" + 
				"[\"a\",\"aa\",\"aaa\",\"aaaa\"]\n" + 
				"\"aabbcccddd\"\n" + 
				"[\"ab\",\"abc\",\"cd\"]\n" + 
				"\"dsahjpjauf\"\n" + 
				"[\"ahjpjau\",\"ja\",\"ahbwzgqnuk\",\"tnmlanowax\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String S = s[i];
			String[] words = parser.parseArrayStr(s[i+1]);
			int ans = sol.numMatchingSubseq(S, words);
			System.out.println(ans);
		}
	}
	
	public int numMatchingSubseq(String S, String[] words) {
        // --> O(M), where N = S.length(), M is the number of letters in words
        
		// Intuition:
		// Collect lists of words waiting for head char elimination.
		// Store the lists with key of common head char
		
        Map<Character, Deque<StringBuilder>> map = new HashMap<Character, Deque<StringBuilder>>();
        for(String w: words){
            Deque<StringBuilder> q = map.getOrDefault(w.charAt(0), new LinkedList<StringBuilder>());
            q.add(new StringBuilder(w));
            if(q.size() == 1) map.put(w.charAt(0), q);
        }
        
        int count = 0;
        for(char c: S.toCharArray()){
            if(!map.containsKey(c) || map.get(c).size() == 0) continue;
            Deque<StringBuilder> deq = map.get(c);
            int tmpSize = deq.size();
            while(tmpSize-- > 0){
                StringBuilder sb = deq.pollFirst();
                sb.deleteCharAt(0);
                if(sb.length() == 0) count++;
                else{
                    Deque<StringBuilder> nextDeq = map.getOrDefault(sb.charAt(0), new LinkedList<StringBuilder>());
                    nextDeq.offerLast(sb);
                    if(nextDeq.size() == 1) map.put(sb.charAt(0), nextDeq);
                }
            }
        }
        return count;
    }
	

	public int numMatchingSubseqBS(String S, String[] words) {
		// --> O(MlogN), where N = S.length(), M is the number of letter in words array.
		// Still Slow
		
		// Intuition:
		// Collect indices of lists of 'a' to 'z'.
		// For each word in words, we do binary search for each char c to find its possible index.
		
        Map<Character, List<Integer>> map = new HashMap<Character, List<Integer>>();
        char[] ca = S.toCharArray();
        for(int i = 0; i < ca.length; i++){
            List<Integer> list = map.getOrDefault(ca[i], new ArrayList<Integer>());
            list.add(i);
            if(list.size() == 1) map.put(ca[i], list);
        }
        
        /*
        for(Map.Entry<Character, List<Integer>> pair: map.entrySet()){
            System.out.println(pair.getKey()+": "+Arrays.toString(pair.getValue().toArray()));
        }
        */
        
        int count = 0;
        for(String w: words){
            char[] wa = w.toCharArray();
            int idx = -1;
            int i = 0;
            for(; i < wa.length; i++){
                if(!map.containsKey(wa[i])) break;
                List<Integer> list = map.get(wa[i]);
                int t = binarySearch(list, idx+1);
                if(t == -1) break;
                idx = t;
            }
            if(i == wa.length){
                count++;
                // System.out.println(w);
            }
        }
        return count;
    }
    
    private int binarySearch(List<Integer> list, int t){
        int i = 0, j = list.size();
        while(i < j){
            int m = i + (j-i)/2;
            if(list.get(m) < t) i = m + 1;
            else j = m;
        }
        return i == list.size()? -1: list.get(i);
    }
}
