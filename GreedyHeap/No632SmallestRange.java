package GreedyHeap;

import java.util.PriorityQueue;

import Util.Show;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class No632SmallestRange {

	public static void main(String[] args) {
		No632SmallestRange sol = new No632SmallestRange();
		Show show = new Show();
		
//		Integer[][] numsA = new Integer[][] {
//			{4,10,15,24,26}, {0,9,12,20}, {5,18,22,30}
//		};
		Integer[][] numsA = new Integer[][] {
			{10, 10}, {11, 11}
		};
		List<List<Integer>> nums = new LinkedList<List<Integer>>();
		for(int i = 0; i < numsA.length; i++) {
			nums.add(Arrays.asList(numsA[i]));
		}
		int[] ans = sol.smallestRange(nums);
		show.showArray(ans);
	}
	
	

	public int[] smallestRange(List<List<Integer>> nums) {
		// --> O(nlogm), where n = total number of elements in all list, m = number of lists
		// usually n >> m
		
		// Eg.
		// [ 4, 10, 15, 24, 26]
		// [ 0,  9, 12, 20]
		// [ 5, 18, 22, 30]
		//
		// 1. store the first element into min heap
		// [0, 4, 5], min = 0, max = 5, minRange = 5
		// 
		// 2. poll the first and offer the next until we reach the end of one of lists
		// [4, 5, 9], min = 4, max = 9, minRange = 5
		// [5, 9, 10], min = 5, max = 10, minRange = 5
		// [9, 10, 18], min = 9, max = 18, minRange = 5
		// [10, 12, 18], min = 10, max = 18, minRange = 5
		// [12, 15, 18], min = 12, max = 18, minRange = 5
		// [15, 18, 20], min = 15, max = 20, minRange = 5
		// [18, 20, 24], min = 18, max = 24, minRange = 5
		// [20, 22, 24], min = 20, max = 24, minRange = 4 <-- new minRange, then store to return array
		// [22, 24] there is no next element after 20 in 2nd list
		// return the return array as answer :)
		
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        PriorityQueue<Tuple> minHeap = new PriorityQueue<Tuple>((i,j) -> i.value - j.value);
        //init heap -> O(mlogm)
        for(int i = 0; i < nums.size(); i++){
            int e = nums.get(i).get(0);
            min = Math.min(min, e);
            max = Math.max(max, e);
            minHeap.offer(new Tuple(e, i));
        }
        int minRange = max - min;
        int[] ret = new int[]{min, max};
        int[] pointer = new int[nums.size()]; // init all 0
        
        // poll-offer process -> O(nlogm)
        while(minHeap.size() != 0){
        	// manipulate heap first
            Tuple first = minHeap.poll();
            pointer[first.belong]++;
            if(pointer[first.belong] == nums.get(first.belong).size()) return ret;
            int e = nums.get(first.belong).get(pointer[first.belong]);
            minHeap.offer(new Tuple(e, first.belong));
            
            // update ret by minRange later
            min = minHeap.peek().value;
            max = Math.max(max, e);
            if(max - min < minRange){
                ret[0] = min;
                ret[1] = max;
                minRange = max - min;
            }
            
        }
        return ret;
    }
	
	
}

class Tuple{
    int value;
    int belong;
    public Tuple(int value, int belong){
        this.value = value;
        this.belong = belong;
    }
}