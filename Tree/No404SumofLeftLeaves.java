package Tree;

public class No404SumofLeftLeaves {

	public static void main(String[] args) {
		No404SumofLeftLeaves sol = new No404SumofLeftLeaves();
		TreeNode tn = new TreeNode();
		String t = "[1,2,3]\n" + 
				"[1,2,null,4,5,null,6]\n" + 
				"[1,2,3,null,null,4,5]\n" + 
				"[1]\n" +
				"[]\n" +
				"[3,9,20,null,null,15,7]\n" + 
				"[2]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int ans = sol.sumOfLeftLeaves(root);
			System.out.println(ans);
		}
	}

	public int sumOfLeftLeaves(TreeNode root) {
        // --> time = O(n), where n = number of nodes in tree.
		
        // Intuiton:
        // Sum up values of roots each of them is left child and
        // also leaf node.
        
        return DFS(root, 'r');
    }
    public int DFS(TreeNode root, char side){
        if(root == null) return 0;
        if(side == 'l' && root.left == null && root.right == null) return root.val;
        return DFS(root.left, 'l') + DFS(root.right, 'r');
    }
}
