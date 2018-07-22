package QueueStack;

public class GfGInfixToPostfix {

	public static void main(String[] args) {
		GfGInfixToPostfix sol = new GfGInfixToPostfix();
		String t = "a+b*(c^d-e)^(f+g*h)-i\n"
					+ "a*(b+d*c)\n"
					+ "-a\n"
					+ "\n"
					+ "a+b-c\n"
					+ "a-b^c\n"
					+ "a+b/c-a*c\n"
					+ "(A-B/C)*(A/K-L)";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String infix = s[i];
			String postfix = sol.infixToPrefix(infix);
			//String postfix = sol.infixToPostfix(infix);
			System.out.println(postfix);
		}
		
	}

	public String infixToPostfix(String infix) {
		// --> time = O(n), where n = infix.length();
		
		// Source: https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
		// Algorithm from GfG:
		
		// Shunting Yard Algorithm by Edgar Dijkstra
		//
		// 1. Scan the infix expression from left to right.
		// 2. If the scanned character is an operand, output it.
		// 3. Else,
		//    3.1 If the precedence of the scanned operator > the precedence of the operator
		//        on the top of the stack(or the stack is empty), push it.
		//    3.2 Else, Pop the operator from the stack until the precedence of the scanned
		//        operator < the precedence of the operator residing on the top of the stack.
		//        Push the scanned operator to the stack.
		// 4. If the scanned character is an '(', push it to the stack.
		// 5. If the scanned character is an ')', pop and output from the stack until an '(' is encountered.
		// 6. Repeat steps 2-6 until infix expression is scanned.
		// 7. Pop and output from the stack until it is not empty.
		
		StringBuilder sb = new StringBuilder();
		char[] ca = infix.toCharArray();
		char[] stack = new char[ca.length];
		int size = 0;
		
		for(char c: ca) {
			if(c == '(') { 
				stack[size++] = c;
			}else if(c == ')') {
				while(size > 0 && stack[size-1] != '(')
					sb.append(stack[--size]);
				size--;
			}else if(c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
				while(size > 0 && stack[size-1] != '(' && prec(stack[size-1]) >= prec(c))
					sb.append(stack[--size]);
				stack[size++] = c;
			}else{
				sb.append(c);
			}
		}
		while(size > 0) sb.append(stack[--size]);
		return sb.toString();
	}
	
	public int prec(char c) {
		switch (c) {
		case '^':
			return 3;
		case '*':
		case '/':
			return 2;
		case '+':
		case '-':
			return 1;
		default:
			break;
		}
		return 0;
	}
	
	// Follow up:
	// infix -> prefix:
	// Similarly, but scan from end to head (<--).
	// for each char c:
	// if c is operand then insert c at front of output string (return string).
	// if c is operator (+, -, *, /, ^)
	//     check stack's top, while precedence of the top > precedence of c
	//     then pop it and insert to the front of output.
	//     push c into the stack
	// if c is ')', just push it into stack.
	// if c is '(', pop and insert all operators before preivous ')' at the front of output.
	// while stack's size > 0, then pop and insert front to output.
	
	public String infixToPrefix(String infix) {
		// --> time = O(n), where n = infix.length();
		
		StringBuilder sb = new StringBuilder();
		char[] ca = infix.toCharArray();
		char[] stack = new char[ca.length];
		int size = 0;
		
		for(int i = ca.length-1; i >= 0 ;i--) {
			char c = ca[i];
			if(c == ')') { 
				stack[size++] = c;
			}else if(c == '(') {
				while(size > 0 && stack[size-1] != ')')
					sb.insert(0, stack[--size]);
				size--;
			}else if(c == '^' || c == '*' || c == '/' || c == '+' || c == '-') {
				while(size > 0 && stack[size-1] != ')' && prec(stack[size-1]) > prec(c))
					sb.insert(0, stack[--size]);
				stack[size++] = c;
			}else{
				sb.insert(0, c);
			}
		}
		while(size > 0) sb.insert(0, stack[--size]);
		return sb.toString();
	}
	
	// ps.
	// prefix -> binary tree
	// We can use binary tree to verify prefix, infix, postfix by pre-, in-, post-order traversal.
	// Also this tree is good for examinating computational structure.
	// Let root be an operator and 2 child nodes be computed results.
	// eg. *A-BC    in-oder    -> (A*(B-C))
	//     *        post-order -> ABC-*
	//     | \
	//     A  -
	//        | \
	//        B  C
}
