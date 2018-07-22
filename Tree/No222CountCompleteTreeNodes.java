package Tree;

public class No222CountCompleteTreeNodes {

	public static void main(String[] args) {
		No222CountCompleteTreeNodes sol = new No222CountCompleteTreeNodes();
		TreeNode tn = new TreeNode();
		String t = "[]\n" + 
				"[1]\n" + 
				"[1,2]\n" + 
				"[1,2,3]\n" + 
				"[1,2,3,4]\n" + 
				"[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int ans = sol.countNodes(root);
			System.out.println(ans);
		}
	}
	public int countNodes(TreeNode root) {
        // --> O(logNlogN), where N is number of the root
        // T(N) = T(N/2) + O(logN)
        // 
        // Master Theorom a = 1, b = 2, f(N) = O(logN)
        // c = log(a)/log(b) = 0
        // case 1: f(N) = O(N^(0-ε)), ε > 0 ? ... no
        // case 3: f(N) = Ω(N^(0+ε)), ε > 0 ? ... no 
        // case 2: f(N) = Θ(N^0 * (logN)^k), k = 1 ... yes!
        // T(N) = Θ(N^0 * (logN)^(k+1)) = logNlogN
        
        // 18 / 18 test cases passed.
        // Runtime: 46 ms
        
        if(root == null) return 0;
        int left = leftLevel(root.left); // logN
        int right = leftLevel(root.right); // logN
        
        // left height == right height => left subtree is a complete tree
        // left height > right height => right subtree is a complete tree
        return left == right ? (1 << left) + countNodes(root.right): countNodes(root.left) + (1 << right);
    }
	
	public int countNodes0(TreeNode root) {
        // --> O(logNlogN), where N is number of the root
        // T(N) = 2logN + 2logN + T(N/2)
        //      = T(N/2) + 4logN
        // 
        // Master Theorom a = 1, b = 2, f(N) = 4logN
        // c = log(a)/log(b) = 0
        // case 1: f(N) = O(N^(0-ε)), ε > 0 ? ... no
        // case 3: f(N) = Ω(N^(0+ε)), ε > 0 ? ... no 
        // case 2: f(N) = Θ(N^0 * (logN)^k), k = 1 ... yes!
        // T(N) = Θ(N^0 * (logN)^(k+1)) = logNlogN
        
        // Intuition:
        // If the tree is not complete just use tree travesal.
        // It takes O(N).
        
        // But this is complete tree!
        // Hint: Compare the left most level and right most level
        
        if(root == null) return 0;
        int lLevel = leftLevel(root.left); // logN
        int rLevel = rightLevel(root.right); // logN
        
        // we get a complete and full tree
        // better than Math.pow(2, lLevel + 1)
        if(lLevel == rLevel) return (1 << lLevel + 1) - 1; // 414 ms -> 109 ms
        
        // left level != right level
        // Only one side needs to do recursion,
        // the other will return at lLevel == rLevel.
        int lCount = countNodes(root.left); // either one is T(N/2)
        int rCount = countNodes(root.right); // the other is 2logN
        return lCount + 1 + rCount ;
    }
    
	// recursion -> iterator
    // 109 ms -> 68 ms
    // Recursion takes double amount of time to do same process, 
    // because recursion is like stack
    // and pop is needed to go back to previous call.
    public int leftLevel(TreeNode root){
        int ret = 0;
        while(root != null){
            root = root.left;
            ret++;
        }
        return ret;
    }
    public int rightLevel(TreeNode root){
        int ret = 0;
        while(root != null){
            root = root.right;
            ret++;
        }
        return ret;
    }

}
