package ArrayMatrix;

import Util.Parser;

public class No334IncreasingTripletSubsequence {

	public static void main(String[] args) {
		No334IncreasingTripletSubsequence sol = new No334IncreasingTripletSubsequence();
		Parser parser = new Parser();
		String t = "[1,2,3,4,5]\n" + 
				"[1,6,3,7,5]\n" + 
				"[1,3,0,5]\n" + 
				"[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] nums = parser.parseArray(s[i]);
			boolean ans = sol.increasingTriplet(nums);
			System.out.println(ans);
		}
	}

	
	public boolean increasingTriplet(int[] nums) {
        // --> O(n)
        // Thanks to:
        // https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79004/Concise-Java-solution-with-comments.
        
        // the solution still works, because big only gets updated when there exists a small that comes before it.
        if(nums.length < 3) return false;
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for(int e: nums){
            if(e <= small) small = e;
            else if(e <= big) big = e;
            else return true;
        }
        return false;
    }
}
