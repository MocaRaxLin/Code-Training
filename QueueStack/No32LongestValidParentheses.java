package QueueStack;

public class No32LongestValidParentheses {

	public static void main(String[] args) {
		No32LongestValidParentheses sol = new No32LongestValidParentheses();
		String t = "\"(()\"\n" + 
				"\")()())\"\n" + 
				"\"()(()))))\"\n" + 
				"\"((()\"\n" + 
				"\"\"\n" + 
				"\"(\"\n" + 
				"\")\"\n" + 
				"\"()\"\n" + 
				"\"((()(()()()\"";
		t = t.replaceAll("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.longestValidParentheses(s[i]);
			System.out.println(ans);
		}
	}

	public int longestValidParentheses(String s) {
		// --> time = O(n), where n = s.length();
		
		// Intuition:
		// eg. ( ( () ( (()())
		//          2        6 -> return 6
		// compute index difference, so stack have to store indices
		// 2 = 3 - 1, 6 = 10 - 4
		// 
		// eg. ( ( () ( ()()() -> len = i - baseIndex
		// ret = MAX(ret, i - baseIndx);
		//
		// The first base is index at -1,
		// so given ()() the max length is 3 - (-1) = 4
		//
		// eg. ())(()) -> we need to build another base index at 2
		// [-1, 0,
		// i = 1 -> see ')' and s[0] == '(' -> pop 0, maxLen = MAX(0, 1 - (-1)) = 2
		// [-1, 
		// i = 2 -> see ')' but top = -1 we hit base, make new base by push 2
		// [-1, 2
		// i = 3, 4 -> [-1, 2, 3, 4, 
		// i = 5 -> see ')' and s[4] == '(' -> pop 4, maxLen = MAX(2, 5 - 3) = 2
		// [-1, 2, 3, ]
		// i = 6 -> see ')' and s[3] == '(' -> pop 3, maxLen = MAX(2, 6 - 2) = 4
		// return maxLen = 4
		
		
        int ret = 0;
        int[] stack = new int[s.length()+1];
        int size = 0;
        stack[size++] = -1;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack[size++] = i;
            }else if(size > 1  && s.charAt(stack[size-1]) == '('){
                size--;
                int baseIdx = stack[size-1];
                ret = Math.max(ret, i - baseIdx);
            }else{ // size = 1 and s[i] == ')' => make new base
                stack[size++] = i;
            }
        }
        return ret;
    }
}
