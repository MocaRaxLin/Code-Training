package Util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Show {
	// List
	public void showListList(List<List<String>> ans) {
		System.out.print("[");
		for(int i = 0; i < ans.size(); i++) {
			showList(ans.get(i), false);
			System.out.print(", ");
		}
		System.out.print("]\n");
	}

	public void showList(List<String> list, boolean nextLine) {
		System.out.print(Arrays.toString(list.toArray()));
		if(nextLine) System.out.print("\n");
	}

	public void showListArray(List<String[]> list) {
		System.out.print("[");
		for(int i = 0; i < list.size(); i++) {
			System.out.print(Arrays.toString(list.get(i)) + ",");
		}
		System.out.print("]\n");
	}
	
	public void showListListInt(List<List<Integer>> ans) {
		if(ans == null) return;
		System.out.print("[");
		for(int i = 0; i < ans.size(); i++) {
			showListInt(ans.get(i), false);
			System.out.print(", ");
		}
		System.out.print("]\n");
	}
	
	public void showListInt(List<Integer> list, boolean nextLine) {
		if(list == null) return;
		System.out.print(Arrays.toString(list.toArray()));
		if(nextLine) System.out.print("\n");
	}
	
	public void showListIntArray(List<int[]> list) {
		System.out.print("[");
		for(int i = 0; i < list.size(); i++) {
			System.out.print(Arrays.toString(list.get(i)) + ",");
		}
		System.out.print("]\n");
	}
	
	// Array
	public void showArray(int[] ans) {
		System.out.println(Arrays.toString(ans));
	}
	
	public void showMatrix(int[][] ans) {
		System.out.print("[");
		for(int i = 0; i < ans.length; i++)
			System.out.print(Arrays.toString(ans[i])+",");
		System.out.print("]\n");
	}
	
	public void showArray(String[] ans) {
		System.out.println(Arrays.toString(ans));
//		System.out.print("[");
//		for(String i: ans) System.out.print(i + ",");
//		System.out.print("]\n");
	}
	
	public void showMatrixStr(String[][] ans) {
		System.out.print("[");
		for(int i = 0; i < ans.length; i++)
			System.out.print(Arrays.toString(ans[i])+",");
		System.out.print("]\n");
	}
	
	public void showMatrixChar(char[][] ans) {
		// System.out.print("[");
		for(int i = 0; i < ans.length; i++)
			System.out.println(Arrays.toString(ans[i])+",");
		System.out.print("\n");
	}
	
	//set
	public void showSetStr(Set<String> ans) {
		Iterator<String> it = ans.iterator();
		System.out.print("[");
		while(it.hasNext()) System.out.print(it.next() + ", ");
		System.out.print("]\n");
	}
}
