package QueueStack;

import java.util.Stack;

public class No331VerifyPreorderSerializationofaBinaryTree {

	public static void main(String[] args) {
		No331VerifyPreorderSerializationofaBinaryTree sol = new No331VerifyPreorderSerializationofaBinaryTree();
		String t = "9,3,4,#,#,1,#,#,2,#,6,#,#\n" + 
				"\n" + 
				"1,#\n" + 
				"9,#,#,1\n" + 
				"1,#,#,#,#";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			boolean ans = sol.isValidSerialization(s[i]);
			System.out.println(ans);
		}
	}

	public boolean isValidSerialization(String preorder) {
        // --> time = O(n), where n = preoder.length().
        
        // 150 / 150 test cases passed.
        // Runtime: 5 ms
    
        // Idea source:
        // https://www.youtube.com/watch?v=_mbnPPHJmTQ
        
        // null node uses 1 out degree from tree
        // non-null node uses 1 out degree from tree and gives 2 out degree for tree
        // if number of out degree < 0 then the tree cannot be tree.
        // Is a valid serialization if outInDegDiff is 0 in the end.
        
        String[] sa = preorder.split(",");
        int outInDegDiff = 1;
        for(int i = 0; i < sa.length; i++){
            if(--outInDegDiff < 0) return false;
            if(!sa[i].equals("#")) outInDegDiff+=2;
        }
        return outInDegDiff == 0;
    }
	
	public boolean isValidSerialization0(String preorder) {
        // --> time = O(n), where n = preoder.length().
        
		// 150 / 150 test cases passed.
		// Runtime: 10 ms
		
        // Stack update by [ ..., "no", "#", "#"] -> [ ..., "#"]
        // Eg.
        // "9,3,4,#,#,1,#,#,2,#,6,#,#"
        // [9,3,4,#]
        // see #, then we know subtree of value 4 is done
        // replace 4,#,# by #
        // [9,3,#]
        // [9,3,#,1,#]
        // see # -> 1,#,# by #
        // [9,3,#,#]
        // still -> 3,#,# by #
        // [9,#,2,#,6,#]
        // see # -> 6,#,# by #
        // still -> 2,#,# by #
        // still -> 9,#,# by #
        // [#] ... end
        
        // ps.
        // if we know the binary tree is end and still need to push #
        // then return false
        // if a tree is end and needs to push another number
        // then this is another tree.
        
        String[] sa = preorder.split(",");
        Stack<String> stack = new Stack<String>();
        for(String s: sa){
            if(s.equals("#")){
                while(stack.size() > 1 && stack.peek().equals("#")){
                    stack.pop();
                    stack.pop();
                }
                if(stack.size() == 1 && stack.peek().equals("#")) return false;
                stack.push("#");
            }else{
                stack.push(s);
            }
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }
}
