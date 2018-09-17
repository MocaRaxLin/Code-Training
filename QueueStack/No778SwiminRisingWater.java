package QueueStack;

import java.util.Comparator;
import java.util.PriorityQueue;

import Util.Parser;

public class No778SwiminRisingWater {

	public static void main(String[] args) {
		No778SwiminRisingWater sol = new No778SwiminRisingWater();
		Parser parser = new Parser();
		String t = "[[0,2],[1,3]]\n" + 
				"[[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]\n" + 
				"[[3,5,6],[0,1,8],[2,7,4]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] grid = parser.parseMatrix(s[i]);
			int ans = sol.swimInWater(grid);
			System.out.println(ans);
		}
	}

	public int swimInWater(int[][] grid) {
		// --> O(NN), where (N,N) is the size of gird.
		
		// Intuition:
		// Use Best first search to select the cell of lower elevation first and try to reach cell[N-1][N-1]
		//     (greedy heuristic)
		// In searching progress, we keep record of heighest elevation we've been walked.
		
		// speed up: use boolean[][] inQ to avoid offering cell existing in priority queue.
		
        int N = grid.length; if(N == 0) return 0;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){return a[2] - b[2]; }
        });
        
        int maxPeak = 0;
        boolean[][] inQ = new boolean[N][N];
        boolean[][] seen = new boolean[N][N];
        int[][] dir = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        pq.offer(new int[]{0, 0, grid[0][0]});
        while(pq.size() > 0){
            int[] cell = pq.poll();
            int i = cell[0], j = cell[1], h = cell[2];
            seen[i][j] = true;
            maxPeak = Math.max(maxPeak, h);
            if(i == N-1 && j == N-1) break;
            for(int d = 0; d < 4; d++){
                int ni = i + dir[d][0], nj = j + dir[d][1];
                if(ni < 0 || nj < 0 || ni == N || nj == N || seen[ni][nj] || inQ[ni][nj]) continue;
                pq.offer(new int[]{ni, nj, grid[ni][nj]});
                inQ[ni][nj] = true; // to ignore the cell already in Q
            }
        }
        return maxPeak;
    }
}
