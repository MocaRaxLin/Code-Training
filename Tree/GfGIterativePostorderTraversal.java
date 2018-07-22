package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Util.Show;

public class GfGIterativePostorderTraversal {
	public static void main(String[] args) {
		GfGIterativePostorderTraversal sol = new GfGIterativePostorderTraversal();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		String t = "[1,2,3,4,5,6,7,8,null,null,9,null,null,0,null]\n"
				+ "[1,2,3,4,5,6,7]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			List<Integer> ans = sol.iterativePostorderTraversal(root);
			show.showListInt(ans, true);
		}
		
	}

	public List<Integer> iterativePostorderTraversal(TreeNode root) {
		// --> time O(n)
		
		// Intution:
		// Think about iterative preorder trversal -> it is like DFS
		// 1. push root
		// 2. while stack size > 0:
		//    2.1. ptr = stack.pop();
		//    2.2. print(ptr);
        //	  2.3. stack.push(ptr.right);
		//    2.4. stack.push(ptr.left);
		//
		// The stack stores sibilings of left over right,
		// so we will print root-left-right order.
		
		// How about terative preorder trversal?
		// we want output as left-right-root order
		// we reverse it, and R(output) becomes root-right-left
		//
		// Thus we can switch 2.3 and 2.4 to get R(output)
		// and then reverse it back to original output = R(R(output))
				
		
		List<Integer> list = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(stack.size() > 0) {
			TreeNode ptr = stack.pop();
			list.add(0, ptr.val);
			if(ptr.left != null) stack.push(ptr.left);
			if(ptr.right != null) stack.push(ptr.right);
		}
		return list;
	}
}
