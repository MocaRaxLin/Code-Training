package Graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Util.Parser;

public class No329LongestIncreasingPathinaMatrix {
	public static void main(String[] args) {
		No329LongestIncreasingPathinaMatrix sol = new No329LongestIncreasingPathinaMatrix();
		Parser parser = new Parser();
		String t = "[[9,9,4],[6,6,8],[2,1,1]]\n" + 
				"[[3,4,5],[3,2,6],[2,2,1]]\n" + 
				"[[1,1],[1,1]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] matrix =parser.parseMatrix(s[i]);
			int ans = sol.longestIncreasingPath(matrix);
			System.out.println(ans);
		}
	}
	
    public int longestIncreasingPath(int[][] matrix) {
		// --> time = O(V+E), V is the size of matrix, E is 4*V
    	
    	// 137 / 137 test cases passed.
    	// Runtime: 9 ms
    	
    	// source:
    	// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution
    	
    	// 1. Do DFS from every cell
    	// 2. Compare every 4 direction and skip cells that are out of boundary or smaller
    	// 3. Get matrix max from every cell's max length
    	// 4. Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
    	// 5. The key is to cache the distance because it's highly possible to revisit a cell <- important
    	

    	// It is fast because we don't need to go through the edges of a node again, 
    	// once we have visited the node.
    	// It is similar to asking the height of a tree.
    	
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                max = Math.max(max, len);
            }
        }
        return max;
    }
    
    final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0) return cache[i][j];
        int max = 1;
        for(int[] dir: dirs) {
            int x = i + dir[0], y = j + dir[1];
            // hit boundary or not a strickly increasing path
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;
            int len = 1 + dfs(matrix, x, y, m, n, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
    
    
	public int longestIncreasingPath0(int[][] matrix) {
		// --> time = O(V+E), V is the size of matrix, E is 4*V
		
		// 137 / 137 test cases passed.
		// Runtime: 24 ms
		
        int m = matrix.length; if(m == 0) return 0;
        int n = matrix[0].length; if(n == 0) return 0;
        
        // set indegree and find all zero in-degree nodes -> O(E)
        int[] inDegree = new int[m*n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i > 0 && matrix[i][j] < matrix[i-1][j]) inDegree[(i-1)*n+j]++;
                if(i < m - 1 && matrix[i][j] < matrix[i+1][j]) inDegree[(i+1)*n+j]++;
                if(j > 0 && matrix[i][j] < matrix[i][j-1]) inDegree[i*n+j-1]++;
                if(j < n - 1 && matrix[i][j] < matrix[i][j+1]) inDegree[i*n+j+1]++;
            }
        }
        List<Integer> zeroInDegree = new LinkedList<Integer>(); // -> O(V)
        for(int i = 0 ; i < inDegree.length; i++) if(inDegree[i] == 0) zeroInDegree.add(i);
        
        // Kahn's algorithm for topological sort -> O(V+E)
        // poll every nodes and trace their edges
        Iterator<Integer> it = zeroInDegree.iterator();
        Queue<int[]> q = new LinkedList<int[]>();
        while(it.hasNext()) q.offer(new int[]{it.next(), 1}); // length 1 end at node i.
        int maxLen = 0;
        
        while(q.size() > 0){
            int[] v = q.poll();
            maxLen = Math.max(maxLen, v[1]);
            int i = v[0]/n, j = v[0]%n;
            if(i > 0 && matrix[i][j] < matrix[i-1][j]){
                int id = (i-1)*n+j;
                inDegree[id]--;
                if(inDegree[id] == 0) q.offer(new int[]{id, v[1] + 1});
            }
            if(i < m - 1 && matrix[i][j] < matrix[i+1][j]){
                int id = (i+1)*n+j;
                inDegree[id]--;
                if(inDegree[id] == 0) q.offer(new int[]{id, v[1] + 1});
            }
            if(j > 0 && matrix[i][j] < matrix[i][j-1]){
                int id = i*n+j-1;
                inDegree[id]--;
                if(inDegree[id] == 0) q.offer(new int[]{id, v[1] + 1});
            }
            if(j < n - 1 && matrix[i][j] < matrix[i][j+1]){
                int id = i*n+j+1;
                inDegree[id]--;
                if(inDegree[id] == 0) q.offer(new int[]{id, v[1] + 1});
            }
        }
        
        return maxLen;
    }
}
