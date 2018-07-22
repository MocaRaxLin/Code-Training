package Tree;

import java.util.ArrayList;
import java.util.List;

public class No662MaximumWidthofBinaryTree {

	public static void main(String[] args) {
		No662MaximumWidthofBinaryTree sol = new No662MaximumWidthofBinaryTree();
		TreeNode tn = new TreeNode();
		String t = "[1,3,2,5,3,null,9]\n" + 
				"[1,3,null,5,4]\n" + 
				"[1,2,3,5]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int ans = sol.widthOfBinaryTree(root);
			System.out.println(ans);
		}
	}

	public int widthOfBinaryTree(TreeNode root) {
        // --> time = O(n), where n = number of nodes in tree
        
        // Inspired by:
        // https://leetcode.com/problems/maximum-width-of-binary-tree/discuss/106654/JavaC++-Very-simple-dfs-solution
        
        // Intuition:
        // Store left-most node in list, and do DFS to compute the width
        // 
        // Think about a tree represented in 1-index-based array.
        // The first index store the root, index 2 store its left child and 3 store its right,
        // so a node at index i has left child at index 2*i, and right child at index 2*i + 1.
        // We use this property to compute the width.
        //
        // 1. Store the index we meet first in a new level
        // 2. When we meet a level again, compute the width by 
        //    the current index with the leftmost index at the same level.
        //    int width = idx - leftMost;
        // 3. Go to left child (2*idx) and right (2*idx+1),
        //    compute their max width rooted at them and return back to root.
        // 4. return the max value of 3 widthes.
        //    (computed so far: cur width, left_max_width, right_max_width)
        
        return helper(root, 0, 1, new ArrayList<Integer>());
    }
    
    public int helper(TreeNode root, int level, int idx, List<Integer> leftBound){
        if(root == null) return 0;
        if(level == leftBound.size()) leftBound.add(idx);
        int cur = idx - leftBound.get(level) + 1;
        int left = helper(root.left, level + 1, 2*idx, leftBound);
        int right = helper(root.right, level + 1, 2*idx + 1, leftBound);
        return Math.max(cur, Math.max(left, right));
        
    }
}
