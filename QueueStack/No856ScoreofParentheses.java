package QueueStack;

import java.util.Stack;

public class No856ScoreofParentheses {

	public static void main(String[] args) {
		No856ScoreofParentheses sol = new No856ScoreofParentheses();
		String t = "()\n"
				+ "(()(()))\n"
				+ "()()";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int ans = sol.scoreOfParentheses(s[i]);
			System.out.println(ans);
		}
	}

    public int scoreOfParentheses(String S) {
    	// --> O(n), where n = S.length()
    	
    	// Stack operation feature
    	// "pop" at least one time, "compute" those pop values, "push" the result back
    	
    	// eg. S = ( ( ) ( ( ) ) )
    	//           see S[1:2], push 1
    	//                 see S[4:5], push 1
    	//                     pop 1, compute 2*1 = 2, push 2
    	//                       pop 2, 1, compute 2*(2+1) = 6, push 6
    	// sum up all element still in stack.
    	
        Stack<Integer> stack =  new Stack<Integer>();
        
        for(char c : S.toCharArray()) {
        	if(c == '(') {
        		stack.push(-1);
        	}else {
        		int cur = 0; // for current computation
        		while(stack.peek() != -1) {
        			cur += stack.pop();
        		}
        		stack.pop();
        		stack.push(cur == 0 ? 1 : 2*cur);
        	}
        }
        
        int ret = 0;
        while(!stack.isEmpty()) ret += stack.pop();
        return ret;
    }
}
