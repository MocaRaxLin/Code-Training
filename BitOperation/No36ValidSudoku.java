package BitOperation;

import Util.Parser;

public class No36ValidSudoku {

	public static void main(String[] args) {
		No36ValidSudoku sol = new No36ValidSudoku();
		Parser parser = new Parser();
		String t = "[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"],"
				+ "[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"],"
				+ "[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"],"
				+ "[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"],"
				+ "[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"],"
				+ "[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"],"
				+ "[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"],"
				+ "[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"],"
				+ "[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]";
		t = t.replace("\"", "");
		String[] s = t.split("\n");
		for(int i = 0; i < s.length; i++) {
			String[][] boardS = parser.parseMatrixStr(s[i]);
			char[][] board = parser.stringMatrix2CharMatrix(boardS);
			boolean ans = sol.isValidSudoku(board);
			System.out.println(ans);
		}
		
	}
	
    public boolean isValidSudoku(char[][] board) {
    	// --> O(9*9)
    	
        // use 9-bit number to record
        // eg. 100010011 -> 1, 2, 5, 9 exist
        
        int[] grids = new int[9];
        int[] rows = new int[9];
        int[] cols = new int[9];
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                
                int v = 1 << (board[i][j] - '1');
                if((v & rows[i]) != 0 || (v & cols[j]) != 0) return false;
                int g = 3*(i/3) + (j/3);
                if((v & grids[g]) != 0) return false;
                
                rows[i] |= v;
                cols[j] |= v;
                grids[g] |= v;
            }
        }
        return true;
    }

}
