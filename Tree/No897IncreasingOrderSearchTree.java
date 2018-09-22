package Tree;

import Util.Parser;

public class No897IncreasingOrderSearchTree {

	public static void main(String[] args) {
		No897IncreasingOrderSearchTree sol = new No897IncreasingOrderSearchTree();
		TreeNode tn = new TreeNode();
		String t = "[5,3,6,2,4,null,8,1,null,null,null,7,9]\n" + 
				"[379,826]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			TreeNode ans = sol.increasingBST(root);
			tn.showTree(ans);
		}
	}
	
	TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummy = new TreeNode(-1);
        cur = dummy;
        inorder(root);
        return dummy.right;
    }
    
    public void inorder(TreeNode root){
        if(root == null) return;
        // System.out.println(root.val);
        inorder(root.left);
        root.left = null;
        cur.right = root;
        cur = cur.right;
        inorder(root.right);
        
    }

}
