package Tree;

public class No124BinaryTreeMaximumPathSum {

	public static void main(String[] args) {
		No124BinaryTreeMaximumPathSum sol = new No124BinaryTreeMaximumPathSum();
		TreeNode tn = new TreeNode();
		String t = "[1,2,3]\n" + 
				"[2,9,20,null,null,15,7]\n" + 
				"[2,-1]\n" +
				"[-10,9,20,null,null,15,7]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int ans = sol.maxPathSum(root);
			System.out.println(ans);
		}
	}

	public int maxPathSum(TreeNode root) {
		// --> time = O(n), where n = number of tree nodes
		
		// Use postorder traversal
		// To compute path sum from left and right children.
		// case 1:
		//     cross root -> Update max sum by MAX( maxSum, leftSum+root.val+rightSum )
		// case 2:
		//     not cross  -> Pick the larger sum plus root.val as representing path sum of root.
		
		
		maxSum = Integer.MIN_VALUE;
        maxLenOneSide(root);
        return maxSum;
    }
    
    int maxSum = Integer.MIN_VALUE;
    public int maxLenOneSide(TreeNode root){
        if(root == null) return 0;
        int left = maxLenOneSide(root.left);
        left = left < 0 ? 0: left; // cut line if max line < 0
        int right = maxLenOneSide(root.right);
        right = right < 0 ? 0: right;
        maxSum = Math.max(maxSum, left + root.val + right);
        return Math.max(left, right) + root.val;
    }
}
