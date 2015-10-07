import java.util.*;

public class SweeperText {
	SweeperLogic logic;
	static int row = 0;
	static int col = 0; 
	
	public SweeperText() {
		System.out.println("Welcome to Mine Sweeper!");
		setup();
	}
	
	public static void main(String[] args) {
		SweeperText game = new SweeperText();
		game.setup();
	}

	public void setup() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a row number for the board.");
		row = sc.nextInt();	
		System.out.println("Enter a column number for the board.");
		col = sc.nextInt();
		
		if(row<4 || col<4) {
			System.out.println("Please make the board larger than 3 x 3 for maximum fun!");
			setup();
		} else {
			logic = new SweeperLogic(row, col);	
			printBoard();
			run();
		}
	}

//	After player has moved, check if they lost, won, or can continue.
	public void run() {
		if(logic.isOver()) {
			System.out.println("Ah! You hit a bomb! You've lost.");
		} else {
			makeMove();
		}
	}
	
	public void printBoard() {
		row = logic.numRows();
		col = logic.numCols();
		
	    int [][] board = new int[row][col];
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				board[r][c] = r*c;
//				System.out.print(logic.getValue(r, c) + " ");
				}
			}
	}
	
//	Get board space for move, move there, call run()
	public void makeMove() {
		Scanner cs = new Scanner(System.in);
		System.out.println("Enter a row number for where you'd like to move.");
		int roww = cs.nextInt();	
		System.out.println("Enter a column number for where you'd like to move.");
		int coll = cs.nextInt();
		
		logic.makeMove(roww, coll);
		run();
	}
}
