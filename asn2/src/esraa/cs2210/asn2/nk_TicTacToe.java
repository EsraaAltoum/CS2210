package esraa.cs2210.asn2;

public class nk_TicTacToe {
	//this class implements methods to execute computerplay//
	
	//declaring variables required//
	private static final int DICT_SIZE = 7993;

	private int board_size;
	private int inline;
	private int max_levels;
	private char[][] gameBoard;
	
	//constructor for tictactoe class//
	//initializing declared variables//
	//INPUT: boardsize of type int, inline of type int, and maxlevels of type int//
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		this.board_size= board_size;
		this.inline= inline;
		this.max_levels= max_levels;
		this.gameBoard= new char[board_size][board_size];
		for(int i=0; i < board_size; i++) {
			for(int j=0; j < board_size; j++) {
				this.gameBoard[i][j] = ' ';
			}
		}
		
	}
	
	//method creates empty dictionary //
	//OUTPUT: returns empty dictionary of wanted size
	public Dictionary createDictionary() {
		return new Dictionary(DICT_SIZE);
	}
	
	//method creates a string representation of configuration 
	//and checks if configuration is in dictionary//
	//INPUT: configurations of type dictionary
	//OUTPUT: returns associated score of configuration if found in dictionary, otherwise returns -1//
	public int repeatedConfig(Dictionary configurations) {
		String config= "";
		for(int i=0; i < board_size; i++) {
			for(int j=0; j < board_size; j++) {
				config += this.gameBoard[i][j];
			}
		}
		return configurations.get(config);
	}
	
	//method creates a string representation of configuration
	//and inserts string configuration and score into dictionary//
	//INPUT: configurations of type dictionary, score of type int//
	public void insertConfig(Dictionary configurations, int score) {
		String config= "";
		for(int i=0; i < board_size; i++) {
			for(int j=0; j < board_size; j++) {
				config += this.gameBoard[i][j];
			}
		}
		Record r= new Record(config, score);
		configurations.insert(r);
		
	}
	
	//method stores the symbol in the gameboard [row][col]//
	//INPUT: row of type int, column of type int, symbol of type char//
	public void storePlay(int row, int col, char symbol) {
		this.gameBoard[row][col] = symbol;
	}
	
	//returns true if sqaure in gameboard is empty, and false otherwise
	//INPUT: row of type int, column of type int//
	public boolean squareIsEmpty (int row, int col) {
		if(this.gameBoard[row][col] == ' ') {
			return true;
		}
		return false;
	}
	
	//method returns true for adjacent wins of a symbol 
	//in row, column, or diagonal, false otherwise//
	//INPUT: symbol of type char//
	public boolean wins (char symbol) {
		//horizontal
		int win_count = 0;
		for(int i=0; i < board_size; i++) {
			win_count = 0;
			for(int j=0; j < board_size; j++) {
				if(this.gameBoard[i][j] == symbol) win_count++;
				else win_count = 0;
				
				
				if(win_count >= this.inline)
					return true;
			}
		}
		
		//vertical//
		win_count = 0;
		for(int j=0; j < board_size; j++) {
			win_count = 0;
			for(int i=0; i < board_size; i++) {
				if(this.gameBoard[i][j] == symbol) win_count++;
				else win_count = 0;	
				
				if(win_count >= this.inline)
					return true;
			}
		}
		
		//diagonal forward//
		win_count = 0;
		for(int j = 0; j < board_size; j++) {
			win_count = 0;
			for(int i= 0; i < board_size -j; i++) {
				if(symbol == gameBoard[i + j][ i]) {
					win_count ++;
				}
				else {
					win_count = 0;
					
				}
				if(win_count >= this.inline) {
					return true;
				}
			}
			win_count =0;
			for(int i= 0; i < board_size -j; i++) {
				if(symbol == gameBoard[i][i +j]) {
					win_count++;
				}
				else {
					win_count= 0; 
				}
				if(win_count >= this.inline) {
					return true;
				}
			}
		}
		

		//diagonal forward//
		win_count = 0;
		for(int j = 0; j < board_size; j++) {
			win_count = 0;
			for(int i= 0; i < board_size -j; i++) {
				if(symbol == gameBoard[i + j][board_size-1 - i]) {
					win_count ++;
				}
				else {
					win_count = 0;
					
				}
				if(win_count >= this.inline) {
					return true;
				}
			}
			win_count =0;
			for(int i= 0; i < board_size -j; i++) {
				if(symbol == gameBoard[i][board_size-1 - i - j]) {
					win_count++;
				}
				else {
					win_count= 0; 
				}
				if(win_count >= this.inline) {
					return true;
				}
			}
		}
		return false;	
	}

	//method returns true if gameboard has no empty positions 
	//and no player has won, false otherwise//
	public boolean isDraw() {
		for(int i=0; i < board_size; i++) {
			for(int j=0; j < board_size; j++) {
				if(this.gameBoard[i][j] == ' ')
					return false;	
			}
		}
		if(!wins('X') && !wins('O')) {
			return true;
			}
		else return false;
		
		}
	
	//method returns an evaluation value//
	//OUTPUT: 3 for computer win, 0 for human win, 2 for draw, 1 for undecided
	public int evalBoard() {
		if(wins('O')) {
			return 3;
		}
		else if(wins('X')) {
			return 0;
		}
		else if(isDraw()) {
			return 2;
		}
		else {
			return 1;
		}
	}
		
	









}
