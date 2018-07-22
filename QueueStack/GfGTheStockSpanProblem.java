package QueueStack;

import java.util.Arrays;

import Util.Parser;

public class GfGTheStockSpanProblem {

	public static void main(String[] args) {
		GfGTheStockSpanProblem sol = new GfGTheStockSpanProblem();
		Parser parser = new Parser();
		String t = "[100, 80, 60, 70, 60, 75, 85]\n"
				+ "[10, 4, 5, 90, 120, 80]\n"
				+ "[1,2,3,4,5]\n"
				+ "[4,3,2,1]\n"
				+ "[3,3,2,2,4,4]";
		t = t.replaceAll(" ", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[] A = parser.parseArray(s[i]);
			int[] ans = sol.calculateSpan(A);
			System.out.println(Arrays.toString(ans));
		}
	}

	public int[] calculateSpan(int[] A) {
		// --> time = O(n), where n = A.length
		
		// Intuition:
		// For span i end at A[i], we want to find the smallest index j,
		// such that j <= i and all A[j] to A[i] are <= A[i].
		// 
		// This implies that A[j-1] is the first element > A[i].
		// Therefore we use stack to store elements decreasingly.
		//
		// For each A[i] we check stack's top.
		// If top <= A[i], pop it until top > A[i], then the max span for A[i] = i - top
		// If the stack is empty, then A[i] = i - (-1) -> range of A[0:i]
		
		int[] ret = new int[A.length];
		int[] stack = new int[A.length]; // store index
		int size = 0;
		for(int i = 0; i < A.length; i++) {
			while(size > 0 && A[stack[size-1]] <= A[i]) size--;
			if(size == 0) ret[i] = i + 1;
			else ret[i] = i - stack[size-1];
			stack[size++] = i;
		}
		return ret;
	}

	
}
