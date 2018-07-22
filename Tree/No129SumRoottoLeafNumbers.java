package Tree;

public class No129SumRoottoLeafNumbers {

	public static void main(String[] args) {
		No129SumRoottoLeafNumbers sol = new No129SumRoottoLeafNumbers();
		TreeNode tn = new TreeNode();
		String t = "[1,2,3]\n" + 
				"[4,9,0,5,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int ans = sol.sumNumbers(root);
			System.out.println(ans);
		}
	}

	public int sumNumbers(TreeNode root) {
        // --> time = O(n), where n = number of nodes in tree
        
        // Intuition:
        // Go down to leaf node and accumulate value of digits 
		// i.e. int curSum = givenSum*10 + root.val;
		// 
		// Tip:
		// how to check we are at a leaf node?
		// if a node does not have left and right children,
		// then we are at a leaf node.
		// i.e. if(root.left == null && root.right == null)
		//
		// Return sum of subtree by adding results of left and right children.
		
        return sum(root, 0);
    }
    
    public int sum(TreeNode root, int givenSum){
        if(root == null) return 0;
        int curSum = givenSum*10 + root.val;
        if(root.left == null && root.right == null) return curSum;
        return sum(root.left, curSum) + sum(root.right, curSum);
    }
}
