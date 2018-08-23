package Tree;

import Util.Parser;

public class No889ConstructBinaryTreefromPreorderandPostorderTraversal {

	public static void main(String[] args) {
		No889ConstructBinaryTreefromPreorderandPostorderTraversal sol = new No889ConstructBinaryTreefromPreorderandPostorderTraversal();
		Parser parser = new Parser();
		TreeNode tn = new TreeNode();
		String t = "[1,2,4,5,3,6,7]\n" + 
				"[4,5,2,6,7,3,1]\n" + 
				"[2,1]\n" + 
				"[1,2]\n" + 
				"[4,2,1,3]\n" + 
				"[3,1,2,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] pre = parser.parseArray(s[i]);
			int[] post = parser.parseArray(s[i+1]);
			TreeNode ans = sol.constructFromPrePost(pre, post);
			tn.showTree(ans);
		}
	}

    int[] postMap;
    int N;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        // --> O(n), where n = pre.length = post.length;
        N = pre.length;
        postMap = new int[N+1];
        for(int i = 0; i < pre.length; i++) postMap[post[i]] = i;
        return getRoot(pre, 0, N-1, post, 0, N-1);
    }
    
    private TreeNode getRoot(int[] pre, int a, int b, int[] post, int c, int d){
        TreeNode root = new TreeNode(pre[a]);
        if(a == b) return root;
        int idx = postMap[pre[a+1]];
        int len = idx - c + 1;
        root.left = getRoot(pre, a+1, a + len, post, c, c + len - 1);
        if(idx + 1 == d) return root; // eg. pre = [1,2], post = [2,1]
        root.right = getRoot(pre, a + len + 1, b, post, idx+1, d-1);
        return root;
    }
}
