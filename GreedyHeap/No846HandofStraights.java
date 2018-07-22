package GreedyHeap;

import java.util.HashMap;
import java.util.PriorityQueue;

import Util.Parser;

public class No846HandofStraights {

	public static void main(String[] args) {
		No846HandofStraights sol = new No846HandofStraights();
		Parser parser = new Parser();
		String testcase = "[1,2,3,6,2,3,4,7,8]\n" + 
				"3\n" + 
				"[1,2,3,4,5]\n" + 
				"4\n" + 
				"[1]\n" + 
				"2\n" + 
				"[0,3,4,5]\n" + 
				"1";
		String[] s = testcase.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] hand = parser.parseArray(s[i]);
			int W = Integer.parseInt(s[i+1]);
			boolean ans = sol.isNStraightHand(hand, W);
			System.out.println(ans);
		}
	}
	
	public boolean isNStraightHand(int[] hand, int W) {
		// --> O(nlogn), where n = hand.length;
		
		// Intuition:
		// Find out all groups with consecutive number started from the smallest number.
		// Using minHeap a.k.a. priority queue to get minimum value in constant time.
		// Then build consecutive arrays based on this minimum value as A[0].
		// If we can successfully build n/W arrays, return true, else return false.
		
        if(hand.length % W != 0) return false;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        
        for(int e: hand){ // -> O(n + nlogn)
            if(!map.containsKey(e)) map.put(e, 0);
            int t = map.get(e) + 1;
            map.put(e, t);
            minHeap.offer(e);
        }
        
        int G = hand.length/W;
        for(int i = 0; i < G; i++){ // -> O(nlogn)
            int head = minHeap.poll();  // O(logn)
            int t = map.get(head) - 1;
            if(t == 0) map.remove(head);
            else map.put(head, t);
            for(int j = 1; j < W; j++){
                int ele = head + j;
                if(!map.containsKey(ele)) return false;
                t = map.get(ele);
                t--;
                if(t == 0) map.remove(ele);
                else map.put(ele, t);
                minHeap.remove(ele); // O(logn)
            }
        }
        return true;
    }
}
