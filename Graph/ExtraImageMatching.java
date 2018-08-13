package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import Util.Parser;

public class ExtraImageMatching {

	public static void main(String[] args) {
		ExtraImageMatching sol = new ExtraImageMatching();
		Parser parser = new Parser();
		String t = "[[1,1,1],[1,0,0],[1,0,1]]\n"
				+ "[[1,1,1],[1,0,0],[1,0,1]]\n"
				+ "[[1,1,1],[1,0,0],[1,0,1]]\n"
				+ "[[1,1,1],[1,0,1],[1,0,0]]\n"
				+ "[[1,1,0,1,1,1,1],[1,0,0,1,0,0,0],[1,1,1,0,0,0,0],[0,1,0,0,1,0,1],[0,0,0,0,1,1,1]]\n"
				+ "[[1,1,0,1,1],[1,0,0,1,0],[1,1,1,0,0],[0,1,0,0,1]]\n"
				+ "[[1,0,0],[0,1,1]]\n"
				+ "[[1,0],[0,1],[0,1]]\n"
				+ "[[0,0,1,0],[1,1,1,0],[0,0,0,1]]\n"
				+ "[[1,0,1,0],[1,0,1,0],[0,1,0,1]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i+=2) {
			int[][] A = parser.parseMatrix(s[i]);
			int[][] B = parser.parseMatrix(s[i+1]);
			int ans = sol.noOfOverlapMatchRegions(A, B);
			System.out.println(ans);
		}
	}
	
	private int noOfOverlapMatchRegions(int[][] A, int[][] B) {
		// -->  O(nm)
		
		// do DFS from positions with both grid A and B available.
		// collect DFS nodes compare both results to see if the search tree is the same.
		
		int n = Math.min(A.length, B.length); if(n == 0) return 0;
		int m = Math.min(A[0].length, B[0].length); if(m == 0) return 0;
		
		boolean[][] seenA = new boolean[n][m];
		boolean[][] seenB = new boolean[n][m];
		int counter = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(seenA[i][j] || seenB[i][j] || A[i][j] == 0 || B[i][j] == 0) continue;
				List<int[]> listA = new LinkedList<int[]>();
				DFSCollection(listA, A, n, m, i, j, seenA);
				List<int[]> listB = new LinkedList<int[]>();
				DFSCollection(listB, B, n, m, i, j, seenB);
				if(sameList(listA, listB)) counter++;
			}
		}
		return counter;
	}

	private boolean sameList(List<int[]> a, List<int[]> b) {
		if(a.size() != b.size()) return false;
		Iterator<int[]> ia = a.iterator();
		Iterator<int[]> ib = b.iterator();
		while(ia.hasNext() && ib.hasNext()) {
			int[] posA = ia.next();
			int[] posB = ib.next();
			if(posA[0] != posB[0] || posA[1] != posB[1]) return false;
		}
		return true;
	}
	private void DFSCollection(List<int[]> list, int[][] grid, int n,  int m, int i, int j, boolean[][] seen) {
		if(i < 0 || j < 0 || n == i || j == m) return;
		if(seen[i][j] || grid[i][j] == 0) return;
		list.add(new int[] {i, j});
		seen[i][j] = true;
		DFSCollection(list, grid, n, m, i-1, j, seen); // up
		DFSCollection(list, grid, n, m, i, j-1, seen); // left
		DFSCollection(list, grid, n, m, i+1, j, seen); // down
		DFSCollection(list, grid, n, m, i, j+1, seen); // right
		
	}

}
