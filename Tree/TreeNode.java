package Tree;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Util.Parser;

public class TreeNode {
	// Binary Tree Node
	
	int val;
	TreeNode parent;
	TreeNode left;
	TreeNode right;
	TreeNode(){ val = 0; }
	TreeNode(int x) { val = x; }
	
	public TreeNode array2Tree(Integer[] ints) {
		if(ints.length == 0) return null;
		TreeNode root = new TreeNode(ints[0]);
		// System.out.println(Arrays.toString(ints));
		int next = 1;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(queue.size() > 0 && next < ints.length) {
			TreeNode tmp = queue.poll();
			Integer left = ints[next];
			next++;
			if(left != null) {
				tmp.left = new TreeNode(left);
				queue.offer(tmp.left);
			}
			if(next == ints.length) break;
			Integer right = ints[next];
			next++;
			if(right != null) {
				tmp.right = new TreeNode(right);
				queue.offer(tmp.right);
			}
		}
		return root;
	}
	
	@SuppressWarnings("deprecation")
	public Integer[] parseTreeInts(String line) {
		Parser parser = new Parser();
		String[] s = parser.parseArrayStr(line);
		//System.out.println(Arrays.toString(s));
		//System.out.println(s.length);
		Integer[] ret = new Integer[s.length];
		for(int i = 0; i < ret.length; i++) {
			if(s[i].equals("null")) continue;
			ret[i] = new Integer(s[i]);
		}
		return ret;
	}
	
	public TreeNode getNodeById(TreeNode root, int id) {
		if(root == null) return null;
		if(root.val == id) return root;
		TreeNode inLeft = getNodeById(root.left, id);
		if(inLeft != null) return inLeft;
		else return getNodeById(root.right, id);
		
	}
	
	public void showTree(TreeNode root, boolean nexLine) {
		// preorder traversal
		List<TreeNode> list = new LinkedList<TreeNode>();
		preorderAddNodes(list, root);
		System.out.print("[");
		Iterator<TreeNode> i = list.iterator();
		while(i.hasNext()) System.out.print(i.next().val +",");
		System.out.print("]");
		if(nexLine) System.out.println();
	}
	
	public void showTree(TreeNode root) {
		// preorder traversal
		List<TreeNode> list = new LinkedList<TreeNode>();
		preorderAddNodes(list, root);
		System.out.print("[");
		Iterator<TreeNode> i = list.iterator();
		while(i.hasNext()) System.out.print(i.next().val +",");
		System.out.print("]\n");
	}
	
	public void showTree(TreeNode root, String order) {
		// preorder traversal - pre - default
		// in-order - in
		// post-order = post
		List<TreeNode> list = new LinkedList<TreeNode>();
		if(order.equals("in")) {
			
		}else if(order.equals("post")) {
			
		}else {
			preorderAddNodes(list, root);
		}
		Iterator<TreeNode> i = list.iterator();
		System.out.print("[");
		while(i.hasNext()) System.out.print(i.next().val +",");
		System.out.print("]\n");
	}
	
	public void showTreeList(List<TreeNode> treeList) {
		Iterator<TreeNode> i = treeList.iterator();
		System.out.print("[");
		while(i.hasNext()) {
			showTree(i.next(), false);
			System.out.print(", ");
		}
		System.out.print("]\n");
	}
	
	private void preorderAddNodes(List<TreeNode> list, TreeNode root){
		if(root == null) return;
		list.add(root);
		preorderAddNodes(list, root.left);
		preorderAddNodes(list, root.right);
	}
	
	// Encodes a tree to a single string -- LeetCode
    public String serialize(TreeNode root) {
        Stack<Integer> stack = new Stack<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(queue.size() > 0){
            TreeNode ptr = queue.poll();
            if(ptr == null){
                stack.push(null);
            }else{
                stack.push(ptr.val);
                queue.offer(ptr.left);
                queue.offer(ptr.right);
            }
        }
        while(stack.size() > 0 && stack.peek() == null) stack.pop();
        
        StringBuilder sb = new StringBuilder();
        if(stack.size() > 0) sb.append(stack.pop());
        while(stack.size() > 0){
            Integer cur = stack.pop();
            sb.insert(0, cur == null? "null,": String.valueOf(cur)+",");
        }
        sb.insert(0, "[");
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree -- LeetCode
    public TreeNode deserialize(String data) {
        if(data.length() == 2) return null;
        data = data.substring(1, data.length()-1);
        String[] s = data.split(",");
        if(s.length == 0) return null;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        int next = 1;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(queue.size() > 0 && next < s.length){
            TreeNode ptr = queue.poll();
            if(!s[next].equals("null")){
                ptr.left = new TreeNode(Integer.parseInt(s[next]));
                queue.offer(ptr.left);
            }
            next++;
            if(next == s.length) break;
            if(!s[next].equals("null")){
                ptr.right = new TreeNode(Integer.parseInt(s[next]));
                queue.offer(ptr.right);
            }
            next++;
        }
        return root;
    }
}
