package Tree;

public class No449SerializeandDeserializeBST {

	public static void main(String[] args) {
		No449SerializeandDeserializeBST sol = new No449SerializeandDeserializeBST();
		TreeNode tn = new TreeNode();

		String t = "[2,1,3]\n" + 
				"[]\n" + 
				"[5,3,6,1,4,null,8,null,2,null,null,7,9]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			String ans1 = sol.serialize(root);
			System.out.println(ans1);
			TreeNode ans2 = sol.deserialize(ans1);
			tn.showTree(ans2);
		}
	}

	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // --> time = O(n)
    	// No duplicate element in BST
        // use preoder traversal
    	
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        sb.deleteCharAt(0);
        return sb.toString();
    }
    
    public void preorder(TreeNode root, StringBuilder sb){
        if(root == null) return;
        sb.append(","+root.val);
        preorder(root.left, sb);
        preorder(root.right, sb);
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // --> time = O(nlogn)
        // Best:  T(n) = 2T(n/2) + O(n) -> T(n) = O(nlogn)
        // Worst: T(n) = T(n-1) + O(n) -> T(n) = O(n^2)
        
        // Decode preorder traversal by finding the root of right child
        // eg. preorder = [5,3,1,2,4,6,8,7,9]
        // root = 5, root of left = 3, root of right = 6 by linear search
        // so [3,1,2,4] and [6,8,7,9] become two subproblem to construct BST
        
        if(data.length() == 0) return null;
        String[] s = data.split(",");
        int[] ints = new int[s.length];
        for(int i = 0; i < ints.length; i++) ints[i] = Integer.parseInt(s[i]);
        return getRoot(ints, 0, ints.length-1);
    }
    
    public TreeNode getRoot(int[] ints, int s, int e){
        if(s > e) return null;
        TreeNode ret = new TreeNode(ints[s]);
        if(s == e) return ret;
        int r = s+1;
        while(r <= e && ints[r] < ints[s]) r++;
        ret.left = getRoot(ints, s+1, r-1);
        ret.right = getRoot(ints, r, e);
        return ret;
    }
}
