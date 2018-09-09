package ArrayMatrix;

import Util.Parser;

public class No769MaxChunksToMakeSorted {

	public static void main(String[] args) {
		No769MaxChunksToMakeSorted sol = new No769MaxChunksToMakeSorted();
		Parser parser = new Parser();
		String t = "[4,3,2,1,0]\n" + 
				"[1,2,4,3,0]\n" + 
				"[0,8,3,6,5,7,1,2,4]\n" + 
				"[3,2,1,0,6,4,5]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.maxChunksToSorted(A);
			System.out.println(ans);
		}
	}

	public int maxChunksToSorted(int[] A) {
		// --> O(n), where n = A.length
		
		// Intuition:
		// We run through the given array,
		// if the cur max number == cur index, 
		// then we find a chunk.
		
		
        int curMax = 0;
        int ret = 0;
        for(int i = 0; i < A.length; i++){
            curMax = A[i] > curMax? A[i]: curMax;
            if(i == curMax) ret++;
        }
        return ret;
    }
}
