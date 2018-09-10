package HashMap;

import java.util.HashMap;
import java.util.Map;

import Util.Parser;

public class No560SubarraySumEqualsK {

	public static void main(String[] args) {
		No560SubarraySumEqualsK sol = new No560SubarraySumEqualsK();
		Parser parser = new Parser();
		String t = "[1,1,1]\n" + 
				"2\n" + 
				"[1,2,3]\n" + 
				"3";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.subarraySum(A, k);
			System.out.println(ans);
		}
	}

	public int subarraySum(int[] A, int k) {
		// -->O(n), where n = A.length
		
        // int[] preSum = new int[A.length];
        // preSum[0] = A[0];
        // for(int i = 1; i < A.length; i++) preSum[i] = A[i] + preSum[i-1];
        
		// Make a preSum array, preSum[j] = Sum(A[i]) for i = 0 to j.
		// SUM(A[i+1:j]) = preSum[j] - preSum[i].
		// Target: k = preSum[j] - preSum[i]
		
		// We run through all preSum array, 
		// For each preSum[j], we want to know if we meet key as (k - preSum[i]) before.
		// Thus we collect frequencies of keys we meet so far,
		// and accumulate the frequenies to var count.
		
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int count = 0;
        for(int j = 0; j < A.length; j++){
            preSum += A[j];
            int key = preSum - k;
            if(map.containsKey(key)) count+=map.get(key);
            int freq = 0;
            if(map.containsKey(preSum)) freq  = map.get(preSum);
            map.put(preSum, ++freq);
        }
        return count;
    }
}
