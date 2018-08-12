package HashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class No128LongestConsecutiveSequence {

	public static void main(String[] args) {
		No128LongestConsecutiveSequence sol = new No128LongestConsecutiveSequence();
		Parser parser = new Parser();
		String t = "[100,4,200,1,3,2]\n" + 
				"[4,4,2,3,0,7,8,7,2,7,21,43,-1]\n" + 
				"[0,0,3,3,5,5,4,-1]\n"+
				"[0,-1]\n" + 
				"[1,2,0,1]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int ans = sol.longestConsecutive(A);
			System.out.println(ans);
		}
	}
	
    public int longestConsecutive(int[] A) {
        // --> O(n), where n = A.length
        
    	// eg. [0,  7]  8  [  9, 12]
    	//      h  e-1  e   e+1   t
    	// merge two intervals if e is the link,
    	// or extend the interval by [h, e] or [e, t]
    	
    	// ps. there is no possible that e is in one of intervals but never used before,
    	//     so our h <= e-1 < e < e+1 <= t is guaranteed.
    	
        if(A.length == 0) return 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int e: A){
            if(map.containsKey(e)) continue; // duplicate element
            Integer h = map.get(e-1); // [h, e-1]
            Integer t = map.get(e+1); // [e+1, t]
            int head = h == null? e: h;
            int tail = t == null? e: t;
            map.put(e,e); // if link two intervals, we still need to record this e as an used element.
            map.put(head, tail);
            map.put(tail, head);
            maxLen = Math.max(maxLen, tail - head + 1);
        }
        return maxLen;
    }
    
    public int longestConsecutive0(int[] A) {

    	// Alternative:
    	// Though it is O(nlogn) method, it is still fast.
    	// Sort A and then scan from head to tail
    	// if A[i-1] + 1 = A[i], then counter++;
    	// else update max length by counter and reset counter.
    	
    	if(A.length < 2) return A.length;
        Arrays.sort(A);
    	int maxLen = 0;
    	int counter = 1;
    	for(int i = 1; i < A.length; i++) {
    		if(A[i-1] + 1 == A[i]) counter++;
            else if(A[i-1] == A[i]) continue;
    		else {
    			maxLen = maxLen < counter? counter: maxLen;
    			counter = 1;
    		}
    	}
    	return maxLen < counter? counter: maxLen;
    }
}
