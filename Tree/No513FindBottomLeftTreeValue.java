package Tree;

import java.util.Stack;

public class No513FindBottomLeftTreeValue {

	public static void main(String[] args) {
		No513FindBottomLeftTreeValue sol = new No513FindBottomLeftTreeValue();
		TreeNode tn = new TreeNode();
		String t = "[1,null,2,2]\n" + 
				"[5,1,7,1,1,7,7]\n" + 
				"[2,1,null,null,2]\n" + 
				"[5,2,5,2,2,5,8,null,null,null,null,null,null,8,8]\n" +
				"[2,1,3]\n" + 
				"[1,2,3,4,null,5,6,null,null,7]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int ans = sol.findBottomLeftValue(root);
			System.out.println(ans);
		}
	}

	public int findBottomLeftValue(TreeNode root) {
        // --> time = O(n)
        
        // do left-first DFS and
        // store the first element we meet at a new level
        // It is similar to level order traversal
        
        Stack<Integer> stack = new Stack<Integer>();
        DFS(root, 0, stack);
        return stack.peek();
    }
    
    public void DFS(TreeNode root, int level, Stack<Integer> stack){
        if(root == null) return;
        if(level == stack.size()) stack.push(root.val);
        DFS(root.left, level+1, stack);
        DFS(root.right, level+1, stack);
    }
}
