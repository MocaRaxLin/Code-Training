package ArrayMatrix;

import Util.Parser;

public class No845LongestMountaininArray {

	public static void main(String[] args) {
		No845LongestMountaininArray sol = new No845LongestMountaininArray();
		Parser parser = new Parser();
		
		String testcase = "[2,1,4,7,3,2,5]\n" + 
				"[0,1,2]\n" + 
				"[1]\n" + 
				"[1,0,2]\n" + 
				"[3,2,1]\n" + 
				"[1,2,1]\n" + 
				"[1,2,2,1]";
		String[] input = testcase.split("\n");
		for(String s: input) {
			int[] A = parser.parseArray(s);
			int ans = sol.longestMountain(A);
			System.out.println(ans);
		}
	}
	
	public int longestMountain(int[] A) {
		// --> time O(n), space O(n), where n = A.length
		
		// Intuition:
		// find the longest up hill on left and the longest down hill on right at position i.
		// if either left or right is zero, then mountain does not exist at position i.
		
        if(A.length < 3) return 0;
        int[] up = new int[A.length];
        int t = 0;
        for(int i = 1; i < A.length; i++){
            if(A[i-1] < A[i]) t++;
            else t = 0;
            up[i] = t;
        }
        int[] down = new int[A.length];
        t = 0;
        for(int i = A.length - 2; i >= 0; i--){
            if(A[i] > A[i+1]) t++;
            else t = 0;
            down[i] = t;
        }
        int ret = 0;
        for(int i = 1; i < A.length; i++){
            if(up[i] == 0 || down[i] == 0) continue;
            ret = Math.max(ret, 1 + up[i] + down[i]);
        }
        return ret;
    }
}
