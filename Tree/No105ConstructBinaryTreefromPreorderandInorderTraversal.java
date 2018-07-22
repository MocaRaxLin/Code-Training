package Tree;

import java.util.HashMap;

import Util.Parser;

public class No105ConstructBinaryTreefromPreorderandInorderTraversal {

	public static void main(String[] args) {
		No105ConstructBinaryTreefromPreorderandInorderTraversal sol = new No105ConstructBinaryTreefromPreorderandInorderTraversal();
		Parser parser = new Parser();
		TreeNode tn = new TreeNode();
		String t = "[3,9,20,15,7]\n" + 
				"[9,3,15,20,7]\n" + 
				"[1,2,3]\n" + 
				"[2,3,1]\n" + 
				"[]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] preorder = parser.parseArray(s[i]);
			int[] inorder = parser.parseArray(s[i+1]);
			TreeNode ans = sol.buildTree(preorder, inorder);
			tn.showTree(ans);
		}
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		// --> time = O(n)
		// T(n) = 2T(n/2) + O(1)
		// c = log2/log2 = 1 => T(n) = O(n) by Master Theorem.
		
		// Intuition:
		// from preorder we know the root node is first element in preorder array, 
		// and then we got to know where is the root in inorder array
		// to split the two array into the following form.
		// preorder = [root, left subtree, right subtree]
		// inorder = [left subtree, root, right subtree]
		// we need to find the break point in preorder(end of left subtree) and in inorder(index of root).
		// After that we can break it into smaller problem,
		// i.e. we focus on left and right subtree seperately.
		//
		// Thanksfully, there is no duplicate element,
		// so we can use hash map to store pair (element, index) of inorder array.
		// This help search root in inorder array 
		// and end of left subtree in preorder take constant time to complete.
		// i.e.
		// root_idx_inorder = map.get(value)
		// end_left_preorder = start_left_preorder + root_idx_inorder - start_left_inorder
		
        if(preorder.length == 0) return null;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return getRoot(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }
    
    public TreeNode getRoot(int[] preorder, int[] inorder, int ps, int pe, int is, int ie, HashMap<Integer, Integer> map){
        if(pe < ps) return null;
        TreeNode ret = new TreeNode(preorder[ps]);
        if(ps == pe) return ret;
        int ir = map.get(preorder[ps]); // root index in inorder
        int pl = ps + ir - is; // left end index in preorder
        ret.left = getRoot(preorder, inorder, ps+1, pl, is, ir-1, map);
        ret.right = getRoot(preorder, inorder, pl+1, pe, ir+1, ie, map);
        return ret;
    }
}
