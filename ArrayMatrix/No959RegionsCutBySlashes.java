package ArrayMatrix;

import java.util.Arrays;

import Util.Parser;

public class No959RegionsCutBySlashes {

	public static void main(String[] args) {
		No959RegionsCutBySlashes sol = new No959RegionsCutBySlashes();
		Parser parser = new Parser();
		String t = "[\" /\",\"/ \"]\n" + 
				"[\" /\",\"  \"]\n" + 
				"[\"\\/\",\"/\\\"]\n" + 
				"[\"/\\\",\"\\/\"]\n" + 
				"[\"//\",\"/ \"]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			// System.out.println(s[i]);
			String[] grid = parser.parseArrayStr(s[i]);
			// System.out.println(Arrays.toString(grid));
			int ans = sol.regionsBySlashes(grid);
			System.out.println(ans);
		}
	}

	int N;
    public int regionsBySlashes(String[] grid) {
        N = grid.length;
        boolean[][] map = new boolean[3*N][3*N];// T -> obstacle, F -> empty
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                setMap(map, i, j, grid[i].charAt(j)); // '\\', '/', ' '
            }
        }
        /*
        for(int i = 0; i < map.length; i++){
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
        */
        int count = 0;
        boolean[][] seen = new boolean[3*N][3*N];
        for(int i = 0; i < 3*N; i++){
            for(int j = 0; j < 3*N; j++){
                if(!map[i][j] && !seen[i][j]){
                    DFS(map, seen, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    
    public void setMap(boolean[][] map, int i, int j, char c){
        i *= 3;
        j *= 3;
        if(c == '/'){
            for(int k = 0; k < 3; k++) map[i+2-k][j+k] = true;
        }else if(c == '\\'){
            for(int k = 0; k < 3; k++) map[i+k][j+k] = true;
        }
    }
    public void DFS(boolean[][] map, boolean[][] seen, int i, int j){
        if(i < 0 || j < 0 || i == 3*N || j == 3*N) return;
        if(map[i][j] || seen[i][j]) return;
        seen[i][j] = true;
        DFS(map, seen, i+1, j);
        DFS(map, seen, i, j+1);
        DFS(map, seen, i-1, j);
        DFS(map, seen, i, j-1);
    }
}
