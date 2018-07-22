package Tree;

public class No669TrimaBinarySearchTree {

	public static void main(String[] args) {
		No669TrimaBinarySearchTree sol = new No669TrimaBinarySearchTree();
		TreeNode tn = new TreeNode();
		String t = "[1,0,2]\n" + 
				"1\n" + 
				"2\n" + 
				"[3,0,4,null,2,null,null,1]\n" + 
				"1\n" + 
				"3\n" + 
				"[5,3,8,2,4,6,10,1,2,4,5,5,6,9,10]\n" + 
				"5\n" + 
				"8\n" + 
				"[6,5,6,1]\n" + 
				"5\n" + 
				"6";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int L = Integer.parseInt(s[i+1]);
			int R = Integer.parseInt(s[i+2]);
			TreeNode ans = sol.trimBST(root, L, R);
			tn.showTree(ans);
		}
	}

	public TreeNode trimBST(TreeNode root, int L, int R) {
		// --> time = O(n), where n = number of nodes in tree
        
		// Intuition:
		// Handle child nodes first, then deal with root, return the node we want.
		// That is postorder traversal
		// 
		// for each node root we check the root's value if it is in range [L,R]
		// if root.val < L return right child
		// if root.val > R reuurn left child
		// else L <= root.val <= R return root itself.
		
		
        if(root == null) return null;
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        if(root.val < L) return root.right;
        else if(R < root.val) return root.left;
        else return root;
    }
}
