package Graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MazeGenerator {

	public static void main(String[] args) {
//		MazeGenerator mgr = new MazeGenerator();
//		int[][] se = new int[2][2];
//		char[][] maze = mgr.getRandomMaze(10, 10, 30, se);
//		for(char[] row: maze) {
//			System.out.println(Arrays.toString(row));
//		}
//		System.out.println("start: "+Arrays.toString(se[0]));
//		System.out.println("end: "+Arrays.toString(se[1]));
	}
	
	public MazeGenerator() {
		random = new Random();
	}
	
	Random random;
	Map<Integer, Integer> map;
	public char[][] getRandomMaze(int row, int col, int obstacle, int[][] startEnd){
		if(obstacle > row*col - 2) {
			System.err.println("Too many obstacle.");
			System.err.println("Obstacle should smaller than row x col - 2");
			return null;
		}
		
		// empty maze
		char[][] ret = new char[row][col];
		for(int i = 0; i < row; i++) Arrays.fill(ret[i], ' ');
		
		// random pick
		map = new HashMap<Integer, Integer>();
		int bound = row*col;
		while(obstacle-- > -2) {
			int r = random.nextInt(bound); // [0, bound-1]
			int idx = r;
			if(r == bound - 1) {
				if(map.containsKey(r)) {
					idx = map.get(r);
					map.remove(r);
				} // else idx = r;
			} else { // r < last
				if(map.containsKey(r)) idx = map.get(r);
				map.put(r, map.getOrDefault(bound - 1, bound - 1));
				map.remove(bound - 1);
			}
			bound--;
			
			int i = idx/col;
			int j = idx%col;
//			System.out.println(r);
//			for(Map.Entry<Integer, Integer> p: map.entrySet())
//				System.out.print("("+p.getKey()+","+p.getValue()+"),");
//			System.out.println();
//			System.out.println(idx);
//			System.out.println(i+","+j);
			ret[i][j] = '#';
			if(obstacle < 0) {
				ret[i][j] = obstacle == -1? 'S': 'T';
				// System.out.println(obstacle);
				startEnd[-obstacle-1] = new int[] {i, j};
			}
			
		}
		return ret;
	}

	public void cleanMaze(char[][] maze) {
		for(int i = 0; i < maze.length; i++) {
			for(int j = 0; j < maze[i].length; j++) {
				if(maze[i][j] != '#' && maze[i][j] != 'S' && maze[i][j] != 'T' && maze[i][j] != '*') {
					maze[i][j] = ' ';
				}
			}
		}
	}
}
