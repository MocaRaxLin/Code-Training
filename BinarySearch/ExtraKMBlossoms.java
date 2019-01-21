package BinarySearch;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import Util.Parser;

public class ExtraKMBlossoms {

	public static void main(String[] args) {
		ExtraKMBlossoms sol = new ExtraKMBlossoms();
		Parser parser = new Parser();
		
		String t = "[1,2,7,6,4,3,5]\n"
				+ "2\n"
				+ "2\n"
				+ "[1,2,7,6,4,3,5]\n"
				+ "2\n"
				+ "1\n"
				+ "[1,2,7,6,4,3,5]\n"
				+ "8\n"
				+ "1\n"
				+ "[1,2,7,6,4,3,5]\n"
				+ "1\n"
				+ "2\n"
				+ "[16,1,4,6,10,5,3,12,13,14,15,8,9,7,2,11]\n"
				+ "3\n"
				+ "3\n"
				+ "[16,1,4,6,10,5,3,12,13,14,15,8,9,7,2,11]\n"
				+ "5\n"
				+ "3\n"
				+ "[16,1,4,6,10,5,3,12,13,14,15,8,9,7,2,11]\n"
				+ "3\n"
				+ "5\n"
				+ "[1,4,3,2,5]\n"
				+ "1\n"
				+ "3\n"
				+ "[1]\n"
				+ "1\n"
				+ "1";
		/*
		String t = "[1,2,7,6,4,3,5]\n"
				+ "2\n"
				+ "2\n"
				+ "[1,2,7,6,4,3,5]\n"
				+ "2\n"
				+ "1\n"
				+ "[1,2,7,6,4,3,5]\n"
				+ "1\n"
				+ "2\n"
				+ "[16,1,4,6,10,5,3,12,13,14,15,8,9,7,2,11]\n"
				+ "3\n"
				+ "3\n"
				+ "[16,1,4,6,10,5,3,12,13,14,15,8,9,7,2,11]\n"
				+ "5\n"
				+ "3\n"
				+ "[16,1,4,6,10,5,3,12,13,14,15,8,9,7,2,11]\n"
				+ "3\n"
				+ "5";
		*/
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] A = parser.parseArray(s[i]);
			int K = Integer.parseInt(s[i+1]);
			int M = Integer.parseInt(s[i+2]);
			int ans = sol.lastDay(A, K, M);
			System.out.println(ans);
		}
	}

	PriorityQueue<int[]> pq;
	private int lastDay(int[] A, int K, int M) {
		// System.out.println(Arrays.toString(A));
		// System.out.println("K = "+ K+", M =" + M);
		
		List<int[]> list = new ArrayList<int[]>();
		int N = A.length;
		int[] lastDay = new int[] {1, N, N};
		list.add(lastDay);
		pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) { return a[2] - b[2]; }
		});
		pq.add(lastDay);
		
		// showListInts(list);
		if(list.size() == M && pq.peek()[2] >= K) return N;
		
		for(int i = N-1; i >= 0; i--) {
			if(list.size() == 0) break;
			binarySplit(list, A[i], K);
			// System.out.println("A[i] = "+ A[i]);
			// showListInts(list);
			if(list.size() == M && pq.peek()[2] >= K) return i;
		}
		return -1;
	}

	private void binarySplit(List<int[]> list, int t, int K) {
		int i = 0, j = list.size();
		// System.out.println("i, j = "+i+","+j);
		// showListInts(list);
		while(i < j) {
			int m = i + (j-i)/2;
			// System.out.println("m ->" + m);
			int[] tmp = list.get(m);
			if(t < tmp[0]) j = m;
			else if(tmp[1] < t) i = m+1;
			else if(tmp[0] <= t && t <= tmp[1]){
				list.remove(m);
				pq.remove(tmp);
				int l_size = t - tmp[0], r_size = tmp[1] - t;
				if(r_size >= K) {
					int[] r = new int[] {t+1, tmp[1], r_size};
					list.add(m, r);
					pq.add(r);
				}
				if(l_size >= K) {
					int[] l = new int[] {tmp[0], t-1, l_size};
					list.add(m, l);
					pq.add(l);
				}
				break;
			}
		}
	}

	public void showListInts(List<int[]> list) {
		System.out.print("{");
		for(int j = 0; j < list.size(); j++) System.out.print("["+list.get(j)[0] + ","+list.get(j)[1]+ ","+list.get(j)[2]+"],");
		System.out.print("}\n");			
	}
	
}
