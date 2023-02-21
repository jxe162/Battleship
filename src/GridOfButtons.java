import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class GridOfButtons extends JPanel {
	
	private static final int ROWS = 9, COLS = 9, SIZE = 65, BORDER = 2;
	private static final Color BOARD_COLOR = Color.BLACK;
	
	public GridOfButtons() {
		
		setLayout(new GridLayout(ROWS, COLS, BORDER, BORDER));
		setBackground(BOARD_COLOR);
		
		StonesFactory factory = new StonesFactory(SIZE);
		boolean isBlack = false;
		
		for (int col = 0; col < COLS; col++) {
			for (int row = 0; row < ROWS; row++) {
				add(factory.makeButton(isBlack));
				isBlack = !isBlack;
			}
		}
		
		this.initBoard();
	}
	
	public void initBoard() {
		JFrame f = new JFrame("Board Of Buttons");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridBagLayout());
		f.add(this);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(GridOfButtons::new);
	}
}

class StonesFactory{
	
	private static final Color STONE = Color.YELLOW, WHITE_STONE = Color.WHITE, BLACK_STONE = Color.BLACK;
	private final int size;
	private final ImageIcon whiteIcon, blackIcon;
	
	public StonesFactory(int size) {
		this.size = size;
		whiteIcon = new ImageIcon(createImage(false));
		blackIcon = new ImageIcon(createImage(true));
	}
	
	JButton makeButton(boolean isBlack){
		JButton stone = new JButton();
		stone.setPreferredSize(new Dimension(size, size));
		stone.setBackground(STONE);
		stone.setIcon(isBlack ? blackIcon : whiteIcon);
		return stone;
	}
	
	//construct image for button's icon
	private BufferedImage createImage(boolean isBlack) {
		BufferedImage img = new BufferedImage(size , size,  BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setColor(isBlack ? BLACK_STONE : WHITE_STONE);
		g2.fillOval(0,0,size,size);
		g2.dispose();
		return img;
	}
}