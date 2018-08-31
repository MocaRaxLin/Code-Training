package Tree;

import Util.Parser;

public class ExtraDistanceInBST {

	public static void main(String[] args) {
		ExtraDistanceInBST sol = new ExtraDistanceInBST();
		Parser parser = new Parser();
		String t = "[2,3,1,0,7,4,6,5,8]\n" + 
				"1\n" + 
				"4\n" + 
				"[2,3,1,0,7,4,6,5,8]\n" + 
				"8\n" + 
				"0\n" + 
				"[3,2,1,5,4,6]\n" + 
				"6\n" + 
				"1\n" + 
				"[3,2,1,5,4,6]\n" + 
				"6\n" + 
				"7";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] nodes = parser.parseArray(s[i]);
			int p = Integer.parseInt(s[i+1]);
			int q = Integer.parseInt(s[i+2]);
			int ans = sol.constructBSTFindDis(nodes, p, q);
			System.out.println(ans);
		}
	}

	private int constructBSTFindDis(int[] A, int p, int q) {
		boolean[] seen = new boolean[2];
		for(int e: A) {
			if(e == p) seen[0] = true;
			else if(e == q) seen[1] = true;
		}
		if(!(seen[0]&&seen[1])) return -1;
		// make p < q
		if(p > q) { int tmp = p; p = q; q = tmp; }
		
		// construct BST -> nlogn
		TreeNode root = new TreeNode(A[0]);
		for(int i = 1; i < A.length; i++) {
			TreeNode cur = root;
			while(cur!=null) {
				if(A[i] < cur.val) {
					if(cur.left == null) {
						cur.left = new TreeNode(A[i]);
						break;
					}else cur = cur.left;
				}else {
					if(cur.right == null) {
						cur.right = new TreeNode(A[i]);
						break;
					}else cur = cur.right;
				}
			}
		}
		
		// find common anccestor -> logn
		TreeNode anc = root;
		while(q < anc.val || anc.val < p) {
			if(q < anc.val) anc = anc.left;
			else anc = anc.right;
		}
		
		// get height from common anccestor -> 2logn
		int[] dis = new int[2];
		dis[0] = getHeight(anc, p);
		dis[1] = getHeight(anc, q);
		return dis[0] + dis[1];
	}

	private int getHeight(TreeNode anc, int node) {
		TreeNode cur = anc;
		int ret = 0;
		while(cur != null && cur.val != node) {
			if(cur.val < node) cur = cur.right;
			else cur = cur.left;
			ret++;
		}
		return ret;
	}


}
