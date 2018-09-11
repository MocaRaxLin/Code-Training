package ArrayMatrix;

import Util.Parser;
import Util.Show;

public class No289GameofLife {

	public static void main(String[] args) {
		No289GameofLife sol = new No289GameofLife();
		Parser parser = new Parser();
		Show show = new Show();
		String t = "[[0,1,0],[0,0,1],[1,1,1],[0,0,0]]";
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			int[][] board = parser.parseMatrix(s[i]);
			sol.gameOfLife(board);
			show.showMatrix(board);
		}
	}

	public void gameOfLife(int[][] board) {
        // code:
        // 2: 1 -> 0
        // 3: 0 -> 1
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                int live = countLife(board, i, j);
                if(board[i][j] == 1 && (live < 2 || 3 < live)) board[i][j] = 2;
                else if(board[i][j] == 0 && live == 3) board[i][j] = 3;
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] %= 2;
            }
        }
        
    }
    
    int[] di = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};
    int[] dj = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    private int countLife(int[][] board, int i, int j){
        int ret = 0;
        for(int d = 0; d < 8; d++){
            int h = i + di[d];
            int k = j + dj[d];
            if(0 <= h && h < board.length && 0 <= k && k < board[h].length
               && (board[h][k] == 1 || board[h][k] == 2)) ret++;
        }
        return ret;
    }
}
