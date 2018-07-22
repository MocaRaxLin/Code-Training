package DP;

import Util.Parser;

public class ExtraKadane {

	public static void main(String[] args) {
		ExtraKadane sol = new ExtraKadane();
		Parser parser = new Parser();
		String t = "[-1,3,-2,1,6]\n"
				+ "[]\n"
				+ "[1,2,3,4,5,6,-10]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.kadane(A);
			System.out.println(ans);
		}
	}

    public int kadane(int[] A){
        // --> O(n), where n = A.length
        // To find the maximal sum of subarray
        
        // This is a variant of DP
        // maxEndHere[i] stores the maximal sum of the subarray which end at A[i].
        // maxEndHere[i] = MAX(0, maxEndHere[i-1]) + A[i]
        
        // Importatnt: The subarray has at least one element > 0.
        
    	if(A.length == 0) return 0;
        int[] maxEndHere = new int[A.length];
        maxEndHere[0] = A[0];
        int maxSoFar = A[0];
        for(int i = 1; i < A.length; i++){
        	// either use previous result or not
            maxEndHere[i] = Math.max(0, maxEndHere[i-1]) + A[i];
            maxSoFar = Math.max(maxSoFar, maxEndHere[i]);
        }
        return maxSoFar;
    }
}
