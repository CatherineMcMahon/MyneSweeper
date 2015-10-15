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
		
		if(row<4 || col<4) { // Check if board is big enough & prompt user again
			System.out.println("Please make the board larger than 3 x 3 for maximum fun!");
			setup(); 
		} else { // Yay, make board & run game
			logic = new SweeperLogic(row, col);	
			run();
		}
	}

//	After player has moved, check if they lost, won, or can continue.
	public void run() {
		if(logic.hasWon() != false) {
			System.out.println("Woohoo! You've won the game!");
		} else if(logic.isOver() != false) {
			makeMove();
			System.out.println(":/ A bomb blew up and you've lost the game.");
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
					System.out.print(logic.getValue(r, c) + "   ");				
				}
				System.out.println(""); // styling
				System.out.println(""); // styling
			}
		System.out.println(""); // styling
	}
	
//	Get board space for move, move there, call run()
	public void makeMove() {
		Scanner cs = new Scanner(System.in);
		System.out.println("Move to row: ");
		int r = cs.nextInt();	
		System.out.println("Move to column: ");
		int c = cs.nextInt();
		
		if(r <= (logic.numRows()-1) && c <= (logic.numCols()-1)) {
			logic.makeMove(r, c);
			printBoard();
			run();
		} else {
			System.out.println("Invalid space. Enter a new value.");
			makeMove();
		}
	}
}
