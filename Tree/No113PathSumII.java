package Tree;

import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No113PathSumII {

	public static void main(String[] args) {
		No113PathSumII sol = new No113PathSumII();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		String t = "[5,4,8,11,null,13,4,7,2,null,null,5,1]\n" + 
				"22\n" + 
				"[]\n" + 
				"0";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i+=2) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int sum = Integer.parseInt(s[i+1]);
			List<List<Integer>> ans = sol.pathSum(root, sum);
			show.showListListInt(ans);
		}
	}
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		// --> time = O(n)
		
		// Intuition:
		// Use DFS to store path from root to current node
		// If remaining sum = 0 and we are at leaf node, then store the path list
		// i.e. sum - root.val == 0 && root.left == null && root.right == null
		// Else go to left and right child
		// After we back to current node, pop this node from path list.
		
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        List<Integer> list = new LinkedList<Integer>();
        DFS(root, ret, list, sum);
        return ret;
    }
    
    public void DFS(TreeNode root, List<List<Integer>> ret, List<Integer> list, int sum){
        if(root == null) return;
        list.add(root.val);
        if(sum - root.val == 0 && root.left == null && root.right == null) ret.add(new LinkedList<Integer>(list));
        DFS(root.left, ret, list, sum - root.val);
        DFS(root.right, ret, list, sum - root.val);
        list.remove(list.size()-1);
    }

}
