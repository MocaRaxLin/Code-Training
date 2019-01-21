package OOD;

public class Chess {
	
	public Player player1, player2;
	public Board board;
	public Chess() {
		player1 = new Player(0);
		player2 = new Player(1);
		board = new Board(player1, player2);
	}
	
	
	public static void main(String[] args) {
		
	}

}

class Board{
	Unit[][] board;
	final int N = 8;
	Player p1, p2;
	public Board(Player player1, Player player2) {
		p1 = player1;
		p2 = player2;
		resetBoard();
	}
	
	public void resetBoard() {
		board = new Unit[N][N];
		for(int i = 0; i < N; i++) board[1][i] = new Pawn(1, i, board, 0);
		board[0][0] = new Rook(0, 0, board, 0);
		board[0][1] = new Knight(0, 1, board, 0);
		board[0][2] = new Bishop(0, 2, board, 0);
		board[0][3] = new King(0, 3, board, 0);
		board[0][7] = new Rook(0, 7, board, 0);
		board[0][6] = new Knight(0, 6, board, 0);
		board[0][5] = new Bishop(0, 5, board, 0);
		board[0][4] = new Queen(0, 4, board, 0);
		for(int i = 0; i < N; i++) board[6][i] = new Pawn(6, i, board, 1);
		board[7][0] = new Rook(0, 0, board, 1);
		board[7][1] = new Knight(0, 1, board, 1);
		board[7][2] = new Bishop(0, 2, board, 1);
		board[7][3] = new King(0, 3, board, 1);
		board[7][7] = new Rook(0, 7, board, 1);
		board[7][6] = new Knight(0, 6, board, 1);
		board[7][5] = new Bishop(0, 5, board, 1);
		board[7][4] = new Queen(0, 4, board, 1);
	}
}

class Player{
	int color;
	boolean win;
	int N = 8;
	public Player(int c) {
		win = false;
		color = c;
	}
	
	public boolean pickFromTo(Unit[][] board, int i, int j, int x, int y) {
		if(x < 0 || y < 0 || x == N || y == N || i < 0 || j < 0 || i == N || j == N || board[i][j] == null || board[i][j].color != color) return false;
		return board[i][j].move(x, y);
	}
}

abstract class Unit{
	int i;
	int j;
	int N;
	Unit[][] board;
	int color;
	public Unit(int i, int j, Unit[][] board, int c) {
		this.i = i;
		this.j = j;
		this.board = board;
		this.color = c;
	}
	public boolean moveTo(int x, int y) {
		if(x < 0 || y < 0 || x == N || y == N) return false;
		i = x;
		j = y;
		return true;
	}
	
	public boolean move(int x, int y) {
		System.out.println("you should not see this line!");
		return false;
	}
	
	public boolean isPlaceable(int x, int y) {
		return board[x][y] == null || board[x][y].color == 1-color;
	}
	
	public boolean hasObstacle(int i, int j, int x, int y) {
		int di = x - i > 0? 1 :x == i? 0 : -1;
		int dj = y - j > 0? 1 :y == j? 0 : -1;
		while(i != x && j != y) {
			i += di; j += dj;
			if(board[i][j] != null && board[i][j].color == this.color) return true;
		}
		return true;
	}
}

interface moveValidate{
	boolean isValid(int x, int y);
}

class King extends Unit implements moveValidate{
	public King(int i, int j, Unit[][] board, int color) {
		super(i, j, board, color);
	}
	public boolean move(int x, int y) {
		return isValid(x, y) && moveTo(x, y);
	}
	public boolean isValid(int x, int y) {
		if(x == i && y == j) return false;
		return Math.abs(x-i) < 2 && Math.abs(y-i) < 2 && isPlaceable(x, y);
	}
}

class Queen extends Unit implements moveValidate{
	public Queen(int i, int j, Unit[][] board, int color) {
		super(i, j, board, color);
	}
	public boolean move(int x, int y) {
		return isValid(x, y) && moveTo(x, y);
	}
	public boolean isValid(int x, int y) {
		if(x == i && y == j) return false;
		return (i == x || j == y || Math.abs((x-i)/(y-j)) == 1) && isPlaceable(x, y) && !hasObstacle(i, j, x, y);
	}
}

class Bishop extends Unit implements moveValidate{
	public Bishop(int i, int j, Unit[][] board, int color) {
		super(i, j, board, color);
	}
	public boolean move(int x, int y) {
		return isValid(x, y) && moveTo(x, y);
	}
	public boolean isValid(int x, int y) {
		if(x == i && y == j) return false;
		return Math.abs((x-i)/(y-j)) == 1 && isPlaceable(x, y) && !hasObstacle(i, j, x, y);
	}
}

class Knight extends Unit implements moveValidate{
	public Knight(int i, int j, Unit[][] board, int color) {
		super(i, j, board, color);
	}
	public boolean move(int x, int y) {
		return isValid(x, y) && moveTo(x, y);
	}
	public boolean isValid(int x, int y) {
		if(x == i && y == j) return false;
		return (Math.abs(x-i) == 1 && Math.abs(y-j) == 2) || (Math.abs(x-i) == 2 && Math.abs(y-j) == 1) && isPlaceable(x, y);
	}
}

class Rook extends Unit implements moveValidate{
	public Rook(int i, int j, Unit[][] board, int color) {
		super(i, j, board, color);
	}
	public boolean move(int x, int y) {
		return isValid(x, y) && moveTo(x, y);
	}
	public boolean isValid(int x, int y) {
		if(x == i && y == j) return false;
		return (i == x || j == y) && isPlaceable(x, y) && !hasObstacle(i, j, x, y);
	}
}

class Pawn extends Unit implements moveValidate{
	boolean first;
	public Pawn(int i, int j, Unit[][] board, int color) {
		super(i, j, board, color);
		first = true;
	}
	public boolean move(int x, int y) {
		if(isValid(x, y) && moveTo(x, y)) {
			if(first) first = false;
			return true;
		}
		return false;
	}
	public boolean isValid(int x, int y) {
		if(x == i && y == j) return false;
		if(first) {
			return x == i && (y - j == 1  || y - j == 2) && isPlaceable(x, y) && !hasObstacle(i, j, x, y);
		}else {
			return x == i && y - j == 1 && isPlaceable(x, y);
		}
	}
}

