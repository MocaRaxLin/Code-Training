package Tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class No508MostFrequentSubtreeSum {

	public static void main(String[] args) {
		No508MostFrequentSubtreeSum sol = new No508MostFrequentSubtreeSum();
		TreeNode tn = new TreeNode();
		String t = "[5,2,-3]\n" + 
				"[]\n" + 
				"[2,5,-5]";
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i++) {
			Integer[] ints = tn.parseTreeInts(s[i]);
			TreeNode root = tn.array2Tree(ints);
			int[] ans = sol.findFrequentTreeSum(root);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        postorder(root, map);
        return getMostFreq(map);
    }
    
    public int[] getMostFreq(Map<Integer, Integer> map){
        int[] ret = new int[treeSize];
        int maxFreq = 0;
        int size = 0;
        for(Map.Entry<Integer, Integer> pair: map.entrySet()){
            if(pair.getValue() > maxFreq){
                size = 0;
                ret[size++] = pair.getKey();
                maxFreq = pair.getValue();
            }else if(pair.getValue() == maxFreq){
                ret[size++] = pair.getKey();
            }
        }
        return Arrays.copyOfRange(ret, 0, size);
    }
    
    int treeSize = 0;
    public int postorder(TreeNode root, Map<Integer, Integer> map){
        if(root == null) return 0;
        int left = postorder(root.left, map);
        int right = postorder(root.right, map);
        int sum = left + root.val + right;
        int v = map.getOrDefault(sum, 0);
        map.put(sum, ++v);
        treeSize++;
        return sum;
    }
}
