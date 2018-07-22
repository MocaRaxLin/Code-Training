package Tree;

import java.util.Stack;

public class No297SerializeandDeserializeBinaryTree {

	public static void main(String[] args) {
		No297SerializeandDeserializeBinaryTree sol = new No297SerializeandDeserializeBinaryTree();
		TreeNode tn = new TreeNode();
		String t = "[1,2,3]\n" + 
				"[1,2,null,4,5,null,6]\n" + 
				"[1,2,3,null,null,4,5]\n" + 
				"[1]\n" +
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			String ans1 = sol.serialize(root);
			System.out.println(ans1);
			TreeNode ans2 = sol.deserialize(ans1);
			tn.showTree(ans2);
		}
	}
	
	// Encodes a tree to a single string.
    public String serialize(TreeNode root) {
    	// --> time = O(n), where n = number of nodes in tree
    	
    	// Use preorder traversal
    	// eg. tree = [1,2,null,4,5,null,6]
    	//     data = 1,2,4,#,6,#,#,5,#,#,#
    	// # -> null
    	
        StringBuilder sb = new StringBuilder();
        DFS_S(root, sb);
        sb.deleteCharAt(0);
        //System.out.println("serialize: " + sb.toString());
        return sb.toString();
    }
    
    public void DFS_S(TreeNode root, StringBuilder sb){
        if(root == null){ sb.append(",#"); return; }
        sb.append("," + String.valueOf(root.val));
        DFS_S(root.left, sb);
        DFS_S(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	// --> time = O(n), where n = number of nodes in tree
    	
    	// Decode:
    	// eg. data = 1,2,4,#,6,#,#,5,#,#,#
    	// iterate all elements in data
    	// -> 1,2,4,#,6,#,#
    	// -> 1,2,4,#,#
    	// -> 1,2,#
    	// -> 1,2,#,5,#,#
    	// -> 1,2,#,#
    	// -> 1,#
    	// -> 1,#,#
    	// -> #
    	
    	// Actual Work:
    	// stack = [ 1, 2, 4, null
    	// null - 4 - 6
    	// pop() 2 times, push 6
    	// It means left subtree of node 4 is done
    	// and the root-right link is connected,
    	// so we don't need reference of node 4.
    	//
    	// stack = [ 1, 2, 6
    	// stack = [ 1, 2, 6, null
    	// see # -> null - 6 - null -> pop() 2 times, push null
    	// It means left subtree of node 2 is done.
    	//
    	// stack = [ 1, 2, null
    	// null - 2 - 5
    	// It means left subtree of node 2 is done
    	// and the root-right (2-5) link is connected,
    	// so we don't need reference of node 2.
    	//
    	// stack = [ 1, 5,
    	// stack = [ 1, 5, null
    	// see # -> null - 5 - null -> pop() 2 times, push null
    	// It means left subtree of node 1 is done.
    	//
    	// stack = [ 1, null
    	// see # -> pop() 2 times, push null
    	//
    	// stack = [ null,
    	// Done.
    	
        String[] s = data.split(",");
        if(s[0].equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        
        int ptr = 1;
        while(ptr < s.length){
            if(s[ptr].equals("#")){
                if(stack.peek() == null){
                    stack.pop(); // pop #
                    stack.pop(); // pop root
                    stack.push(null); // push #, means the subtree is done
                }else{
                    stack.push(null);
                }
            }else{
                TreeNode tmp = new TreeNode(Integer.parseInt(s[ptr]));
                if(stack.peek() == null){
                    stack.pop(); // pop done left subtree
                    stack.pop().right = tmp; // pop root
                    stack.push(tmp);
                }else{
                    stack.peek().left = tmp;
                    stack.push(tmp);
                }
            }
            ptr++;
        }
        return root;
    }
}
