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
				"[1,2,2,1]\n"+
				"[2,1,4,7,3,2,5]\n" + 
				"[2,2,2]\n" + 
				"[3,1,2]\n" + 
				"[2,3,3,1]\n" + 
				"[3,3,1,4,4,6,3,0,7]\n" + 
				"[1,2,3,4,3,2,1]";
		String[] input = testcase.split("\n");
		for(String s: input) {
			int[] A = parser.parseArray(s);
			int ans = sol.longestMountain(A);
			System.out.println(ans);
		}
	}
	
	public int longestMountain(int[] A) {
        // --> time = O(n), space = O(1), where n = A.length
        
        // Intuition:
        // state control, Very Stupid Q_Q
        
        if(A.length < 3) return 0;
        int len = 0, i = 0, j = 1;
        // -1 -> down, 0 -> flat, 1 -> up, 2 -> down after up.
        int state = 3;
        
        // find the first increasing point
        while(j < A.length && A[j-1] >= A[j]) j++;
        if(j == A.length) return 0;
        state = 1; i = j - 1;
        
        for(; j < A.length; j++){
            if(A[j-1] == A[j]){
                if(state == 2) len = Math.max(len, j - i);
                i = j;
                state = 0;
            }else if(A[j-1] < A[j]){
                if(state == 2){
                    len = Math.max(len, j - i);
                    i = j - 1;
                }else if(state == 0 || state == -1){
                    i = j - 1;
                }
                state = 1;
            }else{ // A[j-1] > A[j]
                if(state == 1 || state == 2){
                    state = 2;
                }else{
                    i = j;
                    state = -1;
                }
                
            }
        }
        if(state == 2) len = Math.max(len, j - i);
        return len;
    }
	
	public int longestMountain1(int[] A) {
        // --> O(n^2), where n = A.length
        
        // Fastest solution from Leetcode.
        // Center expend Weird HA HA.
        
        int ret = 0;
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                int sum = 1;
                int j = i - 1;
                while (j >= 0 && A[j] < A[j + 1]) {
                    sum++;
                    j--;
                }
                
                int k = i + 1;
                while (k < A.length && A[k - 1] > A[k]) {
                    sum++;
                    k++;
                }
                ret = Math.max(ret, sum);
                
            }
        }
        
        return ret;
    }
	
	public int longestMountain0(int[] A) {
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
