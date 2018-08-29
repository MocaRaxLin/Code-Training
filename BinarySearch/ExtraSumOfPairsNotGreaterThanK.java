package BinarySearch;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import Util.Parser;
import Util.Show;

public class ExtraSumOfPairsNotGreaterThanK {

	public static void main(String[] args) {
		ExtraSumOfPairsNotGreaterThanK sol = new ExtraSumOfPairsNotGreaterThanK();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[-1,3,1,3,2,5]\n" + 
				"[8,8,0,6,-4,7]\n" + 
				"9\n" + 
				"[3,9,8,8,1]\n" + 
				"[0,5,2,10]\n" + 
				"8\n" + 
				"[0,3]\n" + 
				"[0,0]\n" + 
				"1\n" + 
				"[1]\n" + 
				"[1]\n" + 
				"0";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=3) {
			int[] A = parser.parseArray(s[i]);
			int[] B = parser.parseArray(s[i+1]);
			int k = Integer.parseInt(s[i+2]);
			int[][] ans = sol.findPairsofSumNotGreaterThanK(A, B, k);
			show.showMatrix(ans);
		}
	}

	private int[][] findPairsofSumNotGreaterThanK(int[] A, int[] B, int k) {
		int len = Math.min(A.length, B.length); if(len == 0) return new int[0][];
		
		Arrays.sort(A);
		int last = A[0];
		int count = 1;
		int sizeA = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 1; i < A.length; i++) {
			if(last == A[i]) count++;
			else {
				map.put(last, count);
				A[sizeA++] = last;
				last = A[i];
				count = 1;
			}
		}
		map.put(last, count);
		A[sizeA++] = last;
		
		// store pairs
		int[][] ret = new int[len][];
		int size = 0;
		int maxSum = 0;
		
		// use binary search rather than tree map
		for(int e: B) {
			int t = k - e;
			int idx = getFloorIdx(A, sizeA, 0, sizeA, t);
			if(idx == -1 || A[idx] + e < maxSum) continue;
			int freq = map.get(A[idx]);
			if(A[idx] + e > maxSum) {
				size = 0;
				while(freq-->0) ret[size++] = new int[] {A[idx], e};
				maxSum = A[idx] + e;
			}else if(maxSum == A[idx] + e){
				while(freq-->0) ret[size++] = new int[] {A[idx], e};
			}	
		}
		return Arrays.copyOfRange(ret, 0, size);
	}

	private int getFloorIdx(int[] A, int n, int i, int j, int t) {
		while(i < j) {
			int m = i + (j-i)/2;
			if(A[m] < t) i = m+1;
			else j = m;
		}
		if(i < n && A[i] == t) return i;
		else return i-1; // if floor does not exist, then return -1 
	}

	private int[][] findPairsofSumNotGreaterThanK0(int[] A, int[] B, int k) {
		int len = Math.min(A.length, B.length); if(len == 0) return new int[0][];
		
		// -> O(nlogn), where n = A.length
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(int e: A) {
			int freq = 0;
			if(map.containsKey(e)) freq = map.get(e);
			map.put(e, ++freq);
		}
		
		// store pairs
		int[][] ret = new int[len][];
		int size = 0;
		int maxSum = 0;
		
		// -> Ω(mlogn), O(mn)
		// we have upper bound O(nm), because there might be a case with A = [1,1,1,1,1,1,1,1,1,1]
		// This lead to return array have to add 10 times which is A.length.
		
		for(int e: B) {
			int t = k - e;
			Entry<Integer, Integer> entry = map.floorEntry(t);
			if(entry == null || entry.getKey() + e < maxSum) continue;
			int key = entry.getKey();
			int freq = entry.getValue();
			if(key + e > maxSum) {
				size = 0;
				while(freq-->0) ret[size++] = new int[] {key, e};
				maxSum = key + e;
			}else if(key + e == maxSum) {
				while(freq-->0) ret[size++] = new int[] {key, e};
			}
		}
		return Arrays.copyOfRange(ret, 0, size);
	}

}
