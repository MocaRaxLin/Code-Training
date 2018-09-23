package Graph;

import java.util.LinkedList;
import java.util.Queue;

import Util.Parser;

public class No909SnakesandLadders {

	public static void main(String[] args) {
		No909SnakesandLadders sol = new No909SnakesandLadders();
		Parser parser = new Parser();
		String t = "[[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]\n" + 
				"[[1,1,-1],[1,1,1],[-1,1,1]]\n" + 
				"[[-1,-1,-1],[-1,9,8],[-1,8,9]]\n" + 
				"[[-1,-1,-1,46,47,-1,-1,-1],[51,-1,-1,63,-1,31,21,-1],[-1,-1,26,-1,-1,38,-1,-1],[-1,-1,11,-1,14,23,56,57],[11,-1,-1,-1,49,36,-1,48],[-1,-1,-1,33,56,-1,57,21],[-1,-1,-1,-1,-1,-1,2,-1],[-1,-1,-1,8,3,-1,6,56]]\n" + 
				"[[-1,-1,27,13,-1,25,-1],[-1,-1,-1,-1,-1,-1,-1],[44,-1,8,-1,-1,2,-1],[-1,30,-1,-1,-1,-1,-1],[3,-1,20,-1,46,6,-1],[-1,-1,-1,-1,-1,-1,29],[-1,29,21,33,-1,-1,-1]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] board = parser.parseMatrix(s[i]);
			int ans = sol.snakesAndLadders(board);
			System.out.println(ans);
		}
	}

	 int[] dp;
	 int N;
	 public int snakesAndLadders(int[][] board) {
	        N = board.length;
	        // dp = new int[N*N+1];
	        // Arrays.fill(dp, Integer.MAX_VALUE);
	        // dfs(board, 1, 0);
	        // return dp[N*N] == Integer.MAX_VALUE? -1: dp[N*N];
	        return bfs(board);
	    }
	    
	    
	    private int bfs(int[][] board){
	        // --> O(N^2), for each index we chech once :)
	        
	        Queue<int[]> q = new LinkedList<int[]>();
	        boolean[] seen = new boolean[N*N+1];
	        q.offer(new int[]{1, 0}); // idx, step
	        seen[1] = true;
	        while(q.size() > 0){
	            /*
	            Iterator<int[]> it = q.iterator();
	            while(it.hasNext()) System.out.print(Arrays.toString(it.next())+", ");
	            System.out.println();
	            */
	            int[] cur = q.poll();
	            int idx = cur[0], step = cur[1];
	            
	            for(int i = 1; i <= 6 && idx + i <= N*N; i++){
	                
	                int[] rc = convert(idx+i);
	                int next = board[rc[0]][rc[1]] == -1? idx+i: board[rc[0]][rc[1]];
	                if(next == N*N) return step+1;
	                if(seen[next]) continue;
	                seen[next] = true;
	                q.offer(new int[]{next, step+1});
	            }
	            
	        }
	        return -1;
	    }
	    
	    
	    
	    
	    
	    
	    private void dfs(int[][] board, int idx, int step){
	        // --> O(?), dfs is a slow method,
	        // because we don't know how many time an index will be updated.
	        if(idx > N*N || dp[idx] <= step) return;
	        dp[idx] = step;
	        for(int i = 1; i <= 6 && idx + i <= N*N; i++){
	            int[] rc = convert(idx+i);
	            // System.out.println(idx+i + ": " + Arrays.toString(rc));
	            if(board[rc[0]][rc[1]] == -1) dfs(board, idx+i, step+1);
	            else dfs(board, board[rc[0]][rc[1]], step+1);
	        }
	    }
	    
	    private int[] convert(int idx){
	        int tmp = (idx-1)/N;
	        int r = N - 1 - tmp;
	        int tmp2 = (idx-1)%N;
	        int c = tmp % 2 == 0? tmp2: N - 1 - tmp2;
	        return new int[]{r, c};
	    }
}
