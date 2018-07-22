package GreedyHeap;

import java.util.ArrayList;
import java.util.List;

import Util.Parser;
import Util.Show;

public class ExtraHeap {

	public static void main(String[] args) {
		ExtraHeap heap = new ExtraHeap();
		Parser parser = new Parser();
		Show show = new Show();
		
		String t = "[16,4,10,14,7,9,3,2,8,1]";
		int[] a = parser.parseArray(t);
		List<Integer> A = new ArrayList<Integer>();
		for(int e: a) A.add(e);
		
		// Build Heap
		heap.buildMinHeap(A);
		show.showListInt(A, true);
		
		// Extract Minimum
		int min = heap.extractMin(A);
		System.out.println("min: "+min);
		show.showListInt(A, true);
		
		// Insert Integer
		heap.insertInt(A, 11);
		show.showListInt(A, true);
		heap.insertInt(A, 2);
		show.showListInt(A, true);
		
		// Remove value of index i
		int v1 = heap.removeIdxOf(A, 2);
		System.out.println("1st removed value: " + v1);
		show.showListInt(A, true);
		int v2 = heap.removeIdxOf(A, 1);
		System.out.println("2nd removed value: " + v2);
		show.showListInt(A, true);
		
		// Heap sort
		int[] sortedA = heap.heapSort(A);
		show.showArray(sortedA);
	}

	private int[] heapSort(List<Integer> A) {
		// --> time = O(nlgn)
		int[] ret = new int[A.size()];
		for(int i = 0; i < ret.length; i++) ret[i] = extractMin(A);
		return ret;
	}

	private int removeIdxOf(List<Integer> A, int i) {
		// --> O(lgn)
		// This function is similar to extractMin(),
		// but extend to random index i
		if(i >= A.size()) return Integer.MAX_VALUE;
		int ret = A.get(i);
		int end = A.size() - 1;
		A.set(i, A.get(end));
		A.remove(end);
		minHeapify(A, i);
		return ret;
	}

	private void insertInt(List<Integer> A, int e) {
		// --> time = O(lgn)
		
		A.add(e);
		int i = A.size() - 1;
		// run until we hit root of index 0.
		while( i > 0 && A.get(p(i)) > A.get(i)) {
			// if my parent node's value is greater than me then we swap.
			// Float up operation.
			int p = p(i);
			int tmp = A.get(p);
			A.set(p, A.get(i));
			A.set(i, tmp);
			i = p;
		}
	}

	private int extractMin(List<Integer> A) {
		// --> time = O(logn)
		
		if(A.size() == 0) return Integer.MAX_VALUE;
		int ret = A.get(0);
		int end = A.size() - 1;
		A.set(0,  A.get(end));
		A.remove(end);
		minHeapify(A, 0);
		return ret;
	}

	private void buildMinHeap(List<Integer> A) {
		// --> time = O(n)
		
		// Intuitively we will think each node run a minHeapify(),
		// so the time complexity is O(nlgn).
		// It is true, but not tight!
		
		// Think in this way.
		// Each nodes of height h takes time h to process minHeap().
		// From bottom up, (n/2) takes 1, (n/4) takes 2, (n/8) takes 3, ..., 1 takes lgn.
		// Let T = (n/2)*1 + (n/4)*2 + (n/8)*3 + (n/16)*4 + ... + lgn.              -> (1)
		//   T/2 =           (n/4)*1 + (n/8)*2 + (n/16)*3 + ... + (lgn-1) + (lgn/2) -> (2)
		// (1) - (2):
		//   T/2 = (n/2)*1 + (n/4)*1 + (n/8)*1 + (n/16)*1 + ... + (lgn-1) + (lgn/2).
		// Therefore T = O(n).
		
		// BOTTOM - UP
		int end = (A.size() - 2) / 2; // the last node with at least a child
		for(int i = end; i >= 0; i--)  minHeapify(A, i);
	}

	private void minHeapify(List<Integer> A, int i) {
		// --> time = O(logn)
		// TOP - DOWN
		// the maximum processing time is the height of heap A -> logn
		
		int smallest = i;
		int l = l(i);
		int r = r(i);
		if(l < A.size() && A.get(l) < A.get(smallest)) smallest = l;
		if(r < A.size() && A.get(r) < A.get(smallest)) smallest = r;
		if(smallest != i) {
			int tmp = A.get(i);
			A.set(i, A.get(smallest));
			A.set(smallest, tmp);
			minHeapify(A, smallest);
		}
	}
	
	private int p(int i) {
		//parent of i
		return (i-1)/2;
	}
	private int r(int i) {
		//right child of i
		return 2*i + 1;
	}
	private int l(int i) {
		//left child of i
		return 2*i + 2;
	}
	
	// ps. A short question:
	// Given 3 integers a, b, and c.
	// How to find out the median with minimum of comparison?
	//
	// My solution:
	// if(a > b) swap(a, b);
	// Now there are 3 possible positions for c to choose.
	// _ a _ b _ , c must be one of the blank.
	// if( c < a ) return a;
	// else if( b < c ) return b;
	// else return c;
	//
	// Therefore I think we must use at least 3 comparison.
}
