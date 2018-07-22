package Tree;

import Util.Parser;

public class No654MaximumBinaryTree {

	public static void main(String[] args) {
		No654MaximumBinaryTree sol = new No654MaximumBinaryTree();
		Parser parser = new Parser();
		TreeNode tn = new TreeNode();
		String t = "[]\n" + 
				"[3,2,1,6,0,5]\n" + 
				"[6,3,2,67,7,3,73,5,63,6,34,6,3,7,3,53,6,6]\n" + 
				"[-1,-1,-1,-1,-1,-1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] nums = parser.parseArray(s[i]);
			TreeNode ans = sol.constructMaximumBinaryTree(nums);
			tn.showTree(ans);
		}
	}

	public TreeNode constructMaximumBinaryTree(int[] nums) {
        // --> time = O(nlogn) to O(n^2)
        // T(n) = 2*T(n/2) + n
        // T(n) = T(n-1) + n
        
		// Find the max and seperate the input array into L - root - R
		// make root node of value max
		// deal with left and right parts in the same way.
		
        return getRoot(nums, 0, nums.length -1);
    }
    
    public TreeNode getRoot(int[] A, int i, int j){
        if(j < i) return null;
        else if(i == j) return new TreeNode(A[i]);
        int idxMax = getMaxIdx(A, i, j);
        TreeNode ret = new TreeNode(A[idxMax]);
        ret.left = getRoot(A, i, idxMax-1);
        ret.right = getRoot(A, idxMax+1, j);
        return ret;
    }
    
    public int getMaxIdx(int[] A, int i, int j){
        int ret = -1;
        int max = Integer.MIN_VALUE;
        for(int k = i; k <= j; k++){
            if(A[k] >= max){
                max = A[k];
                ret = k;
            }
        }
        return ret;
    }
}
