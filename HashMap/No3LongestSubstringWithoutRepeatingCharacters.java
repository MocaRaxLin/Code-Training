package HashMap;

public class No3LongestSubstringWithoutRepeatingCharacters {

	public static void main(String[] args) {
		No3LongestSubstringWithoutRepeatingCharacters sol = new No3LongestSubstringWithoutRepeatingCharacters();
		String t = "\"abcabcbb\"\n" + 
				"\"bbbbb\"\n" + 
				"\"pwwkew\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.lengthOfLongestSubstring(s[i]);
			System.out.println(ans);
		}
	}

    public int lengthOfLongestSubstring(String s) {
        boolean[] table = new boolean[128];
        int i = 0, j = 0, maxLen = 0;
        while(i < s.length() && j < s.length()){
            while(table[s.charAt(j)]) table[s.charAt(i++)] = false;
            table[s.charAt(j++)] = true;
            maxLen = j-i > maxLen? j-i: maxLen;
        }
        return maxLen;
    }
}
