import java.util.Random;
import java.util.Scanner;

class SweeperLogic {
	private static int[][] board;
	private int numMoves = 0;
	private int numMines = 0;
	boolean over = false;

// 				**   LOGIC TEST   **
//	public static void main(String[] args) {
//		SweeperLogic logic = new SweeperLogic(10,10);
//		System.out.println("Welcome to Mine Sweeper!");
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
//
//	}

	public SweeperLogic(int row, int col) {
		board = new int[row][col];
		reset();
	}

	public boolean makeMove(int row, int col) {
		try {
			for(int r=row-1; r<=row+1; r++) {
				for(int c=col-1; c<=col+1; c++) {
					if(board[r][c] == 9) { 
						over = true;
						numMines++;
						board[row][col] = numMines; // numMines next to me
						return true;
					}
				}
			}
			if(numMines == 0) { 
				board[row][col] = 10; // no mines next to me	
				return true;
			}
		} catch(ArrayIndexOutOfBoundsException ex) {
			return false;
		}
		return false;
	}

	public int getValue(int row, int col) {
		
		return board[row][col];
	}

	public int numRows() {
		return board.length;
	}

	public int numCols() {
		return board[0].length;
	} 

	public boolean isOver() {
		// if all covered spaces are bombs, user has won
		return over;
	}

	public boolean hasWon() {
		return true;
	}

	public void reveal() {
		for(int r=0; r<board.length; r++) {
			for(int c=0; c<board[0].length; c++) {
				board[r][c] = Math.abs(board[r][c]);
			}
		}
		over = true;
	}

//	 Resets board with mines and calculates value for every board space.
	public void reset() {
		Random randomGenerator = new Random();
//		 mines must be at maximum 1/3 the board's area
		numMines = randomGenerator.nextInt((board.length * board[0].length)/3);
//		System.out.println("numMines: " + numMines);
//		 generates and sets random places for mines
		for(int x=0; x<numMines; x++) {
			int row = randomGenerator.nextInt(board.length);
			int col = randomGenerator.nextInt(board[0].length);
			if( board[row][col] != 9 ) {
				board[row][col] = 9;
			}
			else{
				x--;
			}
		}
		// Cover non-mine spaces -> value = negative
		for(int row=0; row<numRows(); row++) {
			for(int col=0; col<numCols(); col++) {
				for(int r=row-1; r<=row+1; r++) {
					for(int c=col-1; c<=col+1; c++) {
						try {
							if(board[r][c] == 9) { 
								numMines++;
							}
						}
						catch(IndexOutOfBoundsException e  ) {
						}
					}
				}
				if(numMines == 0) { 
					board[row][col] = 10; // 10 = no mines next to me	
				}else{
					board[row][col] = -1*numMines; // neg. number = (covered) numMines next to me
//					System.err.println("why");
				}
			}
		}
	}
}
