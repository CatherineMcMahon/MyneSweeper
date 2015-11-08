import java.util.Random;
import java.util.Scanner;

class SweeperLogic {
	private static int[][] board;
	private int numMoves = 0;
	private int numMines = 0;
	//	private boolean over = false; // checks if game is over; false = not over

	// 				**   LOGIC TEST   **
	//	public static void main(String[] args) {
	//		SweeperLogic logic = new SweeperLogic(10,10);
	//		System.out.println("getValue: " + logic.getValue(0, 0));
	//		System.out.println("numCols: " + logic.numCols());
	//		System.out.println("numRows: " + logic.numRows());
	//		System.out.println("isOver?: " + logic.isOver());
	//		System.out.println("makeMove at (0,0): " + logic.makeMove(0, 0));
	//		logic.reveal();
	//		System.out.println("isOver?: " + logic.isOver());
	//		for(int x=0; x<10; x++) {
	//			System.out.println("made Move: " + logic.makeMove(x, x));
	//		}
	//		System.out.println("isOver?: " + logic.isOver());
	//	}

	public SweeperLogic(int row, int col) {
		board = new int[row][col];
		reset();
	}

	public boolean makeMove(int row, int col) {
		board[row][col] = Math.abs(board[row][col]); // uncover board space
		return true;
	}

	public int getValue(int row, int col) {
		if(board[row][col] < 0) {
			return 0;
		} else{
			return board[row][col];
		}
	}

	public int numRows() {
		return board.length;
	}

	public int numCols() {
		return board[0].length;
	} 

	// Check if game is over; return true if game is over & false otherwise.
	public boolean isOver() {
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				if(board[r][c] == 9) { // hit bomb
					return true; // User has lost; game over
				} else {
					return false; // Game is not over
				}
			} 
		}
		return false; 
	}

	// Check if user has won; return true if game is over + won & false otherwise
	public boolean hasWon() {
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				if(board[r][c]>0 || board[r][c]==-9) { // only covered spaces are bombs
//					System.err.println("hasWon =true");
					//good for now
				} else {
//					System.err.println("hasWon =false");
					return false; // continue playing
				}
				return true;
			}
		}
		return false;
	}

	public void reveal() {
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				board[r][c] = Math.abs(board[r][c]); // positive values = uncovered
			}
		}
	}

	//	 Resets board with correct values & mines
	public void reset() {
		Random randomGenerator = new Random();
		// mines must be at maximum 1/3 the board's area
		int mines = randomGenerator.nextInt((board.length * board[0].length)/3);
		mines++;
		// generates and sets random places for mines

		for(int x=0; x<mines; x++) {
			int r1 = randomGenerator.nextInt(board.length);
			int c1 = randomGenerator.nextInt(board[0].length);
			board[r1][c1] = 9; // add mine values to board spaces
		} 

		for(int row=0; row<numRows(); row++) {
			for(int col=0; col<numCols(); col++) {
				for(int r=row-1; r<=row+1; r++) {
					for(int c=col-1; c<=col+1; c++) {
						try {
							if(board[row][col] == 9) { 
								numMines++; // add mines to counter.
							}
						}
						catch(IndexOutOfBoundsException e) {
						}
					}
				}
				if(numMines == 0) {
					board[row][col] = -10;
					numMines = 0;
				} else {
//				board[row][col] = -1*numMines; 
				board[row][col] = board[row][col]*-1;
				numMines = 0;
				}
			}
		}
		System.out.println();
	}
}


