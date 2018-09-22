package Graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import Util.Show;

public class MazeRunner {

	public static void main(String[] args) {

		Show show = new Show();
		MazeGenerator mgr = new MazeGenerator();
		MazeRunner runner = new MazeRunner();
		
		int[][] se = new int[2][2];
		char[][] maze = mgr.getRandomMaze(14, 20, 87, se);
		
		
//		List<int[]> path = runner.runBFS(maze, se[0], se[1]);
//		List<int[]> path = runner.runDFS(maze, se[0], se[1]);
		
//		List<int[]> path = runner.runGreedyHeuristic(maze, se[0], se[1]);
//		List<int[]> path = runner.runUniformCost(maze, se[0], se[1]); // like bfs in this grid ha! ha!
		List<int[]> path = runner.runAStar(maze, se[0], se[1]);

		
		show.showMatrixChar(maze);
		mgr.cleanMaze(maze);
		show.showMatrixChar(maze);
		System.out.println("Path: ");
		if(path != null) show.showListIntArray(path);
	}
	
	public List<int[]> runAStar(char[][] maze, int[] s, int[] e) {
		int n = maze.length; if(n == 0) return null;
		int m = maze[0].length; if(m == 0) return null;
		
		boolean[][] seen = new boolean[n][m];
		boolean[][] inPQ = new boolean[n][m];
		boolean found = false;
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) { return a[2]+a[3] - b[2]+b[3]; }
		});
		int h = manhattanDis(s[0], s[1], e[0], e[0]);
		pq.offer(new int[] {s[0], s[1], 0, h});
		inPQ[s[0]][s[1]] = true;
		while(pq.size() > 0) {
			int[] cell = pq.poll();
			int i = cell[0], j = cell[1], step = cell[2];
			seen[i][j] = true;
			inPQ[i][j] = false;
			if(i == e[0] && j == e[1]) { found = true; break; }
			for(int d = 0; d < dir.length; d++) {
				int ni = i+dir[d][0], nj = j+dir[d][1];
				if(!inBound(ni,nj,n,m) || seen[ni][nj] || inPQ[ni][nj] || maze[ni][nj] == '#') continue;
				maze[ni][nj] = (char)('0' + (d+2)%dir.length);
				// g -> (step+1) distance walk from start
				// h -> (manhattan distance) heuristic
				int mDis = manhattanDis(ni, nj, e[0], e[1]);
				pq.offer(new int[] {ni, nj, step+1, mDis}); 
				inPQ[ni][nj] = true;
			}
		}
		
		List<int[]> path = new LinkedList<int[]>();
		if(found) { // trace back the route
			path = new LinkedList<int[]>();
			int i = e[0], j = e[1];
			path.add(0, new int[] {i,j});
			int prev = maze[i][j] - '0';
			maze[i][j] = 'T';
			i += dir[prev][0];
			j += dir[prev][1];
			while(maze[i][j] != 'S') {
				path.add(0, new int[] {i,j});
				prev = maze[i][j] - '0';
				maze[i][j] = '*';
				i += dir[prev][0];
				j += dir[prev][1];
			}
			path.add(0, new int[] {i,j});
		}
		return path;
	}
	
	public List<int[]> runUniformCost(char[][] maze, int[] s, int[] e) {
		int n = maze.length; if(n == 0) return null;
		int m = maze[0].length; if(m == 0) return null;
		
		boolean[][] seen = new boolean[n][m];
		boolean[][] inPQ = new boolean[n][m];
		boolean found = false;
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) { return a[2] - b[2]; }
		});
		pq.offer(new int[] {s[0], s[1], 0});
		inPQ[s[0]][s[1]] = true;
		while(pq.size() > 0) {
			int[] cell = pq.poll();
			int i = cell[0], j = cell[1], step = cell[2];
			seen[i][j] = true;
			inPQ[i][j] = false;
			if(i == e[0] && j == e[1]) { found = true; break; }
			for(int d = 0; d < dir.length; d++) {
				int ni = i+dir[d][0], nj = j+dir[d][1];
				if(!inBound(ni,nj,n,m) || seen[ni][nj] || inPQ[ni][nj] || maze[ni][nj] == '#') continue;
				maze[ni][nj] = (char)('0' + (d+2)%dir.length);
				pq.offer(new int[] {ni, nj, step+1}); // keep distance we have walk so far
				inPQ[ni][nj] = true;
			}
		}
		
		List<int[]> path = new LinkedList<int[]>();
		if(found) { // trace back the route
			path = new LinkedList<int[]>();
			int i = e[0], j = e[1];
			path.add(0, new int[] {i,j});
			int prev = maze[i][j] - '0';
			maze[i][j] = 'T';
			i += dir[prev][0];
			j += dir[prev][1];
			while(maze[i][j] != 'S') {
				path.add(0, new int[] {i,j});
				prev = maze[i][j] - '0';
				maze[i][j] = '*';
				i += dir[prev][0];
				j += dir[prev][1];
			}
			path.add(0, new int[] {i,j});
		}
		return path;
	}
	
	
	public List<int[]> runGreedyHeuristic(char[][] maze, int[] s, int[] e) {
		int n = maze.length; if(n == 0) return null;
		int m = maze[0].length; if(m == 0) return null;
		
		boolean[][] seen = new boolean[n][m];
		boolean[][] inPQ = new boolean[n][m];
		boolean found = false;
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) { return a[2] - b[2]; }
		});
		int mDis = manhattanDis(s[0], s[1], e[0], e[1]);
		pq.offer(new int[] {s[0], s[1], mDis});
		inPQ[s[0]][s[1]] = true;
		while(pq.size() > 0) {
			int[] cell = pq.poll();
			int i = cell[0], j = cell[1];
			seen[i][j] = true;
			inPQ[i][j] = false;
			if(i == e[0] && j == e[1]) { found = true; break; }
			for(int d = 0; d < dir.length; d++) {
				int ni = i+dir[d][0], nj = j+dir[d][1];
				if(!inBound(ni,nj,n,m) || seen[ni][nj] || inPQ[ni][nj] || maze[ni][nj] == '#') continue;
				maze[ni][nj] = (char)('0' + (d+2)%dir.length);
				mDis = manhattanDis(ni, nj, e[0], e[1]);
				pq.offer(new int[] {ni, nj, mDis}); // sort based on heuristic
				inPQ[ni][nj] = true;
			}
		}
		
		List<int[]> path = new LinkedList<int[]>();
		if(found) { // trace back the route
			path = new LinkedList<int[]>();
			int i = e[0], j = e[1];
			path.add(0, new int[] {i,j});
			int prev = maze[i][j] - '0';
			maze[i][j] = 'T';
			i += dir[prev][0];
			j += dir[prev][1];
			while(maze[i][j] != 'S') {
				path.add(0, new int[] {i,j});
				prev = maze[i][j] - '0';
				maze[i][j] = '*';
				i += dir[prev][0];
				j += dir[prev][1];
			}
			path.add(0, new int[] {i,j});
		}
		return path;
	}
	
	public List<int[]> runDFS(char[][] maze, int[] s, int[] e) {
		// --> time = O(nm),
		// Pretty bad preformance
		// Not a short path.
		
		int n = maze.length; if(n == 0) return null;
		int m = maze[0].length; if(m == 0) return null;
		List<int[]> path = new LinkedList<int[]>();
		boolean hasPath = dfs(maze, s[0], s[1], e[0], e[1], path);
		maze[s[0]][s[1]] = 'S';
		return hasPath? path: null;
	}
	
	private boolean dfs(char[][] maze, int i, int j, int ei, int ej, List<int[]> path) {
		if(!inBound(i, j, maze.length, maze[0].length) || maze[i][j] == '#' || maze[i][j] == '*') return false;
		if(i == ei && j == ej) return true;
		maze[i][j] = '*';
		path.add(new int[] {i, j});
		for(int d = 0; d < dir.length; d++) {
			int ni = i+dir[d][0], nj = j+dir[d][1];
			if(dfs(maze, ni, nj, ei, ej, path)) return true;
		}
		path.remove(path.size() - 1);
		return false;
	}
	
	public List<int[]> runBFS(char[][] maze, int[] s, int[] e) {
		// --> time = O(nm)
		
		int n = maze.length; if(n == 0) return null;
		int m = maze[0].length; if(m == 0) return null;
		
		boolean[][] seen = new boolean[n][m];
		boolean[][] inQ = new boolean[n][m];
		boolean found = false;
		
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(s);
		inQ[s[0]][s[1]] = true;
		while(q.size() > 0) {
			int[] cell = q.poll();
			int i = cell[0], j = cell[1];
			inQ[i][j] = false;
			seen[i][j] = true;
			
			for(int d = 0; d < dir.length; d++) {
				int ni = i + dir[d][0];
				int nj = j + dir[d][1];
				if(!inBound(ni,nj,n,m) || seen[ni][nj] || inQ[ni][nj] || maze[ni][nj] == '#') continue;
				maze[ni][nj] = (char)('0' + (d+2)%dir.length);
				if(ni == e[0] && nj == e[1]) { found = true; break; }
				q.offer(new int[] {ni,nj});
				inQ[ni][nj] = true;
			}
		}
		
		List<int[]> path = null;
		if(found) { // trace back the route
			path = new LinkedList<int[]>();
			int i = e[0], j = e[1];
			path.add(0, new int[] {i,j});
			int prev = maze[i][j] - '0';
			maze[i][j] = 'T';
			i += dir[prev][0];
			j += dir[prev][1];
			while(maze[i][j] != 'S') {
				path.add(0, new int[] {i,j});
				prev = maze[i][j] - '0';
				maze[i][j] = '*';
				i += dir[prev][0];
				j += dir[prev][1];
			}
			path.add(0, new int[] {i,j});
		}
		return path;
	}
	
	private int[][] dir;
	public MazeRunner() {
		// 0: up, 1: right, 2: down, 3: left
		dir = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
	}
	private boolean inBound(int i, int j, int n, int m) {
		return 0 <= i && i < n && 0 <= j && j < m;
	}
	private int manhattanDis(int x0, int y0, int x1, int y1) {
		return Math.abs(x0-x1) + Math.abs(y0-y1);
	}
}
