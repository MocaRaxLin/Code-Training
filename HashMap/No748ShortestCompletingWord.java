package HashMap;

import java.util.Arrays;

import Util.Parser;

public class No748ShortestCompletingWord {

	public static void main(String[] args) {
		No748ShortestCompletingWord sol = new No748ShortestCompletingWord();
		Parser parser = new Parser();
		String t = "\"1s3 PSt\"\n" + 
				"[\"step\",\"steps\",\"stripe\",\"stepple\"]\n" + 
				"\"1s3 456\"\n" + 
				"[\"looks\", \"pest\", \"stew\", \"show\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String licensePlate = s[i];
			String[] words = parser.parseArrayStr(s[i+1]);
			String ans = sol.shortestCompletingWord(licensePlate, words);
			System.out.println(ans);
		}
	}

	public String shortestCompletingWord(String licensePlate, String[] words) {
        // --> O(n+m*w), where n = licensePlate.length(), m = words.length, w = average length of word.
        
        // Intuition:
        // Construct actual map and size of licensePlate
        // for each word test if the word is able to consume all letters in actual licensePlate.
        
        char[] lpCa = new char[26];
        int lpSize = 0;
        licensePlate = licensePlate.toLowerCase();
        for(char c: licensePlate.toCharArray()){
            if(Character.isLetter(c)){
                lpCa[c-'a']++;
                lpSize++;
            }
        }
        
        String ret = "0123456789abcdef"; // length of 16
        for(String w : words){
            char[] ca = w.toCharArray();
            int tmp_lpSize = lpSize;
            char[] tmp_lpCa = Arrays.copyOf(lpCa, lpCa.length);
            for(char c: ca){
                if(tmp_lpCa[c-'a'] > 0){
                    tmp_lpCa[c-'a']--;
                    tmp_lpSize--;
                }
            }
            if(tmp_lpSize == 0 && w.length() < ret.length()) ret = w;
        }
        return ret;
    }
}
