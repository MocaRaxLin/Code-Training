package String;

import java.util.Stack;

public class No227BasicCalculatorII {

	public static void main(String[] args) {
		No227BasicCalculatorII sol = new No227BasicCalculatorII();
		
		String[] input = new String[] {
				"3+2*2",
				" 3/2 ",
				" 3+5 / 2 ",
				"3*3+44*2/1/2+4-8*3/1",
				""
		};
		for(String s: input) {
			int ans = sol.calculate(s);
			System.out.println(ans);
		}
	}
	
	public int calculate(String s) {
		// --> time O(n), where n is s.length()
		// --> space O(1), we use ret + top rather than the whole stack.
		// To avoid stack operations
		// 22ms -> 90.19%
		
        int ret = 0;
        int num = 0;
        int top = 0;
        char signLeading = '+'; // last sign we want
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)) num = 10*num + c - '0';
            if(!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
                if(signLeading == '+'){
                    ret += top;
                    top = num;
                }else if(signLeading == '-'){
                    ret += top;
                    top = -num;
                }else if(signLeading == '*') top*=num;
                else if(signLeading == '/') top/=num;
                signLeading = c;
                num = 0;
            }
            // skip ' '
        }
        return ret + top;
    }
	
	public int calculate0(String s) {
		// --> time O(n), where n is s.length()
		// --> space O(n), because we use stack, bottle neck is push or pop operations
		// 38ms -> 52.15%
		//
		// We store the leading sign into variable signLeading whose default value is plus '+'.
		// Whenever we meet the next sign, we manipulate the collected number according to signLeading,
		// After that store this sign into signLeading, and continue looping.
		// The operation priority asks us to do multiplication '*' and division '/'.
		
		// NOTE:
		// Think of Stack easy pop, calculate, push for '*' and '/'
		// Do '+' and '-' in the end.
		
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char signLeading = '+'; // last sign we want
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = 10*num + c - '0';
            }
            if(!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
                if(signLeading == '+') stack.push(num);
                else if(signLeading == '-') stack.push(-num);
                else if(signLeading == '*') stack.push(stack.pop()*num);
                else if(signLeading == '/') stack.push(stack.pop()/num);
                signLeading = c;
                num = 0;
            }
            // skip ' '
        }
        
        int ret = 0;
        while(stack.size() > 0){
            ret+=stack.pop();
        }
        return ret;
    }
}
