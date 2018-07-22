package Tree;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No199BinaryTreeRightSideView {

	public static void main(String[] args) {
		No199BinaryTreeRightSideView sol = new No199BinaryTreeRightSideView();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		String t = "[]\n" + 
				"[1,2,3,null,5,null,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			List<Integer> ans = sol.rightSideView(root);
			show.showListInt(ans, true);
		}
	}
	
	public List<Integer> rightSideView(TreeNode root) {
		// --> time = O(n), where n = number of nodes in tree
        
		// Inuition:
		// Remeber the level order traversal in O(n)?
		// We use lists to store elements at the same level
		// and group those list into a big list.
		
		// This time we use right first DFS to deepen the tree 
		// and store the first element we meet at a new level.
		
        List<Integer> ret = new LinkedList<Integer>();
        DFSRight(root, 0, ret);
        return ret;
    }
    
    public void DFSRight(TreeNode root, int level, List<Integer> list){
        if(root == null) return;
        if(level == list.size()) list.add(root.val);
        DFSRight(root.right, level+1, list);
        DFSRight(root.left, level+1, list);
    }
}
