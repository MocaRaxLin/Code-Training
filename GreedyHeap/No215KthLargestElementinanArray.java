package GreedyHeap;

import java.util.PriorityQueue;

import Util.Parser;

public class No215KthLargestElementinanArray {

	public static void main(String[] args) {
		No215KthLargestElementinanArray sol = new No215KthLargestElementinanArray();
		Parser parser = new Parser();
		String t = "[3,2,1,5,6,4]\n" + 
				"2\n" + 
				"[3,2,3,1,2,4,5,5,6]\n" + 
				"4";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] A = parser.parseArray(s[i]);
			int k = Integer.parseInt(s[i+1]);
			int ans = sol.findKthLargest(A, k);
			System.out.println(ans);
		}
	}

	
	
	
	public int findKthLargest(int[] A, int k) {
        // --> time = average O(n), where n = A.length
        
        // Intuition:
        // Select with quick sort
        // Idea: T(n) = T(n/2) + O(n) --> Θ(n)
        // Worst: T(n) = T(n-1) + O(n) --> Θ(n^2) Ha! Ha!
        
        // find k-th largest -> get A[n-k] after sorted.
        
        int n = A.length;
        return quickSelect(A, 0, n-1, n-k);
    }
    
    private int quickSelect(int[] A, int s, int e, int t){
        int i = s, j = s;
        while(j < e){
            if(A[j] < A[e]) swap(A, i++, j);
            j++;
        }
        swap(A, i, e);
        if(i == t) return A[i];
        else if(i < t) return quickSelect(A, i+1, e, t);
        else return quickSelect(A, s, i-1, t);
    }
    
    private void swap(int[] A, int i, int j){
        int tmp = A[i]; A[i] = A[j]; A[j] = tmp;
    }
    
	
	
	public int findKthLargest0(int[] A, int k) {
        // --> time = O(nlogk)
        
        // Intuition:
        // Use min heap to keep track of first k largest numbers
        
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int e: A){
            if(pq.size() == k && e <= pq.peek()) continue;
            if(pq.size() == k) pq.poll();
            pq.offer(e);
        }
        return pq.peek();
    }
}
