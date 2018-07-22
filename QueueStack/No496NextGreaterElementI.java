package QueueStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import Util.Parser;

public class No496NextGreaterElementI {

	public static void main(String[] args) {
		No496NextGreaterElementI sol = new No496NextGreaterElementI();
		Parser parser = new Parser();
		String t = "[4,1,2]\n" + 
				"[1,3,4,2]\n" + 
				"[]\n" + 
				"[]\n" + 
				"[3,7]\n" + 
				"[4,3,5,-1,2,7]\n" + 
				"[0,3]\n" + 
				"[-2, 1, 3, 0, -1, 4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] nums1 = parser.parseArray(s[i]);
			int[] nums2 = parser.parseArray(s[i+1]);
			int[] ans = sol.nextGreaterElement(nums1, nums2);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // --> time = O(n), space = O(n), where n = nums2.length
        
        // eg. [5, 4, 3, 2, 1, 6]
        // All elements have 6 as their the next greater number.
        // Our stack will store all elements < 6 when we see 6.
        
        // Since there is no duplicates,
        // we can set key-value pair as <num, nextGtr> instead of <num, index>
        
        // Original Intuition:
        // eg. [-2, 1, 3, 0, -1, 4]
        //
        //   stack = [-2,
        //     idx = [ 0, ... see 1
        // nextGtr = [ 1, 3,
        // -2 < 1 -> stack pop, then push -2
        //
        //   stack = [ 1,
        //     idx = [ 1, ... see 3
        // nextGtr = [ 1, 3,
        //  1 < 3 -> stack pop, then push 3
        //
        //   stack = [ 3,
        //     idx = [ 2, ... see 0
        // nextGtr = [ 1, 3, ?,
        //  3 > 0 -> push 0
        //
        //   stack = [ 3, 0,
        //     idx = [ 2, 3, ... see -1
        // nextGtr = [ 1, 3, ?, ?
        //  0 > -1 -> push -1
        //
        //   stack = [ 3, 0,-1
        //     idx = [ 2, 3, 4 ... see 4
        // nextGtr = [ 1, 3, ?, ?, ?
        //  -1 < 4 -> pop -1, nextGtr[4] = 4
        //   0 < 4 -> pop  0, nextGtr[3] = 4
        //   3 < 4 -> pop  3, nextGtr[2] = 4 -> push 4
        //
        //   stack = [ 4
        //     idx = [ 5
        // nextGtr = [ 1, 3, 4, 4, 4, ?]
        // pop 4, nextGtr[5] = -1
        // nextGtr = [ 1, 3, 4, 4, 4,-1]
        
        Stack<Integer> stack = new Stack<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums2.length; i++){
            while(stack.size() > 0 && stack.peek() < nums2[i])
                map.put(stack.pop(), nums2[i]);
            stack.push(nums2[i]);
        }
        
        int[] ret = new int[nums1.length];
        for(int i = 0; i < ret.length; i++)
            ret[i] = map.getOrDefault(nums1[i], -1);
        return ret;
    }
}
