package Tree;

public class No235LowestCommonAncestorofaBinarySearchTree {

	public static void main(String[] args) {
		No235LowestCommonAncestorofaBinarySearchTree sol = new No235LowestCommonAncestorofaBinarySearchTree();
		TreeNode tn = new TreeNode();
		String t = "[6,2,8,0,4,7,9,null,null,3,5]\n" + 
				"2\n" + 
				"8\n" + 
				"[6,2,8,0,4,7,9,null,null,3,5]\n" + 
				"2\n" + 
				"0\n" + 
				"[1,null,2,null,3]\n" + 
				"3\n" + 
				"1";
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
        // --> O(logN), N = number of nodes in tree.
        
        // Intution:
        // if p < cur < q -> return cur
        // if p, q < cur -> go to cur.left
        // if cur < p, q -> go to cur.right
        // if cur == p or q -> return cur
        
        if(p.val > q.val){ TreeNode tmp = p; p = q; q = tmp; }
        TreeNode cur = root;
        while(cur != null && !(p.val < cur.val && cur.val < q.val) ){
            if(cur.val == p.val || cur.val == q.val) return cur;
            if(q.val < cur.val){
                cur = cur.left;
            }else{ // cur.val < p.val
                cur = cur.right;
            }
        }
        return cur;
    }
}
