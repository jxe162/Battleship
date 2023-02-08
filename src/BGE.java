public class BGE {
    /**
     * Battleship game engine or BGE implements a one player battleship game as a package
     * based on https://github.com/florinpop17/app-ideas/blob/master/Projects/3-Advanced/Battleship-Game-Engine.md
     *
     *
     * setBoard:    game board with battleships placed; not to be seen by player
     *              0 means no ship in cell
     *              1 means ship in cell
     *
     * playBoard:   game board to show player with hits and misses
     *              initialized to all O
     *              if targeted but no hit then a space
     *              if targeted and hit then an X
     */

    private int[][] setBoard;      //game board with battleships placed; not to be seen by player
    private char[][] playBoard;     //game board to show player with hits and misses

    /**
     * Caller can invoke a startGame() function to begin a 1-player game.
     * This function will generate an 8x8 game board consisting of 3 ships having a width of one square and a length of:
     *     Destroyer: 2 squares
     *     Cruiser: 3 squares
     *     Battleship: 4 squares
     *
     * startGame() will randomly place these ships on the board in any direction and will return an array representing ship placement.
     *
     */
    public void startGame(){
        setBoard = new int[8][8];
        playBoard = new char[8][8];


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

    }

}
