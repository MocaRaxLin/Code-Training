package Tree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class No501FindModeinBinarySearchTree {

	public static void main(String[] args) {
		No501FindModeinBinarySearchTree sol = new No501FindModeinBinarySearchTree();
		TreeNode tn = new TreeNode();
		String t = "[]\n" + 
				"[1,null,2,2]\n" + 
				"[5,1,7,1,1,7,7]\n" + 
				"[2,1,null,null,2]\n" + 
				"[5,2,5,2,2,5,8,null,null,null,null,null,null,8,8]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int[] ans = sol.findMode(root);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] findMode(TreeNode root) {
        // --> time = O(n), space = O(n), where n = number of nodes in tree
        // inorder + online collection
        //
        // Follow up:
        // use constant space? I have no idea,
        // because if we want to return an array size of n,
        // I got to use O(n) space.
        
        if(root == null) return new int[0];
        List<Integer> list = new LinkedList<Integer>();
        inorder(root, list);
        
        int ret[] = new int[list.size()];
        Iterator<Integer> i = list.iterator();
        int p = 0;
        while(i.hasNext()) ret[p++] = i.next();
        return ret;
        
    }
    
    Integer prev = null;
    int counter = 1, max = 0;
    public void inorder(TreeNode root, List<Integer> list){
        if(root == null) return;
        inorder(root.left, list);
        if(prev != null){
            if(prev == root.val) counter++;
            else counter = 1;
        }
        if(counter > max){
            max = counter;
            list.clear();
            list.add(root.val);
        }else if(counter == max){
            list.add(root.val);
        }
        prev = root.val;
        inorder(root.right, list);
    }
}
