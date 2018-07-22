package Tree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class No99RecoverBinarySearchTree {

	public static void main(String[] args) {
		No99RecoverBinarySearchTree sol = new No99RecoverBinarySearchTree();
		TreeNode tn = new TreeNode();
		String t = "[1,3,null,null,2]\n"
				+ "[3,1,4,null,null,2]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			sol.recoverTree(root);
			tn.showTree(root);
		}
	}

	public void recoverTree(TreeNode root) {
		// --> time = O(n), space = O(n)
		
		// 1916 / 1916 test cases passed.
		// Runtime: 34 ms
		// Suprisingly fast
		
		// Intuition:
		// 1. Use tree traversal to get vals in all nodes
		// 2. Sort them
		// 3. Use inorder traversal to re-deploy val back to BST
		
        List<Integer> list = new LinkedList<Integer>();
        inorderGet(list, root);
        int[] A = new int[list.size()];
        Iterator<Integer> i = list.iterator(); // use iterator rather than list.get(idx)
        int j = 0;
        while(i.hasNext()) A[j++] = i.next();
        Arrays.sort(A);
        inorderPut(A, new int[]{0}, root);
    }
    
    public void inorderPut(int[] A, int[] idx, TreeNode root){
        if(root == null) return;
        inorderPut(A, idx, root.left);
        root.val = A[idx[0]++];
        inorderPut(A, idx, root.right);
        
    }
    
    public void inorderGet(List<Integer> list, TreeNode root){
        if(root == null) return;
        inorderGet(list, root.left);
        list.add(root.val);
        inorderGet(list, root.right);
    }
}
