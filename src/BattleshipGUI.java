import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BattleshipGUI extends JPanel{
	public JLabel labelText;
	public JButton btn;
	private BGE bge;
	private static final int BORDER = 2, squareSize=50;
	private static final Color Hit=Color.red, Miss=Color.gray;
	
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
		JFrame f = new JFrame("Battleship Board");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridBagLayout());
		f.add(this);
		f.pack();
		f.setVisible(true);
	}
	
	
	public JButton makeButton(int row, int col){
		JButton square = new JButton();
		square.setPreferredSize(new Dimension(squareSize, squareSize));
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
				square.removeActionListener(this);
				
			}
		});
		return square;
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(BattleshipGUI::new);
	}

}
