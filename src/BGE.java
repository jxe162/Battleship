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
     *              initialized to all O
     *              if targeted but no hit then a space
     *              if targeted and hit then an X
     */

    private boolean[][] setBoard;       //game board with battleships placed; not to be seen by player
    private char[][] playBoard;         //game board to show player with hits and misses
    private int boardSize;              //size of board
    private int[] shipSizes;            //array of ship sizes

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
        // initialize global variables
        boardSize=8;
        shipSizes = new int[]{2, 3, 4};
        setBoard = new boolean[boardSize][boardSize];
        playBoard = new char[boardSize][boardSize];


        //initialize values within 2d arrays
        for(int i=0; i<boardSize;i++){
            for (int j = 0; j < boardSize; j++) {
                playBoard[i][j] = 'O';
                setBoard[i][j]=false;
            }
        }

        //attempt to place ships in testboard
        //TODO: add try catch to catch exception for too many attempts
        //TODO: replace setBoard here with a local variable such that setBoard is only set after all ships are placed
        for (int shipSize: shipSizes) {
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
        //TODO: select random boolean for direction; 2 random ints for position with sizes adjusted based on
        // boardSize and ship size check if ship intersects with any other ships and retry if so; when location
        // found then change given board to add ship

        return testBoard;
    }
    
    /**
     * checks if ship would overlap with any ships already on the board
     * @param board: boolean[][] with preexisiting ships on it
     * @param shipSize: length of ship
     * @param r: start row on board
     * @param c: start column on board
     * @param dir: true is horizontal; false if veritcal
     * @return true if ship can be placed at location without hitting another ship; false if hits a ship
     */
    private boolean checkShip(boolean[][] board, int shipSize, int r, int c, boolean dir){
        //iterates over length of ship starting at given in either row or column depending on direction
        for (int i = 0; i < shipSize; i++) {
            if(dir){
                if(!board[r+i][c])
                    return false;
            }
            else{
                if(!board[r][c+i])
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
     */
    public void shoot(int r, int c){
        //TODO: check setBoard if player hits or misses; adjust playBoard to reflect results

    }
    
    
    /**
     * toString method for the set boards; should not be shown to player
     * @param setBoard: boolean board to print
     * @return string representation of boolean array
     */
    public String toString(boolean[][] setBoard){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<setBoard.length;i++){
            for (int j = 0; j < setBoard[0].length; j++) {
                str.append("[");
                str.append(setBoard[i][j]);
                str.append("] ");
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
        for(int i=0; i<playBoard.length;i++){
            for (int j = 0; j < playBoard[0].length; j++) {
                str.append("[");
                str.append(playBoard[i][j]);
                str.append("]");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
