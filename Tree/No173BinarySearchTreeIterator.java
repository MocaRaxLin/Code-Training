package Tree;

import java.util.Stack;

public class No173BinarySearchTreeIterator {

	public static void main(String[] args) {
		No173BinarySearchTreeIterator sol = new No173BinarySearchTreeIterator();
		TreeNode tn = new TreeNode();
		String t = "[]\n" + 
				"[4,2,6,1,3,5,7]\n" +
				"[2,1,3]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			BSTIterator b = new BSTIterator(root);
			System.out.print("[");
			while(b.hasNext()) {
				System.out.print(b.next() + ",");
			}
			System.out.print("]\n");
		}
	}

}

class BSTIterator {
    
    // I use Stack to store directed left children from root.
    // When next() be called, I just pop one element and process its right child as new root.
    
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        pushToMin(root);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return stack.size() > 0;
    }

    /** @return the next smallest number */
    public int next() {
    	// Important:
    	// Get Current and point to the Next!
        TreeNode cur = stack.pop();
        pushToMin(cur.right);
        return cur.val;
    }
    
    public void pushToMin(TreeNode x){
        while(x != null){
            stack.push(x);
            x = x.left;
        }
    }
}