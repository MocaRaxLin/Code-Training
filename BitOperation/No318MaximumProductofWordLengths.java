package BitOperation;

import Util.Parser;

public class No318MaximumProductofWordLengths {

	public static void main(String[] args) {
		No318MaximumProductofWordLengths sol = new No318MaximumProductofWordLengths();
		Parser parser = new Parser();
		String t = "[\"abcw\",\"baz\",\"foo\",\"bar\",\"xtfn\",\"abcdef\"]\n" + 
				"[\"a\",\"ab\",\"abc\",\"d\",\"cd\",\"bcd\",\"abcd\"]\n" + 
				"[\"a\",\"aa\",\"aaa\",\"aaaa\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[] words = parser.parseArrayStr(s[i]);
			int ans = sol.maxProduct(words);
			System.out.println(ans);
		}
	}

	public int maxProduct(String[] words) {
		// --> O(n^2), where n = words.length
		
		// Use bit and & operation to see if words[i] and words[j] has common letter
		// use bits as set to store letter
		
        if(words.length == 0) return 0;
        int[] set = new int[words.length];
        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words[i].length(); j++){
                set[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        
        int ret = 0;
        for(int i = 0; i < words.length; i++){
            for(int j = i+1; j <words.length; j++){
                if((set[i] & set[j]) == 0){
                    int p = words[i].length() * words[j].length();
                    ret = p > ret? p : ret;
                }
            }
        }
        return ret;
    }
}
