package ArrayMatrix;

public class No467UniqueSubstringsinWraparoundString {

	public static void main(String[] args) {
		No467UniqueSubstringsinWraparoundString sol = new No467UniqueSubstringsinWraparoundString();
		String t = "a\n"
				+ "zabczacdedefg";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.findSubstringInWraproundString(s[i]);
			System.out.println(ans);
		}
	}

    public int findSubstringInWraproundString(String p) {
    	// --> time = O(n), where n = p.length();
    	
    	// ref.
    	// https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/95439/Concise-Java-solution-using-DP
    	
    	// eg. p = "zabczacdedefg"
    	//
    	//     z, A[z] = 1
    	//     za a, A[a] = 2
    	//     zab, ab, b, A[b] = 3
    	//     zabc, abc, bc, c, A[c] = 4
    	//
    	//     z
    	//     za a
    	//     c
    	//     cd, d, A[d] = 2
    	//     cde, de, e, A[e] = 3
    	//
    	//     d
    	//     de, e
    	//     def, ef, f, A[f] = 3
    	//     defg, efg, fg, g,  A[g] = 4
    	//
    	//     Sum up A = 22
    	
    	
    	
        // count[i] is the maximum unique substring end with i-th letter.
        // 0 - 'a', 1 - 'b', ..., 25 - 'z'.
        int[] count = new int[26];
        
        // store longest contiguous substring ends at current position.
        int maxLengthCur = 0; 

        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || (p.charAt(i - 1) - p.charAt(i) == 25))) {
                maxLengthCur++;
            }
            else {
                maxLengthCur = 1;
            }
            
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], maxLengthCur);
        }
        
        // Sum to get result
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }
        return sum;
    }
}
