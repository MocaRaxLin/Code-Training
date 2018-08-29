package String;

import java.util.Arrays;
import java.util.Comparator;

import Util.Parser;

public class ExtraLogSort {

	public static void main(String[] args) {
		ExtraLogSort sol = new ExtraLogSort();
		Parser parser = new Parser();
		String t = "[\"a1 9 2 3 1\",\"a1c Act car\",\"zo4 4 7\",\"ab1 off KEY dog\",\"a1b act car\"]\n" + 
				"[\"mi2 jog mid pet\",\"wz3 34 54 398\",\"a1 alps cow bar\",\"x4 45 21 7\"]\n" + 
				"[\"t2 13 121 98\",\"r1 box ape bit\",\"b4 xi me nu\",\"b4 xi me nu 2\",\"br8 eat nim did\",\"w1 has uni gry\",\"f3 52 54 31\"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0 ; i < s.length; i++) {
			String[] logs = parser.parseArrayStr(s[i]);
			sol.sortLogs(logs);
			System.out.println(Arrays.toString(logs));
		}
	}

	private void sortLogs(String[] logs) {
//		Arrays.sort(logs, new Comparator<String>() {
//			@Override
//			public int compare(String a, String b) {
//				return compareTo(a, b);
//			}
//		});
		
//		quicksort(logs, 0, logs.length-1);
		mergeSort(logs, 0, logs.length);
	}
	
	private void mergeSort(String[] logs, int i, int j) {
		// --> O(nlogn), stable nlogn time, but use extra space of O(n)
		if(j - i < 2) return; // single element
		int m = i + (j-i)/2;
		mergeSort(logs, i, m);
		mergeSort(logs, m, j);
		String[] tmp = new String[j-i];
		int l = i, r = m, idx = 0;
		while(l < m && r < j) {
			if(compareTo(logs[l], logs[r]) < 0)  tmp[idx++] = logs[l++];
			else tmp[idx++] = logs[r++];
		}
		while(l < m) tmp[idx++] = logs[l++];
		while(r < j) tmp[idx++] = logs[r++];
		for(String s: tmp) logs[i++] = s;
	}

	private void quicksort(String[] logs, int h, int r) {
		// You can use ExtraSort.java in ArrayMatirx package
		// to refresh sorting algo.
		// --> O(nlogn) to O(n^2)
		if(h < r) {
			int i = h, j = h;
			while(j < r) {
				if(compareTo(logs[j], logs[r]) < 0) { // A[j] < A[r]
					swap(logs, i, j);
					i++;
				}
				j++;
			}
			swap(logs, i, r);
			quicksort(logs, h, i - 1);
			quicksort(logs, i+1, r);
		}
	}

	private void swap(String[] A, int i, int j) {
		String tmp = A[i];
		A[i] = A[j];
		A[j] = tmp;
	}
	
	private int compareTo(String a, String b) {
		// like codeOf(a) - codeOf(b)
		// return -1 or negative if a < b,
		// return 0 if a == b,
		// return 1 or positive, if a > b
		
		String a2 = a.split("\\s+")[1];
		String b2 = b.split("\\s+")[1];

		//System.out.println("["+a2+" : "+b2+"]");
		
		int n = Math.min(a2.length(), b2.length());
		int i = 0;
		for(; i < n; i++) {
			char ca = a2.charAt(i);
			char cb = b2.charAt(i);
			if(Character.isDigit(ca) && !Character.isDigit(cb)) return 1;
			else if(!Character.isDigit(ca) && Character.isDigit(cb)) return -1;
			else { // both digis or both non-digits
				int diff = ca - cb;
				if(diff != 0) return diff;
			}
		}
		
		if(i == a2.length() && i == b2.length()) return 0;
		else if(i == a2.length()) return -1;
		else return 1;
		
	}

}
