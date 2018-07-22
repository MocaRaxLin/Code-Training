package String;

public class No859BuddyStrings {

	public static void main(String[] args) {
		No859BuddyStrings sol = new No859BuddyStrings();
		
		String t = "ab\n" + 
				"ba\n" + 
				"\n" + 
				"aa\n" + 
				"aaaaaaabc\"\n" + 
				"aaaaaaacb\"\n" + 
				"ab\n" + 
				"ab\n" + 
				"aa\n" + 
				"aa";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			String A = s[i];
			String B = s[i+1];
			boolean ans = sol.buddyStrings(A, B);
			System.out.println(ans);
		}
	}

    public boolean buddyStrings(String A, String B) {
        // --> time = O(n), where n = A.length() = B.length();
        
    	// 1. same length
    	// 2. all the same except at = bs and as = bt
    	//
        // Let
        // A = a0, a1, ..., at, ..., as, ..., an-1
        // B = b0, b1, ..., bt, ..., bs, ..., bn-1
    	//
        // case 1: at == as
    	// 		   Find if there is any char appears at least twice.
        // case 2: at != as
    	//         1st difference at index t
    	//         2nd difference at index s
    	//         check at == bs && as == bt ?
    	//             Yes -> later chars must be the same
    	//             No  -> return false
        
        if(A.length() != B.length()) return false;
        
        // at == as
        if(A.equals(B)){
            int[] alphabet = new int[26];
            for(char c: A.toCharArray()){
                alphabet[c -'a']++;
                if(alphabet[c -'a'] == 2) return true;
            }
            return false;
        }
        // at != as
        else{
           int fdIdx = -1; // firstDiffIdx
            for(int i = 0; i < A.length(); i++){
                if(A.charAt(i) != B.charAt(i)){
                    if(fdIdx == -1){
                        fdIdx = i; // find the 1st diff at i.
                    }else if(fdIdx < A.length()){
                        if(A.charAt(i) == B.charAt(fdIdx) && B.charAt(i) == A.charAt(fdIdx))
                            fdIdx = A.length(); // find the 2nd diff.
                        else return false;
                    }else{
                    	// more than 2 positions have different chars
                        return false;
                    }
                }
            }
            return fdIdx == A.length(); // we found exactly one pair 
        }
        
    }
}
