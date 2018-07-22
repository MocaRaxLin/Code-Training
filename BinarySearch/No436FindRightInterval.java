package BinarySearch;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import Util.Parser;
import Util.Show;

public class No436FindRightInterval {

	public static void main(String[] args) {
		No436FindRightInterval sol = new No436FindRightInterval();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[1,2]]\n" + 
				"[[1,2],[2,3],[3,4]]\n" + 
				"[[3,4],[0,1],[1,3],[2,3]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] M = parser.parseMatrix(s[i]);
			Interval[] intervals = new Interval[M.length];
			for(int j = 0; j < intervals.length; j++)
				intervals[j] = new Interval(M[j][0], M[j][1]);
			int[] ans = sol.findRightInterval(intervals);
			show.showArray(ans);
		}
	}

	private int[] findRightInterval(Interval[] intervals) {
		// --> time = O(n + startMAx-startMin)
		
		// 17 / 17 test cases passed.
		// Runtime: 3 ms
		
		// Bucket sort
		// store each indices by their start point
		// eg. [3,5], [0,1], [4,7], [2,5], [8,9]
		//      0      1      2      3      4
		// idxs = [-1,-1,-1,-1,-1,-1,-1,-1,-1] with length = 8 - 0 + 1 = 9
		//          0  1  2  3  4  5  6  7  8
		//      = [ 1,-1, 3, 0, 2,-1,-1,-1, 4]
		// So if an interval.end is 5 then its right interval is [8,9]
		// give this interval index 4 as its result
		// For conveinece 
		// idxs = [ 1, 3, 3, 0, 2, 4, 4, 4, 4]
		
		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int i = 0; i < intervals.length; i++){
            min = Math.min(min, intervals[i].start);
            max = Math.max(max, intervals[i].start);
        }
        int len = max - min + 1;
        int[] idxs = new int[len];
        Arrays.fill(idxs, -1);
        for(int i = 0; i < intervals.length; i++) idxs[intervals[i].start - min] = i;
        for(int i = idxs.length - 2; i >=0; i--){
            if(idxs[i] == -1) idxs[i] = idxs[i+1];
        }
        int[] ret = new int[intervals.length];
        for(int i = 0; i < ret.length; i++){
            if(max < intervals[i].end) ret[i] = -1;
            else ret[i] = idxs[intervals[i].end - min];
        }
        return ret;
	}

	public int[] findRightInterval0(Interval[] intervals) {
		// --> time = O(nlogn), space = O(n),
		// where n = intervals.length
		
		// 17 / 17 test cases passed.
		// Runtime: 29 ms
		
		// Build a BST map based on intervals' start -> O(nlogn)
		// Search closest start point by BST.ceiling(intervals[i].end) -> O(nlogn)
		
        if(intervals.length == 0) return new int[]{-1};
        TreeMap<Integer, Integer> rbt = new TreeMap<Integer, Integer>();
        for(int i = 0; i < intervals.length; i++) rbt.put(intervals[i].start, i);
        
        int[] ret = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++){
            Map.Entry<Integer, Integer> entry = rbt.ceilingEntry(intervals[i].end);
            if(entry == null) ret[i] = -1;
            else ret[i] = entry.getValue();
        }
        return ret;
    }
}

class Interval {
	int start;
	int end;
	Interval() { start = 0; end = 0; }
	Interval(int s, int e) { start = s; end = e; }
}