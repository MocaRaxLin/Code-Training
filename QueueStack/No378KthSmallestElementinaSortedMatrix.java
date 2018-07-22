package QueueStack;

import java.util.Comparator;
import java.util.PriorityQueue;

import Util.Parser;

public class No378KthSmallestElementinaSortedMatrix {

	public static void main(String[] args) {
		No378KthSmallestElementinaSortedMatrix sol = new No378KthSmallestElementinaSortedMatrix();
		Parser parser = new Parser();
		String t = "[[1,5,9],[10,11,13],[12,13,15]]\n" + 
				"8";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] M = parser.parseMatrix(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.kthSmallest(M, k);
			System.out.println(ans);
		}
	}

	public int kthSmallest(int[][] M, int k) {
        // --> time = O(klogk)
        // Using max heap
        
		// 85 / 85 test cases passed.
		// Runtime: 16 ms
		
        // Push every element to max heap
        // if heap's size == k, we check the k-th smallest element so far
        // if heap.max <= newElement then break both loops,
        // because there will not be any element smaller then heap.max in the next few iterataions.
        
        int m = M.length; if(m == 0) return 0;
        int n = M[0].length; if(n == 0) return 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>(){
            @Override
            public int compare(Integer i, Integer j){ return j - i; }
        });
        
        for(int i = 0; i < m; i++){
            if(pq.size() == k && pq.peek() <= M[i][0]) break;
            for(int j = 0; j < n ; j++){
                if(pq.size() == k && pq.peek() <= M[i][j]) break;
                if(pq.size() == k) pq.poll();
                pq.offer(M[i][j]);
            }
        }
        
        return pq.peek();
    }
	
	public int kthSmallest0(int[][] M, int k) {
        // --> time = O(klogm)
		// where n = # of column and m = # of rows
        
		// 85 / 85 test cases passed.
		// Runtime: 128 ms
		
		// M = [[ 1, 5, 9],  k = 8
		//      [10,11,13],
		//      [12,13,15]] 
		//
		// Put each first element of rows in to minHeap
		// minHeap = [1, 10, 12] -> O(mlogm)
		// Poll and offer k-1 time.
		// Poll 1 and push the next element 5
		// minHeap = [5,10,12]
		// ...
		// After that the min element in minHeap is the value we want.
		
		
        int m = M.length; if(m == 0) return 0;
        int n = M[0].length; if(n == 0) return 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            @Override
            public int compare(int[] i, int[] j){ return i[0] - j[0]; }
        });
        for(int i = 0; i < m; i++) pq.offer(new int[]{M[i][0], i});
        int[] ptr = new int[m];
        for(int i = 1; i < k; i++){ // poll 1 ~ (k-1) smallest elements
            int[] tmp = pq.poll(); // because 1 ≤ k ≤ n^2, pq.poll() is always valid
            ptr[tmp[1]]++;
            int row = tmp[1];
            int idx = ptr[tmp[1]];
            if(idx < n) pq.offer(new int[]{ M[row][idx], row });
        }
        return pq.peek()[0];
    }
}
