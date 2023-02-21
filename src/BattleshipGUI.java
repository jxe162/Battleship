import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BattleshipGUI extends JPanel{
	private BGE bge;
	private static final int BORDER = 2, squareSize=50;
	private static final Color Hit=Color.red, Miss=Color.gray, Neuter=Color.white;
	private JFrame gameFrame, winFrame;
	
	public BattleshipGUI() {
		bge =  new BGE();
		bge.startGame();
		setLayout(new GridLayout(bge.getBoardSize(),bge.getBoardSize(), BORDER,BORDER));
		for (int col = 0; col < bge.getBoardSize(); col++) {
			for (int row = 0; row < bge.getBoardSize(); row++) {
				add(makeButton(row,col));
			}
		}
		this.initBoard();
	}
	
	public void initBoard() {
		gameFrame = new JFrame("Battleship Board");
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLayout(new GridBagLayout());
		gameFrame.add(this);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	
	public void winGame(){
		winFrame = new JFrame("You Win");
		winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		winFrame.add(new JLabel("Congrats!!! \nYou Won!!!"));
		winFrame.pack();
		winFrame.setVisible(true);
		gameFrame.setVisible(false);
		gameFrame.dispose();
		
	}
	
	
	public JButton makeButton(int row, int col){
		JButton square = new JButton();
		square.setPreferredSize(new Dimension(squareSize, squareSize));
		square.setBackground(Neuter);
		square.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean hit = bge.shoot(row, col);
				if (hit){
					square.setBackground(Hit);
				}
				else {
					square.setBackground(Miss);
				}
				if(bge.isGameOver()){
					winGame();
				}
				square.removeActionListener(this);
				
			}
		});
		return square;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(BattleshipGUI::new);
	}

}
