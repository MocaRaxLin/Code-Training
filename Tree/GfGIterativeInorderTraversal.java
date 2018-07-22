package Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import Util.Show;

public class GfGIterativeInorderTraversal {

	public static void main(String[] args) {
		GfGIterativeInorderTraversal sol = new GfGIterativeInorderTraversal();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		String t = "[1,2,3,4,5,6,7,8,null,null,9,null,null,0,null]\n"
				+ "[1,2,3,4,5,6,7]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			List<Integer> ans = sol.iterativeInorderTraversal(root);
			show.showListInt(ans, true);
		}
	}

	public List<Integer> iterativeInorderTraversal(TreeNode root) {
		// --> time O(n)
		
		// Important:
		// This function can be used to make an iterator of BST
		// - hasNext(): has next element?
		//              -> return stack.size() > 0; (constant time)
		// - next()   : return the current element, move pointer to the next
		//              -> TreeNode ret = stack.pop();
		//              -> ptr = ret.right;
		//              -> ptr = pushToMin(ptr); (it takes amortized constant time)
		//              -> return ret;
		
		// output: left, ptr = stack.pop: root, stack: right
		
		// Algorithm:
		// source: https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
		// 1) Create an empty stack S.
		// 2) Initialize current node as root
	    // 3) Push the current node to S and set current = current->left until current is NULL
	    // 4) If current is NULL and stack is not empty then 
	    //     a) Pop the top item from stack.
	    //     b) Print the popped item, set current = popped_item->right 
	    //     c) Go to step 3.
	    // 5) If current is NULL and stack is empty then we are done.
		
		List<Integer> list = new LinkedList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode ptr = root; // let ptr be a referencne
		ptr = pushToMin(stack, ptr); // pass in by value, it won't change ptr unless we update it.
		while(ptr == null && stack.size() > 0) {
			ptr = stack.pop();
			list.add(ptr.val);
			ptr = ptr.right;
			ptr = pushToMin(stack, ptr);
		}
		return list;
	}

	public TreeNode pushToMin(Stack<TreeNode> stack, TreeNode ptr) {
		while(ptr != null) {
			stack.push(ptr);
			ptr = ptr.left;
		}
		return ptr;
	}

	// ps.
	// Java is a pass-by-value language
	// If we want to pass by reference, we go to wrap obejects in array.
	// And then use the array as an input.
	//
	// See the grapic example here:
	// https://stackoverflow.com/questions/9404625/java-pass-by-reference/9404727#9404727
	
	// pps.
	// pushToMin(ptr) takes amortized constant time
	// eg. Given a binary tree with n nodes.
	// we build its iterator and call next() n times.
	// In our process, funtion pushToMin(ptr) pushes total n nodes into the stack
	// Thus n-next() process calls stack.push(ptr) n times; 
	// that is, next() calls once stack.push(ptr) w.r.t. amortized analysis.
	
}
