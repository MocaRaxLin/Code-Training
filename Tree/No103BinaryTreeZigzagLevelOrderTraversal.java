package Tree;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No103BinaryTreeZigzagLevelOrderTraversal {

	public static void main(String[] args) {
		No103BinaryTreeZigzagLevelOrderTraversal sol = new No103BinaryTreeZigzagLevelOrderTraversal();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		
		String t = "[3,9,20,null,null,15,7]\n" + 
				"[1,2,3,4,null,6,7,8,null,10,11,12]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			List<List<Integer>> ans = sol.zigzagLevelOrder(root);
			show.showListListInt(ans);
		}
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		// --> O(n), where n = total number of nodes
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        traversal(root, 0, ret);
        return ret;
    }
    public void traversal(TreeNode node, int level, List<List<Integer>> list){
        if(node == null) return;
        if(list.size() == level) list.add(new LinkedList<Integer>());
        List<Integer> levelList = list.get(level);
        if(level % 2 == 0) levelList.add(node.val);
        else levelList.add(0, node.val);
        traversal(node.left, level+1, list);
        traversal(node.right, level+1, list);
    }
}
