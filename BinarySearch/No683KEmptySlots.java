package BinarySearch;

import java.util.ArrayList;
import java.util.List;

import Util.Parser;

public class No683KEmptySlots {

	public static void main(String[] args) {
		No683KEmptySlots sol = new No683KEmptySlots();
		Parser p = new Parser();
		String t = "[1,3,2]\n" + 
				"1\n" + 
				"[1,2,3]\n" + 
				"0\n" + 
				"[2,5,6,1,4,3,7]\n" + 
				"1\n" + 
				"[2,5,6,1,4,3,7]\n" + 
				"2\n" + 
				"[2,5,6,1,4,3,7]\n" + 
				"3\n" + 
				"[9,2,3,1,4,10,7,6,5,8]\n" + 
				"10";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] flowers = p.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.kEmptySlots(flowers, k);
			System.out.println(ans);
		}
	}

	public int kEmptySlots(int[] flowers, int k){
        int[] days =  new int[flowers.length];
        for(int i = 0; i < flowers.length; i++) days[flowers[i] - 1] = i + 1;
        // System.out.println(Arrays.toString(days));
        int left = 0, right = k + 1, res = Integer.MAX_VALUE;
        for(int i = 0; right < days.length; i++){
            if(days[i] < days[left] || days[i] <= days[right]){
                if(i == right) res = Math.min(res, Math.max(days[left], days[right]));   //we get a valid subarray
                // detect invalid
                left = i; 
                right = k + 1 + i;
            }
        }
        return res == Integer.MAX_VALUE? -1: res;
    }
	
	public int kEmptySlots0(int[] flowers, int k) {
		// O(nlogn)
		
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i < flowers.length; i++){
            int[] dis = binaryInsert(list, flowers[i] - 1);
            // System.out.println(Arrays.toString(list.toArray()));
            // System.out.println(largestDis);
            if(dis[0] == k || dis[1] == k) return i+1;
        }
        return -1;
    }
    
    private int[] binaryInsert(List<Integer> list, int t){
        int i = 0, j = list.size();
        while(i < j){
            int m = i + (j-i)/2;
            if(list.get(m) < t) i = m+1;
            else j = m;
        }
        list.add(i, t);
        int left = i == 0? -1: list.get(i) - list.get(i-1) - 1;
        int right = i == list.size() - 1? -1: list.get(i+1) - list.get(i) - 1;
        return new int[]{left, right};
    }
}
