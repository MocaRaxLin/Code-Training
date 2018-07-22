package Tree;

public class No230KthSmallestElementinaBST {

	public static void main(String[] args) {
		No230KthSmallestElementinaBST sol = new No230KthSmallestElementinaBST();
		TreeNode tn = new TreeNode();
		String t = "[3,1,4,null,2]\n" + 
				"1\n"
				+ "[5,3,6,2,4,null,null,1]\n"
				+ "3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.kthSmallest(root, k);
			System.out.println(ans);
		}
	}

	public int kthSmallest(TreeNode root, int k) {
        // --> time = O(k)
        
        // Inorder traversal to the k-th node
		
        K = k; // be caresul global variable
        findNodeAt(root);
        return ans.val;
    }
    
    int K;
    TreeNode ans;
    public void findNodeAt(TreeNode root){
        if(root == null || K <= 0) return;
        findNodeAt(root.left);
        K--;
        if(K == 0) ans = root;
        findNodeAt(root.right);
    }
    
    // Follow up:
    // What if the BST is modified (insert/delete operations) often 
    // and you need to find the kth smallest frequently? 
    // How would you optimize the kthSmallest routine?
    //
    // Compare the values of those operations with 
    // the k-th smallest value "ans" we just found.
    //
    // if insert i, i >= ans => k-th smallest number is still ans
    // Otherwise we find the successor of "ans" which take O(logN)
    //
    // if delete i,  i > ans => k-th smallest number is still ans
    // Otherwise we find the predecessor of "ans" BEFORE DELETE which take O(logN)
    
    // Successor(x):
    // if(x.right != null) return MIN(x.right)
    // p = x.parent
    // while(p != null && x == p.right){
    //     x = p
    //     p = p.parent
    // }
    // return p
    //
    // Predecessor(x):  It is symmetric to Successor(x).
    // if(x.left != null) return MAX(x.left)
    // p = x.parent
    // while(p != null && x = p.left){
    //     x = p
    //     p = p.parent
    // }
    // return p
}
