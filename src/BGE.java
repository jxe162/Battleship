import java.util.Arrays;
import java.util.Random;

public class BGE {
    /**
     * Battleship game engine or BGE implements a one player battleship game as a package
     * based on https://github.com/florinpop17/app-ideas/blob/master/Projects/3-Advanced/Battleship-Game-Engine.md
     *
     *
     * setBoard:    game board with battleships placed; not to be seen by player
     *              false means no ship in cell
     *              true means ship in cell
     *
     * playBoard:   game board to show player with hits and misses
     *              initialized to all empty space
     *              if targeted but no hit then an O
     *              if targeted and hit then an X
     */

    private boolean[][] setBoard;       //game board with battleships placed; not to be seen by player
    private char[][] playBoard;         //game board to show player with hits and misses with text game
    private int boardSize;              //size of board
    private int[] shipSizes;            //array of ship sizes
    private int hits;
    private int misses;

    
    public BGE(){
        hits=0;
        misses=0;
        boardSize=8;
        shipSizes = new int[]{2, 3, 4};
    
    }
    /**
     * Caller can invoke a startGame() function to begin a 1-player game.
     * This function will generate a 8x8 game board consisting of 3 ships having a width of one square and a length of:
     *     Destroyer: 2 squares
     *     Cruiser: 3 squares
     *     Battleship: 4 squares
     *
     * startGame() will randomly place these ships on the board in any direction and will return an array representing ship placement.
     *
     */
    public void startGame(){
        // initialize boards
        setBoard = new boolean[boardSize][boardSize];
        playBoard = new char[boardSize][boardSize];


        //initialize values within 2d arrays
        for(int i=0; i<boardSize;i++){
            for (int j = 0; j < boardSize; j++) {
                playBoard[i][j] = ' ';
                setBoard[i][j]=false;
            }
        }

        //attempt to place ships in test board
        //TODO: add try catch to catch exception for too many attempts
        //TODO: replace setBoard here with a local variable such that setBoard is only set after all ships are placed
        for (int shipSize: shipSizes) {
            if(shipSize>boardSize)
                throw new IllegalArgumentException();
            setBoard = placeShip(setBoard, shipSize);
        }


    }


    /**
     * edits setBoard to place ship of given size. Does so by:
     *          randomly selects direction (horizontal or vertical) and position
     *          check if able to place ship there
     *          if able, edit setBoard to true where ship is
     *          if unable, retry until able
     *          if after 50 tries, ship is unable to be placed, throw error
     * @param shipSize: size of ship to be placed
     */
    private boolean[][] placeShip(boolean[][] testBoard, int shipSize){
        //initial values; won't be included on board
        boolean shipFound=false;
        boolean dir = true;
        int row=0;
        int col=0;
        Random random = new Random();
        
        
        //keep retrying to find random placement for ship
        //TODO: set limit for number of tries and throw exception when reached
        while(!shipFound){
            //Uses Math.random to select random direction and position; adjusts to not go out of bounds
            dir = random.nextBoolean();
            row = dir ? random.nextInt(boardSize) : random.nextInt(boardSize-shipSize);
            col = !dir ? random.nextInt(boardSize) : random.nextInt(boardSize-shipSize);
            
            shipFound = checkShip(testBoard, shipSize, row, col, dir);
        }
        
        //edit board to add ship
        for (int i = 0; i < shipSize; i++) {
            if(dir){
                testBoard[row][col+i] = true;
            }
            else{
                testBoard[row+i][col] = true;
            }
        }

        return testBoard;
    }
    
    /**
     * checks if ship would overlap with any ships already on the board
     * @param board: boolean[][] with preexisting ships on it
     * @param shipSize: length of ship
     * @param r: start row on board
     * @param c: start column on board
     * @param dir: true is horizontal; false if vertical
     * @return true if ship can be placed at location without hitting another ship; false if hits a ship
     */
    private boolean checkShip(boolean[][] board, int shipSize, int r, int c, boolean dir){
        //iterates over length of ship starting at given in either row or column depending on direction
        //immediately returns false if ship is present; otherwise returns true
        for (int i = 0; i < shipSize; i++) {
            if(dir){
                if(board[r][c+i])
                    return false;
            }
            else{
                if(board[r+i][c])
                    return false;
            }
        }
        return true;
    }

    /**
     * Caller can invoke a shoot() function passing the target row and column coordinates of the targeted cell on the game board.
     * shoot() will return indicators representing if the shot resulted in a hit or miss, the number of ships left (i.e. not yet sunk),
     * the ship placement array, and an updated hits and misses array.
     *
     * @param r: row number to shoot at
     * @param c: column number to shoot at
     * @return returns true if a ship was hit and false if no ship was hit or a cell was hit previously
     */
    public boolean shoot(int r, int c){
        //checks setBoard if player hits or misses; adjust playBoard to reflect results; adjusts setBoard to be false
        boolean hitShip = setBoard[r][c];
        if(playBoard[r][c] == ' ') {
            if(hitShip){
                playBoard[r][c] = 'X';
                hits++;
            }
            else{
                playBoard[r][c] = 'O';
                misses++;
            }
        }
        else{
            misses++;
        }
        setBoard[r][c] = false;
        return hitShip;
        
    }
    
    public int getBoardSize(){
        return boardSize;
    }
    
    public String gameStats(){
        return "'{\"hits\":" +
                hits +
                ", \"misses\":" +
                misses +
                "}'";
    }
    
    /**
     *  string of board to show player
     * @return: string representation of playBoard
     */
    public String showBoard(){
        return toString(playBoard);
    }
    
    /**
     * game is over when player has hit all ships and wins
     * @return true if game is over; false otherwise
     */
    public boolean isGameOver(){
        //iterates over setBoard to see if there are any spots with ships on it
        //note in shoot we change squares to false if its hit
        for (boolean[] row: setBoard) {
            for (boolean cell: row) {
                if (cell)
                    return false;
            }
            
        }
        return true;
    }
    
    public char[][] getPlayerBoard(){
        return playBoard;
    }
    
    /**
     * toString method for the set boards; should not be shown to player
     * @param setBoard: boolean board to print
     * @return string representation of boolean array
     */
    public String toString(boolean[][] setBoard){
        StringBuilder str = new StringBuilder();
        for (boolean[] booleans : setBoard) {
            for (int j = 0; j < setBoard[0].length; j++) {
                str.append("[");
                str.append((booleans[j] ? "X" : " "));
                str.append("]");
            }
            str.append("\n");
        }
        return str.toString();
    }
    
    /**
     * toString method for play Board; can be shown to player
     * @param playBoard: char 2d array to string
     * @return string representation of char array
     */
    public String toString(char[][] playBoard){
        StringBuilder str = new StringBuilder();
        for (char[] chars : playBoard) {
            for (int j = 0; j < playBoard[0].length; j++) {
                str.append("[");
                str.append(chars[j]);
                str.append("]");
            }
            str.append("\n");
        }
        return str.toString();
    }
    
    
    
    
    
    
    /**
     * follow methods are only to allow access to private methods for testing
     */
    public boolean checkShipTest(boolean[][] board, int shipSize, int r, int c, boolean dir){
        return checkShip(board,shipSize,r,c,dir);
    }
    public boolean[][] placeShipTest(boolean[][] board, int shipSize){
        return placeShip(board,shipSize);
    }
    public void setBoardSize(int s){
        boardSize=s;
    }
    
    public void showCheatBoard(){
        System.out.println(toString(setBoard));
    }

}
