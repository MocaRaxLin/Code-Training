package Tree;

public class No606ConstructStringfromBinaryTree {

	public static void main(String[] args) {
		No606ConstructStringfromBinaryTree sol = new No606ConstructStringfromBinaryTree();
		TreeNode tn = new TreeNode();
		
		Integer[][] inputs = new Integer[][] {
			{1,2,3,4}, {1,2,3,null,4}
		};
		for(int i = 0; i < inputs.length; i++) {
			TreeNode root = tn.array2Tree(inputs[i]);
			String ans = sol.tree2str(root);
			System.out.println(ans);
		}
	}
	
	public String tree2str(TreeNode t) {
		// --> O(n), where n = number of nodes in the given tree.
		
		// [1,2,3,4] -> "1(2(4))(3)"
		// [1,2,3,null,4] -> "1(2()(4))(3)"
		
		// We could see a pattern like the following
		// node = null -> ""
		// left -> "val(f[left])"
		// right -> "val()(f[right])"
		// left and right -> "val(f[left)(f[right])"
		
        if(t == null) return "";
        else if(t.left == null && t.right == null) return "" + t.val;
        else if(t.left == null && t.right != null) return  t.val + "()(" + tree2str(t.right) + ")";
        else if(t.left != null && t.right == null) return  t.val + "(" + tree2str(t.left) + ")";
        else return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
    }
}
