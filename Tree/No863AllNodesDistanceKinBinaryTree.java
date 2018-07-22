package Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Util.Show;

public class No863AllNodesDistanceKinBinaryTree {

	public static void main(String[] args) {
		No863AllNodesDistanceKinBinaryTree sol = new  No863AllNodesDistanceKinBinaryTree();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		
		String t = "[3,5,1,6,2,0,8,null,null,7,4]\n" + 
				"5\n" + 
				"2\n" + 
				"[9,3,10,5,1,null,null,6,2,0,8,null,null,7,4]\n" + 
				"5\n" + 
				"2";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			TreeNode target = tn.getNodeById(root, Integer.parseInt(s[i+1]));
			int K = Integer.parseInt(s[i+2]);
			List<Integer> ans = sol.distanceK(root, target, K);
			show.showListInt(ans, true);
		}
	}

	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // --> O(n), where n is number of nodes in the binary tree
        
        // Get parent refernce by BFS
        HashMap<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
        parent.put(root, null);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(queue.size() > 0){
            TreeNode cur = queue.poll();
            if(cur.left != null){
                queue.offer(cur.left);
                parent.put(cur.left, cur);
            }
            if(cur.right != null){
                queue.offer(cur.right);
                parent.put(cur.right, cur);
            }
        }
        
        List<Integer> ret = new LinkedList<Integer>();
        // get K-distance child nodes
        getKDisChild(target, K, ret);
        
        // get K-distance sibiling nodes or ancestor node
        for(int i = K - 1; i >= 0; i--){ // var i is p's
            TreeNode p = parent.get(target);
            if(p == null) break;
            if(i == 0){ ret.add(p.val); break; }
            if(target == p.left) getKDisChild(p.right, i-1, ret);
            else getKDisChild(p.left, i-1, ret);
            target = p;
        }
        return ret;
    }
    
    public void getKDisChild(TreeNode root, int k, List<Integer> ret){
        if(root == null) return;
        else if(k == 0){ ret.add(root.val); return; }
        getKDisChild(root.left, k-1, ret);
        getKDisChild(root.right, k-1, ret);
    }
}
