import org.junit.Assert;
import org.junit.Test;

public class TestBGE {
	/**
	 * checks if ship would overlap with any ships already on the board
	 *      * @param board: boolean[][] with preexisiting ships on it
	 *      * @param shipSize: length of ship
	 *      * @param r: start row on board
	 *      * @param c: start column on board
	 *      * @param dir: true is horizontal; false if veritcal
	 *      * @return true if ship can be placed at location without hitting another ship; false if hits a ship
	 */
	@Test
	public void testCheckShip(){
		boolean[][] board = new boolean[][]
				{		{false, false, false, false, false, false, false, false},
						{false, false, false, true, false, false, false, false},
						{false, false, false, true, false, false, false, false},
						{false, false, false, true, false, false, true, true},
						{false, false, false, false, false, false, false, false},
						{false, true, true, true, true, false, false, false},
						{false, false, false, false, false, false, false, false},
						{false, false, false, false, false, false, false, false}
		};
		BGE bge = new BGE();
		
		Assert.assertFalse(bge.testcheckShip(board, 3, 1, 1, true));
		Assert.assertFalse(bge.testcheckShip(board, 3, 1, 6, false));
		Assert.assertTrue(bge.testcheckShip(board, 3, 1, 1, false));
		Assert.assertTrue(bge.testcheckShip(board, 3, 6, 1, true));
		
		
	}
	
	@Test
	public void testPlaceShip(){
		boolean[][] board = new boolean[][]
				{		{false, false, false, false, false, false, false, false},
						{false, false, false, true, false, false, false, false},
						{false, false, false, true, false, false, false, false},
						{false, false, false, true, false, false, true, true},
						{false, false, false, false, false, false, false, false},
						{false, true, true, true, true, false, false, false},
						{false, false, false, false, false, false, false, false},
						{false, false, false, false, false, false, false, false}
				};
		BGE bge = new BGE();
		bge.setBoardSize(8);
		
		System.out.println(bge.toString(bge.placeShipTest(board, 2)));
		
	}
	
	@Test
	public void testShoot(){
		BGE bge = new BGE();
		bge.startGame();
		bge.showCheatBoard();
		bge.shoot(1,3);
		bge.shoot(3,3);
		bge.shoot(5,7);
		bge.shoot(2,1);
		bge.shoot(0,0);
		bge.shoot(7,7);
		System.out.println(bge.showBoard());
		
	}
	public static void main(String[] args) {
		TestBGE tbge = new TestBGE();
		tbge.testCheckShip();
	}
}
