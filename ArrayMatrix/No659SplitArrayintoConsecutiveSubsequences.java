package ArrayMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import Util.Parser;

public class No659SplitArrayintoConsecutiveSubsequences {

	public static void main(String[] args) {
		No659SplitArrayintoConsecutiveSubsequences sol = new No659SplitArrayintoConsecutiveSubsequences();
		Parser parser = new Parser();
		String t = "[1,2,3,3,4,5]\n" + 
				"[1,2,3,3,4,4,5,5]\n" + 
				"[1,2,3,4,4,5]\n" + 
				"[1]\n" + 
				"[1,1,1,2,3,4]\n" + 
				"[1,2,3,4,6,7,8,9,10,11]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			boolean ans = sol.isPossible(A);
			System.out.println(ans);
		}
	}
	
	public boolean isPossible(int[] nums) {
        // --> O(n), where n = A.length
        
        // Intution:
        // DP
        // Thanks to:
        // https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence
        
        if(nums.length < 3) return false;
        int pre = Integer.MIN_VALUE, p1 = 0, p2 = 0, p3 = 0;
        int cur = 0, cnt = 0, c1 = 0, c2 = 0, c3 = 0;
        
        for (int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3) {
            for (cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; cnt++, i++);
            if (cur != pre + 1) {
                if (p1 != 0 || p2 != 0) return false;
                c1 = cnt; c2 = 0; c3 = 0;
            } else {
                if (cnt < p1 + p2) return false;
                c1 = Math.max(0, cnt - (p1 + p2 + p3));
                c2 = p1;
                c3 = p2 + Math.min(p3, cnt - (p1 + p2));
            }
        }
        return (p1 == 0 && p2 == 0);
    }
	

	public boolean isPossiblePq(int[] A) {
        // --> O(nlogn), where n = A.length
        // Such slower ??
        
        // Intuition:
        // insert e = A[i] into the shortest available sequence.
        // check all sequences are at least length of 3.
        
        if(A.length < 3) return false;
        // map stores lengthes of sub sequences end at key
        // eg. 1,2,3,4,5 and 3,4,5 -> (5, [3, 5])
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<Integer, PriorityQueue<Integer>>();
        for(int e: A){
            int len = 0;
            if(map.containsKey(e-1)){
                PriorityQueue<Integer> pq = map.get(e-1);
                len = pq.poll();
                if(pq.size() == 0) map.remove(e-1);
            }
            PriorityQueue<Integer> pq_e = map.getOrDefault(e, new PriorityQueue<Integer>());
            pq_e.offer(++len);
            map.put(e, pq_e);
        }
        
        for(PriorityQueue<Integer> pq: map.values()){
            while(pq.size() > 0){
                if(pq.poll() < 3) return false;
            }
        }
        return true;
    }
	
	public boolean isPossibleDPHashMap(int[] A) {
        // --> O(n), where n = A.length
		// But actually pretty slow, damn.
		// Too many hash map operations.
        
        // Thanks to :
        // https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106514/Python-esay-understand-solution
        
        if(A.length < 3) return false;
        Map<Integer, Integer> left = new HashMap<Integer, Integer>();
        Map<Integer, Integer> end = new HashMap<Integer, Integer>();
        for(int e: A){
            int freq = left.getOrDefault(e, 0);
            left.put(e, ++freq);
        }
        for(int e: A){
            if(!left.containsKey(e)) continue;
            
            int f1 = left.get(e);
            if(--f1 == 0) left.remove(e);
            else left.put(e, f1);
            
            if(end.containsKey(e-1)){
                int f2 = end.get(e-1);
                if(--f2 == 0) end.remove(e-1);
                else end.put(e-1, f2);
                
                f2 = end.getOrDefault(e, 0);
                end.put(e, ++f2);
            }else if(left.containsKey(e+1) && left.containsKey(e+2)){
                int f3 = left.get(e+1);
                if(--f3 == 0) left.remove(e+1);
                else left.put(e+1, f3);
                
                f3 = left.get(e+2);
                if(--f3 == 0) left.remove(e+2);
                else left.put(e+2, f3);
                
                f3 = end.getOrDefault(e+2, 0);
                end.put(e+2, ++f3);
            }else{
                return false;
            }
        }
        return true;
    }
}
