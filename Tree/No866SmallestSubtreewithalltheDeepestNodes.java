package Tree;

public class No866SmallestSubtreewithalltheDeepestNodes {

	public static void main(String[] args) {
		No866SmallestSubtreewithalltheDeepestNodes sol = new No866SmallestSubtreewithalltheDeepestNodes();
		TreeNode tn = new TreeNode();
		String t = "[3,5,1,6,2,0,8,null,null,7,4]\n" + 
				"[0,1,null,2]\n" + 
				"[0,1,2,3,null,null,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			TreeNode ans = sol.subtreeWithAllDeepest(root);
			tn.showTree(ans);
		}
	}
	
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
        // --> O(n), where n = # of node in this tree
        
        // As we know we want to find the ancestor of all leaf nodes
        // Therefore the height of each node becomes an important property
        // We use post order traversal to compute those heights
        // root.height = MAX(left.height, right.height) + 1
        // Here is the point!
        // If the height of both subtree are the same,
        // it means leaf nodes come from both subtrees,
        // so this root is a possible ancestor we want and return this root,
        // Otherwise, return the stored possible ancestor from the subtree with higher height.
        // i.e. From either left subtree or right subtree.
        
        TreeNode ret = computeHeight(root).subtreeAllDeepestNode;
        return ret;
    }
    
    public Tuple computeHeight(TreeNode root){
        if(root == null) return new Tuple(0, null);
        Tuple left = computeHeight(root.left);
        Tuple right = computeHeight(root.right);
        if(left.height < right.height){
            right.height++;
            return right;
        }else if(left.height > right.height){
            left.height++;
            return left;
        }else{
            return new Tuple(left.height + 1, root);
        }
    }
}
class Tuple {
    int height;
    TreeNode subtreeAllDeepestNode;
    public Tuple(int h, TreeNode node){
        height = h;
        subtreeAllDeepestNode = node;
    }
}