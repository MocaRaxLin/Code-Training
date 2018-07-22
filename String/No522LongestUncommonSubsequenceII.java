package String;

public class No522LongestUncommonSubsequenceII {

	public static void main(String[] args) {
		No522LongestUncommonSubsequenceII sol = new No522LongestUncommonSubsequenceII();
		
		String[][] input = new String[][] {
			{"aba","cdc","eae"},
			{"a","b", "aba", "aba","a"},
			{"aba","aba","a","fe"}
		};
		for(String[] strs: input) {
			int ans = sol.findLUSlength(strs);
			System.out.println(ans);
		}
	}
	
	public int findLUSlength(String[] strs) {
		// --> O(k*n^2), where n = strs.length,
		// k = the longest length of given strings. Here k = 10, because of given constraint of inputs.
		
		// Longest Uncommon Subsequence (LUS)
		
		// eg. "abc" "a" -> "abc" -> 3
		// The problem looks like a joke if given 2 strings,
		// because we only need to select the longer one as our answer
		// if their lengths are the same -> test if they are the same string?
		// if yes return -1, but if not return the length.
		
		// Here comes an extended problem.
		// Given an array of strings, find the length of the LUS
		// eg. ["aba", "a", "aba", "b", "a"]
		// we could compare strings 2 by 2, but how?
		//
		// Again, we want to find the longest string
		// which is not a subsequence of any other strings.
		// 
		// With this hint, we compare string i and j in O(n^2) time,
		// if string i is subsequence of string j,
		// then string i is not a possible answer.
		// 
		// ps.
		// A => string i is subsequence of string j.
		// B => string j is subsequence of string i.
		// A and B are not equivalent, we have to check both directions.
		
        int ret = -1;
        for(int i = 0; i < strs.length; i++){
            int j = 0;
            for(;j < strs.length; j++){
                if(i == j) continue;
                if(isSubsequenceOf(strs[i], strs[j])) break;
            }
            // if all other strings are checked
            // strs[i] is not a subsequence of any other string
            if(j == strs.length) ret = Math.max(ret, strs[i].length());
        }
        return ret;
    }
    
    public boolean isSubsequenceOf(String a, String b){
        // if string a is subsequence of string b ?
        int i = 0;
        for(int j = 0; i < a.length() && j < b.length(); j++)
            if(a.charAt(i) == b.charAt(j)) i++;
        return i == a.length();
    }
}
