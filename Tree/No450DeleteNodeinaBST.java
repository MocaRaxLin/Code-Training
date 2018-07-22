package Tree;

public class No450DeleteNodeinaBST {

	public static void main(String[] args) {
		No450DeleteNodeinaBST sol = new No450DeleteNodeinaBST();
		TreeNode tn = new TreeNode();
		String t = "[5,3,6,2,4,null,7]\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i+=2) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int key  = Integer.parseInt(s[i+1]);
			TreeNode ans = sol.deleteNode(root, key);
			tn.showTree(ans);
		}
	}

	public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        
        if(root.val < key) root.right = deleteNode(root.right, key);
        else if(key < root.val) root.left = deleteNode(root.left, key);
        
        // got key node
        // has only one child node
        else if(root.right == null) root = root.left;
        else if(root.left == null) root = root.right;
        
        else{
            // Important: has both child nodes
            TreeNode successor = getMin(root.right);
            // Update root's value by successor's value
            root.val = successor.val;
            // Therefore the right subtree will be the same tree
            // but remove the node of value successor's val.
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }
    
    public TreeNode getMin(TreeNode x){
        while(x.left != null) x = x.left;
        return x;
    }
}
