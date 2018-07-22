package GreedyHeap;

import java.util.Arrays;

import Util.Parser;

public class No646MaximumLengthofPairChain {

	public static void main(String[] args) {
		No646MaximumLengthofPairChain sol = new No646MaximumLengthofPairChain();
		Parser parser = new Parser();
		
		String t = "[[1,2],[2,3],[3,4]]\n" + 
				"[[-10,-8],[8,9],[-5,0],[6,10],[-6,-4],[1,7],[9,10],[-4,7]]\n" + 
				"[]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] pairs = parser.parseMatrix(s[i]);
			int ans = sol.findLongestChain(pairs);
			System.out.println(ans);
		}
	}

    public int findLongestChain(int[][] pairs) {
    	// --> time = O(nlogn)
    	
    	// This problem is identical to Interval Scheduling Problem.
    	// ISP:
    	// Given a set of n requests, the i-th request start from s[i] and end at f[i],
    	// find the largest computable subset of these requests.
    	
    	// Greedy:
    	// Always consider the earliest finished request, and pick up the non-overlapping one.
    	// Sort these requests by finishing time f, and iterate all requests.
    	
        Arrays.sort(pairs, (i, j) -> i[1] - j[1]);
        int ret = 0;
        int last = Integer.MIN_VALUE;
        for(int[] interval: pairs){
            if(last < interval[0]){
                ret++;
                last = interval[1];
            }
        }
        return ret;
    }
}
