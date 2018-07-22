package BinarySearch;

import java.util.Arrays;
import java.util.TreeSet;

import Util.Parser;

public class No475Heaters {

	public static void main(String[] args) {
		No475Heaters sol = new No475Heaters();
		Parser parser = new Parser();
		String t = "[1,2,3]\n" + 
				"[2]\n" + 
				"[4,8,1]\n" + 
				"[10,5,2,1]\n" + 
				"[1]\n" + 
				"[]\n" + 
				"[2,6,3,9,111,23,91]\n" + 
				"[31,3,90,200]\n" + 
				"[282475249,622650073,984943658,144108930,470211272,101027544,457850878,458777923]\n" + 
				"[823564440,115438165,784484492,74243042,114807987,137522503,441282327,16531729,823378840,143542612]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] houses = parser.parseArray(s[i]);
			int[] heaters = parser.parseArray(s[i+1]);
			int ans = sol.findRadius(houses, heaters);
			System.out.println(ans);
		}
	}

	public int findRadius(int[] houses, int[] heaters) {
        // --> time = O(nlogn + mlogm + (n+m) + (2n+m)) = O(nlogn+mlogm) 
        
		// 30 / 30 test cases passed.
        // Runtime: 12 ms
		
		// Intuition:
		// For each house[i] find the closest heater on the right side and the left side,
		// and pick the heater with the smaller distance.
		// Find the maximal distance in those smaller distances.
		
        int n = houses.length;
        int m = heaters.length;
        if(n == 0) return 0;
        if(m == 0) return Integer.MAX_VALUE;
        
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int[] nearestDis = new int[n];
        // nearest heaters at right
        int ptr = 0;
        for(int i = 0; i < n; i++){
            while(ptr < m && heaters[ptr] < houses[i]) ptr++;
            nearestDis[i] = ptr == m ? Integer.MAX_VALUE: heaters[ptr] - houses[i];
        }
        // nearest heaters at left
        int ret = Integer.MIN_VALUE;
        ptr = m-1;
        for(int i = n-1; i >= 0; i--){
            while(ptr >= 0 && houses[i] < heaters[ptr]) ptr--;
            int dis = ptr == -1 ? Integer.MAX_VALUE: houses[i] - heaters[ptr];
            nearestDis[i] = Math.min(nearestDis[i], dis);
            ret = Math.max(ret, nearestDis[i]);
        }
        
        return ret;
    }
	
	public int findRadius0(int[] houses, int[] heaters) {
        // --> O(mlogm + nlogm)
        
        // 30 / 30 test cases passed.
        // Runtime: 74 ms
        // Too slow
        
        if(houses.length == 0) return 0;
        if(heaters.length == 0) return Integer.MAX_VALUE;
        int n = houses.length;
        int m = heaters.length;
        
        TreeSet<Integer> rbt = new TreeSet<Integer>();
        for(int i = 0; i < m; i++) rbt.add(heaters[i]);
        int ret = 0;
        for(int i = 0; i < n; i++){
            Integer left = rbt.floor(houses[i]);
            Integer right = rbt.ceiling(houses[i]);
            int minDis = Integer.MAX_VALUE;
            if(left == null && right == null) break;
            else if(left == null) minDis = right - houses[i];
            else if(right == null) minDis = houses[i] - left;
            else minDis = Math.min(houses[i] - left, right - houses[i]);
            ret = Math.max(ret, minDis);
        }
        return ret;
    }
}
