import java.util.*;

public class SweeperText {
	SweeperLogic logic;
	static int row = 0;
	static int col = 0; 
	
	public SweeperText() {
		logic = new SweeperLogic(row, col);
	}
	
	public static void main(String[] args) {
		SweeperText game = new SweeperText();
		game.setup();
	}

	public void setup() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Mine Sweeper!");
		System.out.println("Enter the row number for the board.");
		row = sc.nextInt();	
		System.out.println("Enter the column number for the board.");
		col = sc.nextInt();
		
		if(row<4 || col<4) {
			System.out.println("Please make the board larger than 3 x 3 for maximum fun!");
			setup();
		} else {
			logic = new SweeperLogic(row, col);	
			logic.reset();
			printBoard();
		}
	}

	public void run() {
		while(!logic.isOver()) {
			printBoard();
			makeMove();
		}		
		if(logic.isOver()) {
			
		}
		// You won or lost
	}
	
	public void printBoard() {
		row = logic.numRows();
		col = logic.numCols();
		
	    int [][] board = new int[row][col];
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				board[r][c] = r*c;
				System.out.print(logic.getValue(r, c) + " ");
				}
				System.out.println();
			}
	}
	
	public void makeMove() {
		
		logic.makeMove(row, col);
	}
}
