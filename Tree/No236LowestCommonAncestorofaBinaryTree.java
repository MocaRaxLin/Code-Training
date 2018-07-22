package Tree;

public class No236LowestCommonAncestorofaBinaryTree {

	public static void main(String[] args) {
		No236LowestCommonAncestorofaBinaryTree sol = new No236LowestCommonAncestorofaBinaryTree();
		TreeNode tn = new TreeNode();
		String t = "[3,5,1,6,2,0,8,null,null,7,4]\n" + 
				"5\n" + 
				"1\n" + 
				"[3,5,1,6,2,0,8,null,null,7,4]\n" + 
				"5\n" + 
				"4\n" + 
				"[1]\n" + 
				"1\n" + 
				"1\n" + 
				"[1,2]\n" + 
				"1\n" + 
				"2\n" +
				"[6,2,8,0,4,7,9,null,null,3,5]\n" +
				"2\n" +
				"8\n" +
				"[6,2,8,0,4,7,9,null,null,3,5]\n" +
				"2\n" + 
				"4";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			TreeNode p = tn.getNodeById(root, Integer.parseInt(s[i+1]));
			TreeNode q = tn.getNodeById(root, Integer.parseInt(s[i+2]));
			TreeNode ans = sol.lowestCommonAncestor(root, p, q);
			System.out.println(ans.val);
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		// --> time = O(n), where n = number of nodes in tree.
		
		// Intuition for LCA:
		// Node: L - root - R
		// If root is null, is p, or is q, then toor return itself
		// If p in subtree L and q in subtree R, then return itself again.
		//
		// If returned node from "left" node is null, 
		// then it means both p and q are not in left subtree,
		// so return the node which returned from "right" subtree.
		// The other case (node from right subtree is null) is the opposite.
		
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        else if(right == null) return left;
        else return root;
    }
}
