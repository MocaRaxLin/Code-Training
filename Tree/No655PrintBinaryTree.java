package Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Util.Show;

public class No655PrintBinaryTree {

	public static void main(String[] args) {
		No655PrintBinaryTree sol = new No655PrintBinaryTree();
		TreeNode tn = new TreeNode();
		Show show = new Show();
		String t = "[1,2]\n" + 
				"[1,2,3,5,null,null,6,7]\n" + 
				"[1,2,3,4,5,6,7,8,9,null,null,null,null,10]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			List<List<String>> map = sol.printTree(root);
			show.showListList(map);
		}
	}

	public List<List<String>> printTree(TreeNode root) {
        // --> time = O(HW), H = height of tree = logn, W = 2^H - 1
        // --> time = O(nlogn)
        
        // Enlight by:
        // https://leetcode.com/problems/print-binary-tree/discuss/106273/Simple-Python-with-thorough-explanation
        
        // eg. H = 4, W = 2^4 - 1 = 15
        // .......1....... <- Level 0, insert at 0, 7 = W/2, next offset = 4 = 2^(H-2)
        // ...2.......3... <- Level 1, insert at [1, 7 - 4] [1, 7 + 4], next offset = 2 = 4/2
        // .4...5...6...7. <- Level 2
        // 8.9............ <- Level 3
        
        int H = getHeight(root);
        int W = (1 << H) - 1;
        
        String[][] matrix = new String[H][W];
        for(String[] array: matrix) Arrays.fill(array, "");
        
        int offset = 1 << (H-2);
        preorderDrawMap(matrix, root, 0, W/2, offset);
        List<List<String>> map = convertToMap(matrix, H, W);
        return map;
    }
    
    public List<List<String>> convertToMap(String[][] matrix, int h, int w){
        List<List<String>> map = new LinkedList<List<String>>();
        for(int i = 0; i < h; i++){
            List<String> list = new LinkedList<String>();
            for(int j = 0; j < w; j++) list.add(matrix[i][j]);
            map.add(list);
        }
        return map;
    }
    
    public void preorderDrawMap(String[][] matrix, TreeNode root, int r, int c, int offset){
        if(root == null || r == matrix.length) return;
        matrix[r][c] = String.valueOf(root.val);
        preorderDrawMap(matrix, root.left, r+1, c - offset, offset >> 1);
        preorderDrawMap(matrix, root.right, r+1, c + offset, offset >> 1);
    }
    
    public int getHeight(TreeNode root){
        return root == null? 0: Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
