package backtracking;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No17LetterCombinationsofaPhoneNumber {

	public static void main(String[] args) {
		No17LetterCombinationsofaPhoneNumber sol = new No17LetterCombinationsofaPhoneNumber();
		Show show = new Show();
		String t = "\"23\"\n" + 
				"\"2948\"\n" + 
				"\"7\"\n" + 
				"\"\"";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			List<String> ans = sol.letterCombinations(s[i]);
			show.showList(ans, true);
		}
	}
	
	public List<String> letterCombinations(String digits) {
		// --> O(3^n), n = digits.length()
		
		// Naive backtracking
		
        char[][] map = new char[][]{{}, {}, {'a','b','c'}, {'d','e','f'}, {'g','h','i'}, {'j','k','l'}
                                   , {'m','n','o'}, {'p','q','r','s'}, {'t','u','v'}, {'w','x','y','z'}};
        
        char[] ca = digits.toCharArray();
        List<String> ret = new LinkedList<String>();
        StringBuilder sb = new StringBuilder();
        backtracking(ca, 0, sb, map, ret);
        return ret;
    }
    
    private void backtracking(char[] ca, int idx, StringBuilder sb, char[][] map, List<String> list){
        if(idx == ca.length){
            if(sb.length() > 0) list.add(sb.toString());
            return;
        }
        for(int i = 0; i < map[ca[idx]-'0'].length; i++){
            sb.append(map[ca[idx]-'0'][i]);
            backtracking(ca, idx+1, sb, map, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
