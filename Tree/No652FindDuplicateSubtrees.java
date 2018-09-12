package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class No652FindDuplicateSubtrees {

	public static void main(String[] args) {
		No652FindDuplicateSubtrees sol = new No652FindDuplicateSubtrees();
		TreeNode tn = new TreeNode();
		String t = "[1,2,3,4,null,2,4,null,null,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			List<TreeNode> ans = sol.findDuplicateSubtrees(root);
			tn.showTreeList(ans);
		}
	}

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		// --> O(N^N)
		
		// Intuition:
		// We can encode a tree as a preorder serial
		// eg.
		//     4
		//    /       ->  convert -> 4,2,#,#,#
		//   2
		// use # as null node.
		
        List<TreeNode> ret = new LinkedList<TreeNode>();
        preorder(root, ret, new HashMap<String, Integer>());
        return ret;
    }
    
    public String preorder(TreeNode root, List<TreeNode> list, Map<String, Integer> map){
        if(root == null) return "#";
        String serial = root.val + "," + preorder(root.left, list, map) + "," + preorder(root.right, list, map);
        if(map.getOrDefault(serial, 0) == 1) list.add(root);
        int times = map.getOrDefault(serial, 0);
        map.put(serial, ++times);
        return serial;
    }
}
