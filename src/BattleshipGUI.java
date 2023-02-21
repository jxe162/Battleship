import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BattleshipGUI extends JPanel{
	public JLabel labelText;
	public JButton btn;
	
	public BattleshipGUI() {
		JFrame f = new JFrame("test");
		f.setVisible(true);
		f.setSize(500,500);
		f.setLayout(null);
		labelText = new JLabel("StackOverflow");
		labelText.setBounds(50, 100, 150, 30);
		f.add(labelText);
		
		btn = new JButton("Change Text");
		btn.setBounds(70, 200, 150, 60);
		f.add(btn);
		
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btn.setText("Changed");
			}
		});
	}
	
	public static void main(String[] args) {
		new BattleshipGUI();
	}

}
