package ArrayMatrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class No768MaxChunksToMakeSortedII {

	public static void main(String[] args) {
		No768MaxChunksToMakeSortedII sol = new No768MaxChunksToMakeSortedII();
		Parser parser = new Parser();
		String t = "[5,4,3,2,1]\n" + 
				"[2,1,3,4,4]\n" + 
				"[5,3,2,3,9,7,10]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.maxChunksToSorted(A);
			System.out.println(ans);
		}
	}

	
	public int maxChunksToSorted(int[] A) {
        // --> O(N), where N = A.length
        
        // Leetcode's fastest solution.
        // Very clever!
        
        // Intution:
        // Frame out the left(min) and right(max) of chuncks.
        // eg. A   = [5,3,2,3,9,7,10]
        //     max = [5,5,5,5,9,9,10] , scan forwards
        //     min = [2,2,2,3,7,7,10] , scan backwards
        //                  |   |  |    count = 3
        
        int N = A.length; if(N == 0) return 0;
        
        int[] max = new int[N];
        max[0] = A[0];
        for(int i = 1; i < N; i++) max[i] = Math.max(max[i-1], A[i]);
        
        int count = 0;
        int min = Integer.MAX_VALUE;
        for (int i = N - 1; i >= 0; i--) {
            if (max[i] <= min) count++;
            min = Math.min(min, A[i]);
        }
        return count;
    }
	
	
	public int maxChunksToSorted0(int[] A) {
		// --> O(n), where n = A.length
		
		// Intuition:
		// Thanks to hint, for index k, if A[0:k] is permutation of sorted(A[0:k]), we cut here.
		// computer permutation by hash map.
		//
		// Slow for calling hash map.
		
        if(A.length == 0) return 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] B = Arrays.copyOfRange(A, 0, A.length);
        Arrays.sort(B);
        // System.out.println(Arrays.toString(A));
        // System.out.println(Arrays.toString(B));
        
        for(int i = 0; i < A.length; i++){
            int a = map.getOrDefault(A[i], 0) + 1;
            if(a == 0) map.remove(A[i]);
            else map.put(A[i], a);
            int b = map.getOrDefault(B[i], 0) - 1;
            if(b == 0) map.remove(B[i]);
            else map.put(B[i], b);
            if(map.size() == 0) count++;
        }
        return count;
    }
}
