package ArrayMatrix;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Util.Parser;
import Util.Show;

public class ExtraSort {

	public static void main(String[] args) {
		Parser parser = new Parser();
		Show show = new Show();
		ExtraSort sort = new ExtraSort();
		
		//String t = "[16,4,10,14,7,9,3,2,8,1]";
		//String t = "[13,19,9,5,12,8,7,4,21,2,6,11]";
		//String t = "[2,8,7,1,3,5,6,4]";
		//String t = "[4,3,2,1]";
		String t = "[329,457,657,839,436,720,355]";
		
		int[] A = parser.parseArray(t);
		//sort.selectionSort(A);
		//sort.insertionSort(A);
		//sort.quickSort(A, 0, A.length - 1);
		//sort.countingSort(A);
		sort.mergeSort(A);
		show.showArray(A);
		
	}

	private void mergeSort(int[] A) {
		// --> time = O(nlgn)
		// divide and conquer
		Queue<List<Integer>> queue = new LinkedList<List<Integer>>();
		for(int i = 0; i < A.length; i++) {
			List<Integer> list = new LinkedList<Integer>();
			list.add(A[i]);
			queue.offer(list);
		}
		while(queue.size() > 1) {
			List<Integer> a = queue.poll();
			List<Integer> b = queue.poll();
			List<Integer> c = new LinkedList<Integer>();
			while(a.size() > 0 && b.size() > 0) {
				List<Integer> small;
				if(a.get(0) < b.get(0)) small = a;
				else small = b;
				c.add(small.get(0));
				small.remove(0);
			}
			if(a.size() > 0) c.addAll(a);
			else if(b.size() > 0) c.addAll(b);
			queue.offer(c);
		}
		List<Integer> last = queue.poll();
		for(int i = 0; i < A. length; i++) A[i] = last.get(i);
	}

	private void countingSort(int[] A) {
		// --> time is linear time O(k+n),
		// where k is the range of A, n is A.length
		// Use scenario:
		// The range of the array is not too big.
		
		// Let's sort 0 to 29
		int[] count = new int[30];
		for(int i: A) count[i]++;
		int idx = 0;
		for(int i = 0; i < count.length; i++) {
			while(count[i] > 0) {
				A[idx] = i;
				idx++;
				count[i]--;
			}
		}
	}

	private void quickSort(int[] A, int p, int r) {
		// --> time = Θ(nlgn) in average, but runs Θ(n^2) in the worst case.
		// p = front, r = rear
		
		// in-place sorting by using divide and conquer
		// Best case: T(n) = 2T(n/2) + n
		// case 2 -> T(n) = Θ(nlgn)
		// Worst case: T(n) = T(n-1) + n
		// case X - > just Θ(n^2)
		
		// eg. sort A = [2,8,7,1,3,5,6,4]
		// [2, 8, 7, 1, 3, 5, 6, 4]
		//  p = i = j            r
		// [2, 8, 7, 1, 3, 5, 6, 4]
		//     i = j             r
		// [2, 8, 7, 1, 3, 5, 6, 4]
		//     i  j              r
		// [2, 8, 7, 1, 3, 5, 6, 4]
		//     i     j           r
		// [2, 1, 7, 8, 3, 5, 6, 4]
		// Swap A[i] with A[j], because A[j] < A[r], i = i + 1
		// [2, 1, 7, 8, 3, 5, 6, 4]
		//        i     j        r
		// [2, 1, 3, 8, 7, 5, 6, 4]
		// Swap A[i] with A[j], because A[j] < A[r], i = i + 1
		// ... continue to j = r-1
		// [2, 1, 3, 8, 7, 5, 6, 4]
		//           i        j  r
		// [[2, 1, 3], 4, [7, 5, 6, 8]]
        // Swap A[i] with A[r] in the end of divide,
		// and conquer the two parts A[p:i-1], A[i+1:r]
		
		if(p < r) {
			int i = p;
			for(int j = p; j < r; j++) {
				if(A[j] < A[r]) {
					swap(A, i, j);
					i++;
				}
			}
			swap(A, i, r);
			quickSort(A, p, i-1);
			quickSort(A, i+1, r);
		}
		
	}
	
	private void insertionSort(int[] A) {
		// --> time best O(n), worst O(n^2)
		// pick the card and insert to the previous sorted sequence
		for(int i = 1; i < A.length; i++) {
			int pick = A[i];
			int j = i-1;
			while(j >= 0 && A[j] > pick) { // always compare with the picked number
				A[j+1] = A[j];
				j--;
			}
			A[j+1] = pick;
		}
	}
	
	private void selectionSort(int[] A) {
		// --> time = O(n^2)
		// find the minimum is the array and pick it up...
		
		for(int i = 0; i < A.length; i++) {
			int min = A[i];
			int idx = i;
			for(int j = i+1; j < A.length; j++) {
				if(min > A[j]) {
					min = A[j];
					idx = j;
				}
			}
			swap(A, i, idx);
		}
	}
	
	private void swap(int[] A, int i, int j) {
		int tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
	
	// Master Theorem:
	// A fast way to analyze time complexity of recursive function.
	// T(n) = aT(n/b) + f(n), where a > 1, b > 1, f(n) is a positive function.
	// Let c = log(a)/log(b)
	
	// case 1. (only leaf)
	// if f(n) = O(n^(c-ε)) for some ε > 0,
	// then T(n) = Θ(n^c)
	
	// case 2. (all nodes) -> same n^c times one more logn
	// if f(n) = Θ(n^c * (logn)^k), where k >= 0, 
	// then T(n) = Θ(n^c * (logn)^(k+1) )
	
	// case 3. (internal nodes)
	// if f(n) = Ω(n^(c+ε)) for some ε > 0,
	// then T(n) = Θ(f(n))
	
	// ps. O is upper bound, Θ is exact time, Ω is lower bound.  
}
