package QueueStack;

public class No844BackspaceStringCompare {

	public static void main(String[] args) {
		No844BackspaceStringCompare sol = new No844BackspaceStringCompare();
		String t = "\"ab#c\"\n" + 
				"\"ad#c\"\n" + 
				"\"ab##\"\n" + 
				"\"c#d#\"\n" + 
				"\"a##c\"\n" + 
				"\"#a#c\"\n" + 
				"\"a#c\"\n" + 
				"\"b\"\n" + 
				"\"y#fo##f\"\n" + 
				"\"y#f#o##f\"";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			boolean ans = sol.backspaceCompare(s[i], s[i+1]);
			System.out.println(ans);
		}
	}
	
	public boolean backspaceCompare0(String S, String T) {
		// --> time = O(n+m), space = O(n+m)
		
        char[] sCa = S.toCharArray();
        char[] stack1 = new char[sCa.length];
        int size1 = 0;
        for(char c: sCa){
            if(c != '#') stack1[size1++] = c;
            else if(size1 > 0) size1--;
        }
        //System.out.print(Arrays.toString(Arrays.copyOfRange(stack1, 0, size1)) + ",");
        
        char[] tCa = T.toCharArray();
        char[] stack2 = new char[tCa.length];
        int size2 = 0;
        for(char c: tCa){
            if(c != '#') stack2[size2++] = c;
            else if(size2 > 0) size2--;
        }
        //System.out.print(Arrays.toString(Arrays.copyOfRange(stack2, 0, size2)) + "\n");
        
        if(size1 != size2) return false;
        for(int i = 0; i < size1; i++){
            if(stack1[i] != stack2[i]) return false;
        }
        return true;
    }
	
	// Follow up:
	// - Can you solve it in O(N) time and O(1) space?
	//
	// Ref: https://leetcode.com/problems/backspace-string-compare/discuss/135603/C++JavaPython-4-lines-O(N)-time-and-O(1)-space
	// Ans:
	// I believe you have one difficulty here: When we meet a char, we are not sure if it will be still there or be deleted.
	// However, we can do a back string compare (just like the title of problem).
	// If we do it backward, we meet a char and we can be sure this char won't be deleted.
	// If we meet a '#', it tell us we need to skip next lowercase char.
	// eg. S = a##bc#c -> backward-looking: c(#c)b(##a) -> final string: bc
	// eg. S = y#f#o##f -> backward-looking: f(##o#f#y) -> final string: f
	//
	// Think about the code.

	public boolean backspaceCompare(String S, String T) {
		// Follow up
        for(int i = S.length() - 1, j = T.length() - 1; ; i--, j--){
            for(int hash = 0; i >= 0 && (hash > 0 || S.charAt(i) == '#'); i--)
                hash += S.charAt(i) == '#' ? 1 : -1;
            for(int hash = 0; j >= 0 && (hash > 0 || T.charAt(j) == '#'); j--)
                hash += T.charAt(j) == '#' ? 1 : -1;
            
            // if i or j is -1 i.e. either of strings comes to end,
            // then check if both come to end
            //
            // if both not comes to end,
            //     s[i] == T[j] -> continue
            //     s[i] != T[j] -> false
            // And of course i and j are not -1
            //                              
            if(i < 0 || j < 0 || S.charAt(i) != T.charAt(j)) return i == -1 && j == -1;
        }
    }
}
