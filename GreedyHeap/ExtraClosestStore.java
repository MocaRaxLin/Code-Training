package GreedyHeap;

import java.util.Arrays;

import Util.Parser;

public class ExtraClosestStore {

	public static void main(String[] args) {
		ExtraClosestStore sol = new ExtraClosestStore();
		Parser parser = new Parser();
		
		String t = "[3,5]\n"
				+ "[6,2,4]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[] stores = parser.parseArray(s[i]);
			int[] houses = parser.parseArray(s[i+1]);
			int[] ans = sol.findNearestStore(stores, houses);
			System.out.println(Arrays.toString(ans));
		}
	}

	private int[] findNearestStore(int[] stores, int[] houses) {
		Arrays.sort(stores);
		int[] ret = new int[houses.length];
		for(int i = 0; i < houses.length; i++) {
			int idx = binarySearch(stores, houses[i]);
			if(idx == stores.length) ret[i] = stores[idx-1];
			else if(idx == 0) ret[i] = stores[idx];
			else if(stores[idx] - houses[i] < houses[i] - stores[idx-1]) ret[i] = stores[idx];
			else ret[i] = stores[idx-1];
		}
		return ret;
	}
	
	private int binarySearch(int[] A, int t) {
		int i = 0, j = A.length;
		while(i < j) {
			int m = i + (j-i)/2;
			if(A[m] < t) i = m+1;
			else j = m;
		}
		return i;
	}
	
}
