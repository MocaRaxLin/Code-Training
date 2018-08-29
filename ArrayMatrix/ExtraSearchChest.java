package ArrayMatrix;

import Util.Parser;

public class ExtraSearchChest {

	public static void main(String[] args) {
		ExtraSearchChest sol = new ExtraSearchChest();
		Parser parser = new Parser();
		String t = "[[0,0,0,0,1,0,0,0,0,0,1],[0,0,0,0,0,0,1,0,0,1,0],[0,1,0,0,1,0,1,1,1,2,0],[0,1,1,1,1,0,0,0,0,1,0],[0,0,0,0,0,0,0,0,0,0,0],[0,1,1,0,0,1,0,0,0,1,0],[0,0,1,1,0,1,1,1,0,1,0],[1,0,0,0,0,1,0,0,0,0,1],[0,0,0,0,1,1,0,1,1,0,0]]\n" + 
				"[[0,0,0],[0,2,0]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] map = parser.parseMatrix(s[i]);
			int ans = sol.minDisToChase(map);
			System.out.println(ans);
		}
	}

	private int minDisToChase(int[][] map) {
		int n = map.length; if(n == 0) return -1;
		int m = map[0].length; if(m == 0) return -1;
		boolean[][] seen = new boolean[n][m];
		int[] minDis = new int[] {Integer.MAX_VALUE};
		
		// start from map[0][0]
		DFS(map, 0, 0, seen, minDis, 0);
		
		return minDis[0];
	}

	private void DFS(int[][] map, int i, int j, boolean[][] seen, int[] minDis, int curDis) {
		if(i < 0 || j < 0 || map.length == i || map[i].length == j) return;
		if(map[i][j] == 1 || seen[i][j]) return;
		seen[i][j] = true;
		if(map[i][j] == 2) minDis[0] = Math.min(minDis[0], curDis);
		DFS(map, i-1, j, seen, minDis, curDis+1);
		DFS(map, i+1, j, seen, minDis, curDis+1);
		DFS(map, i, j-1, seen, minDis, curDis+1);
		DFS(map, i, j+1, seen, minDis, curDis+1);
		seen[i][j] = false; // unlock to choice other path.
	}

}
