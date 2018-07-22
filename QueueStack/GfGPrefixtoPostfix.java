package QueueStack;


public class GfGPrefixtoPostfix {

	public static void main(String[] args) {
		GfGPrefixtoPostfix sol = new GfGPrefixtoPostfix();
		String t = "*+AB-CD\n"
				+ "*-A/BC-/AKL";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String prefix = s[i];
			String postfix = sol.prefixToPostfix(prefix);
			System.out.println(postfix);
		}
	}

	public String prefixToPostfix(String prefix) {
		// --> time = O(n)
		
		// Source: https://www.geeksforgeeks.org/prefix-postfix-conversion/
		//
		// Image binary tree structure
		// Operator is root and has 2 operands as child nodes
		// After we had 2 operands, we concatenate them as a new operand for upper-level operators
		// new operand = operandFront + operandBack + operator
		// 
		// Scan prefix backwards
		// If c is a  operand then push it to stack.
		// If c is an operator then pop 2 operands and
		//     concatenate them with c at the tail.
		//     Push it back to the stack.
		//
		// We will have 1 operand at the end. This is our answer.
		
		char[] ca = prefix.toCharArray();
		String[] stack = new String[ca.length];
		int size = 0;
		for(int i = ca.length - 1; i >= 0; i--) {
			char c = ca[i];
			if(c == '+' || c == '-' || c == '*' || c == '/') {
				String operand1 = stack[--size];
				String operand2 = stack[--size];
				// Assume string concatenation takes constant time
				stack[size++] = operand1 + operand2 + c;
			}else{
				stack[size++] = "" + c;
			}
		}
		return stack[0];
	}

	// Follow up:
	// prefix -> infix:
	//     It is similar to prefix to postfix. 
	//     Again scan prefix from end to head (<--), but modify the re-push part
	//     into stack[size++] = "(" + operand1 + c + operand2 +")";
	//
	// postfix -> prefix:
	//     Scan from head to end (-->), push operands.
	//     If we see operator c, pop 2 operands op2 op1.
	//     re-push stack[size++] = c + op1 + op2;
	//     eg. ab+ -> stack = [a, b -> [+ab, 
	//
	// postfix -> infix:
	//     It is similar to the above.
	//     Just change re-push part
	//     eg. ab+ -> stack = [a, b -> [a+b, 
}
