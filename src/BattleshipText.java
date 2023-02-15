import java.util.Scanner;

public class BattleshipText {
	private static BGE bge;
	private static Scanner reader;
	
	public static void play(){
		int row;
		int col;
		while(!bge.isGameOver()){
			System.out.print(bge.showBoard());
			
			do {
				System.out.print("row: ");
				row = reader.nextInt();
			}while(row>7||row<0);
			do {
				System.out.print("column: ");
				col = reader.nextInt();
			}while(col>7||col<0);
			
			boolean hit = bge.shoot(row,col);
			System.out.println(hit? "HIT!":"Miss");
			System.out.println();
			
		}
		System.out.println("Congrats!! You Won!");
	}
	public static void main(String[] args) {
		boolean keepPlaying=true;
		bge = new BGE();
		reader = new Scanner(System.in);
		while(keepPlaying){
			System.out.println("Welcome to Battleship! Select a row and column from 0-7 to see if you can hit all the " +
					"enemy ships\n");
			bge.startGame();
			play();
			System.out.println("Do you want to play again?");
			String again=reader.nextLine();
			keepPlaying = again.equalsIgnoreCase("yes");
			
		}
	
	}
}
