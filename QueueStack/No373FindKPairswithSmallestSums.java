package QueueStack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import Util.Parser;
import Util.Show;

public class No373FindKPairswithSmallestSums {

	public static void main(String[] args) {
		No373FindKPairswithSmallestSums sol = new No373FindKPairswithSmallestSums();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[1,7,11]\n" + 
				"[2,4,6]\n" + 
				"3\n" + 
				"[1,1,2]\n" + 
				"[1,2,3]\n" + 
				"2\n" + 
				"[1,2]\n" + 
				"[3]\n" + 
				"3\n" + 
				"[1]\n" + 
				"[1]\n" + 
				"1\n" + 
				"[1,1]\n" + 
				"[1,1]\n" + 
				"4\n" + 
				"[]\n" + 
				"[]\n" + 
				"0\n" + 
				"[1]\n" + 
				"[3,5,6,7,8,100]\n" + 
				"4\n" +
				"[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]\n" + 
				"[1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]\n" + 
				"5";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int k = Integer.parseInt(s[i+2]);
			List<int[]> ans = sol.kSmallestPairs(A, B, k);
			show.showListIntArray(ans);
		}
	}

	public List<int[]> kSmallestPairs(int[] A, int[] B, int k) {
		// --> time = O(klogk)
		// 27 / 27 test cases passed.
		// Status: Accepted
		// Runtime: 7 ms
		
        // Because A and B are increasing-ordered,
        // if maxHeap.max <= A[i] + A[j], we know there is no possible pair after i and j
        // Thus we will stop at pq.size() == k && pq.peek()[2] <= A[i] + B[j]
        
        // eg. A = [1,1,2] B = [1,2,3] k = 3
        // i = 0, j = 0
        // A[i] >= A[j] => heap = [[1,1], [1,1], [1,2]], j++;
        // 
        // A = [1,1,2] B = [2,3]
        // i = 0, j = 1
        // heap.max = sum([1,2]) = 3 = A[i] + B[j]
        // break
        // return heap
		
        if(A.length == 0 || B.length == 0 || k == 0) return new LinkedList<int[]>();
        int i = 0, j = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] p, int[] q) { return q[2] - p[2]; }
        });
        while(i < A.length && j < B.length){
            if(pq.size() == k && pq.peek()[2] <= A[i] + B[j]) break;
            if(A[i] < B[j]){
                for(int b = j; b < B.length; b++){
                    //System.out.println(i + ", " + b +", "+ pq.size());
                    if(pq.size() == k && pq.peek()[2] <= A[i]+B[b]) break;
                    if(pq.size() == k) pq.poll();
                    pq.offer(new int[]{A[i], B[b], A[i]+B[b]});
                }
                i++;
            }else{
                for(int a = i; a < A.length; a++){
                    if(pq.size() == k && pq.peek()[2] <= A[a]+B[j]) break;
                    if(pq.size() == k) pq.poll();
                    pq.offer(new int[]{A[a], B[j], A[a]+B[j]});
                }
                j++;
            }
        }
        
        List<int[]> ret = new LinkedList<int[]>();
        while(pq.size() > 0){
            int[] tmp = pq.poll();
            ret.add(0, new int[]{tmp[0], tmp[1]});
        }
        return ret;
    }
}
