package Tree;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class GfGMorrisInorderTraversal {

	public static void main(String[] args) {
		GfGMorrisInorderTraversal sol = new GfGMorrisInorderTraversal();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		String t = "[1,2,3,4,5,6,7,8,null,null,9,null,null,0,null]\n"
				+ "[1,2,3,4,5,6,7]\n"
				+ "[0,1,2,3,4,null,5,null,null,6,7,8,null]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			//List<Integer> ans = sol.inorderMorrisTraversal(root);
			List<Integer> ans = sol.preorderMorrisTraversal(root);
			show.showListInt(ans, true);
		}
	}
	
	public List<Integer> inorderMorrisTraversal(TreeNode root){
		// --> time = O(n), space = O(1)
		
		// Source: https://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
		//
		// Intuition:
		// Build a link for root's predecessor to root.
		// The predecessor of root is the rightmost node of root's left child.
		// Because right child of predecessor prev is null by default (i.e. prev.right = null),
		// we set prev.right = root for traversal convenience.
		//
		// After linking and reaching the root from this connection, 
		// we revert the change (disconnect it), print root and point to root.right.
		
		List<Integer> ret = new LinkedList<Integer>();
		TreeNode cur = root, prev = null;
		while(cur != null) {
			if(cur.left == null) {
				ret.add(cur.val);
				cur = cur.right;
			} else {
				prev = cur.left;
				while(prev.right != null && prev.right != cur)
					prev = prev.right;
				if(prev.right == null) {
					prev.right = cur;
					cur = cur.left;
				} else {
					prev.right = null;
					ret.add(cur.val);
					cur = cur.right;
				}
			}
		}
		return ret;
	}
	
	// Preorder Morris Traversal
	// Only one line difference
	// Output root first and than set the link.
	public List<Integer> preorderMorrisTraversal(TreeNode root){
		List<Integer> ret = new LinkedList<Integer>();
		TreeNode cur = root, prev = null;
		while(cur != null) {
			if(cur.left == null) {
				ret.add(cur.val);
				cur = cur.right;
			} else {
				prev = cur.left;
				while(prev.right != null && prev.right != cur)
					prev = prev.right;
				if(prev.right == null) {
					ret.add(cur.val); // preorder
					prev.right = cur;
					cur = cur.left;
				} else {
					prev.right = null;
					// ret.add(cur.val); inorder
					cur = cur.right;
				}
			}
		}
		return ret;
	}
	
	// ps.
	// Postorder Morris Traversal - Hard
	// See image illustration in AnnieKim's blog
	// Source code by icode123
	// https://github.com/icode123/geeksforgeeks/blob/master/Morris%20traversal%20for%20Postorder.cpp
}
