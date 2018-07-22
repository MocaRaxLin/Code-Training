package Tree;

public class No623AddOneRowtoTree {

	public static void main(String[] args) {
		No623AddOneRowtoTree sol = new No623AddOneRowtoTree();
		TreeNode tn = new TreeNode();
		String t = "[4,2,6,3,1,5]\n" + 
				"1\n" + 
				"2\n" + 
				"[4,2,null,1,3]\n" + 
				"7\n" + 
				"4\n" + 
				"[0]\n" + 
				"1\n" + 
				"1";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int v = Integer.parseInt(s[i+1]);
			int d = Integer.parseInt(s[i+2]);
			TreeNode ans = sol.addOneRow(root, v, d);
			tn.showTree(ans);
		}
	}
	
	public TreeNode addOneRow(TreeNode root, int v, int d) {
		// --> time = O(m), where m is the number of nodes in level 1 to d
		
		// Intuition:
		// Use DFS to the level we want to manipulate
		// Say add new nodes at d, then we want to manipulate at level of d-1
		// Save roots of left and right subtree in tmp variables
		// Add new node of value v and refer back to the new nodes
		
		// Level calculate:
		// Because the root is at level 1, decrease d and stop at d = 0 does not work.
		// If the level is 0-based and we want to reach level d-1,
		// then we stop at d = 1 using decreased-d method
		// Now the level is 1-based and still want to reach level d-1,
		// then we stop at d = 2.
		//
		// 0-based level =   0,   1,   2,   3, ...,   d-2,  "d-1"
		// 1-based level =   1,   2,   3,   4, ..., "d-1",     d
		// 
		// decrease d    =   d, d-1, d-2, d-3, ...,    2 ,     1
		
		
        if(d == 1){
            TreeNode dummy = new TreeNode(v);
            dummy.left = root;
            return dummy;
        }
        DFS(root, d, v);
        return root;
    }
    
    public void DFS(TreeNode root, int d, int v){
        if(root == null) return;
        if(d == 2){
            TreeNode leftTmp = root.left;
            TreeNode rightTmp = root.right;
            root.left = new TreeNode(v);
            root.right = new TreeNode(v);
            root.left.left = leftTmp;
            root.right.right = rightTmp;
        }else{
            DFS(root.left, d-1, v);
            DFS(root.right, d-1, v);
        }
    }
}
